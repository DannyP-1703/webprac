CREATE TABLE individual_client (
  client_id SERIAL PRIMARY KEY,
  passport CHAR(10) UNIQUE NOT NULL,
  surname VARCHAR(100) NOT NULL,
  "name" VARCHAR(100) NOT NULL,
  patronymic VARCHAR(100),
  region VARCHAR(100) NOT NULL,
  contact_phone_number CHAR(12) NOT NULL,
  email VARCHAR(100)
);

CREATE TABLE entity_client (
  client_id SERIAL PRIMARY KEY,
  registration_number CHAR(13) UNIQUE NOT NULL,
  "name" VARCHAR(100) NOT NULL,
  region VARCHAR(100) NOT NULL,
  "address" TEXT NOT NULL,
  contact_surname VARCHAR(100) NOT NULL,
  contact_name VARCHAR(100) NOT NULL,
  contact_patronymic VARCHAR(100),
  contact_phone_number CHAR(12) NOT NULL,
  email VARCHAR(100)
);

CREATE TYPE ACCOUNT_STATUS AS ENUM ('ACTIVE', 'BLOCKED', 'CLOSED');
CREATE TYPE OPERATION_TYPE AS ENUM (
    'TOPUP',
    'SERVICE_CONNECT',
    'SERVICE_SUBSCRIPTION',
    'SERVICE_DISCONNECT',
    'ACCOUNT_OPEN',
    'ACCOUNT_BLOCK',
    'ACCOUNT_UNBLOCK',
    'ACCOUNT_CLOSE'
);
CREATE TYPE SUBSCRIPTION_TYPE AS ENUM ('ONETIME', 'DAILY', 'MONTHLY', 'ANNUAL');

CREATE TABLE account (
  account_id SERIAL PRIMARY KEY,
  individual_client_id INT REFERENCES individual_client (client_id) ON DELETE CASCADE,
  entity_client_id INT REFERENCES entity_client (client_id) ON DELETE CASCADE,
  creation_time timestamp NOT NULL,
  balance NUMERIC NOT NULL,
  serviced_phone_number CHAR(12),
  status ACCOUNT_STATUS NOT NULL,
  credit_max NUMERIC NOT NULL,
  credit_interval INTERVAL,
  CHECK ( (individual_client_id IS NULL) != (entity_client_id IS NULL) )
);

CREATE TABLE service (
  service_id SERIAL PRIMARY KEY,
  "name" VARCHAR(100) UNIQUE NOT NULL,
  subscription_type SUBSCRIPTION_TYPE NOT NULL,
  activation_fee NUMERIC,
  subscription_fee NUMERIC,
  deactivation_fee NUMERIC,
  duration INTERVAL,
  package_phone SMALLINT,       -- in minutes
  package_internet SMALLINT,    -- in GB
  package_message SMALLINT,     -- in pieces
  description TEXT,
  CONSTRAINT subscription_type_check CHECK (
    NOT (subscription_type = 'ONETIME') OR (activation_fee IS NOT NULL) AND
    NOT (subscription_type IN ('DAILY', 'MONTHLY', 'ANNUAL')) OR (subscription_fee IS NOT NULL)
  )
);

CREATE TABLE connected_service (
  connection_id SERIAL PRIMARY KEY,     -- do i need it?
  account_id INT NOT NULL REFERENCES account (account_id) ON DELETE CASCADE,
  service_id INT NOT NULL REFERENCES service (service_id) ON DELETE CASCADE,
  connection_time TIMESTAMP NOT NULL,
  UNIQUE (account_id, service_id, connection_time)
);


CREATE TABLE operation (
  operation_id SERIAL PRIMARY KEY,
  operation_time TIMESTAMP NOT NULL,
  account_id INT NOT NULL REFERENCES account (account_id) ON DELETE CASCADE,
  "type" OPERATION_TYPE NOT NULL,
  money_amount NUMERIC,
  service_id INT REFERENCES service (service_id) ON DELETE CASCADE,
  UNIQUE (operation_time, account_id, service_id),
  CHECK ( ("type" IN ('SERVICE_CONNECT','SERVICE_SUBSCRIPTION','SERVICE_DISCONNECT')) = (service_id IS NOT NULL) )
);

-- -- -- TRIGGERS -- -- --
-- account --

CREATE FUNCTION check_account_status() RETURNS trigger AS $$
    BEGIN
        IF (TG_OP = 'INSERT' AND NEW.status <> 'ACTIVE') THEN
            RAISE 'Невозможно создать новый счёт, статус которого %', NEW.status;
        END IF;

        IF (TG_OP = 'UPDATE' AND OLD.status = 'CLOSED') THEN
            RAISE 'Невозможно произвести действия с закрытым счётом';
        END IF;

        RETURN NEW;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER account_manipulation
    BEFORE INSERT OR UPDATE OF status ON account
    FOR EACH ROW
    EXECUTE FUNCTION check_account_status();

CREATE FUNCTION log_account_status() RETURNS trigger AS $$
    BEGIN
        IF (TG_OP = 'INSERT') THEN
            INSERT INTO operation VALUES
                (DEFAULT, NEW.creation_time, NEW.account_id, 'ACCOUNT_OPEN', NULL, NULL);
        ELSIF (NEW.status != OLD.status) THEN
            IF (NEW.status = 'BLOCKED') THEN
                INSERT INTO operation VALUES
                    (DEFAULT, NOW(), NEW.account_id, 'ACCOUNT_BLOCK', NULL, NULL);
            ELSIF (NEW.status = 'CLOSED') THEN
                INSERT INTO operation VALUES
                    (DEFAULT, NOW(), NEW.account_id, 'ACCOUNT_CLOSE', NULL, NULL);
            ELSIF (NEW.status = 'ACTIVE') THEN
                INSERT INTO operation VALUES
                    (DEFAULT, NOW(), NEW.account_id, 'ACCOUNT_UNBLOCK', NULL, NULL);
             END IF;
        END IF;

        RETURN NULL;
    END;
$$ LANGUAGE plpgsql;

CREATE FUNCTION log_account_balance() RETURNS trigger AS $$
    BEGIN
        IF (TG_OP = 'INSERT' AND NEW.balance <> 0) THEN
            INSERT INTO operation VALUES
                (DEFAULT, NEW.creation_time, NEW.account_id, 'TOPUP', NEW.balance, NULL);
        END IF;

        IF (TG_OP = 'UPDATE' AND NEW.balance - OLD.balance > 0) THEN
            INSERT INTO operation VALUES
                (DEFAULT, NOW(), NEW.account_id, 'TOPUP', NEW.balance - OLD.balance, NULL);
        END IF;

        IF (NEW.balance < -NEW.credit_max) THEN
            UPDATE account SET status = 'BLOCKED' WHERE account_id = NEW.account_id;
        END IF;

        RETURN NULL;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER account_manipulation_1_status
    AFTER INSERT OR UPDATE OF status ON account
    FOR EACH ROW
    EXECUTE FUNCTION log_account_status();

CREATE TRIGGER account_manipulation_2_balance
    AFTER INSERT OR UPDATE OF balance ON account
    FOR EACH ROW
    EXECUTE FUNCTION log_account_balance();

-- connected services --

CREATE FUNCTION log_service_connection() RETURNS trigger AS $$
    BEGIN
        IF (TG_OP = 'INSERT') THEN
            INSERT INTO operation VALUES
                (DEFAULT, NEW.connection_time, NEW.account_id, 'SERVICE_CONNECT', (SELECT activation_fee FROM service WHERE service_id = NEW.service_id), NEW.service_id);
            UPDATE account SET balance = balance - (SELECT activation_fee FROM service WHERE service_id = NEW.service_id) WHERE account_id = NEW.account_id;
        END IF;

        IF (TG_OP = 'DELETE') THEN
            INSERT INTO operation VALUES
                (DEFAULT, NOW(), OLD.account_id, 'SERVICE_DISCONNECT', (SELECT deactivation_fee FROM service WHERE service_id = OLD.service_id), OLD.service_id);
            UPDATE account SET balance = balance - (SELECT deactivation_fee FROM service WHERE service_id = OLD.service_id) WHERE account_id = OLD.account_id;
        END IF;

        RETURN OLD;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER service_connection
    AFTER INSERT OR DELETE ON connected_service
    FOR EACH ROW
    EXECUTE FUNCTION log_service_connection();