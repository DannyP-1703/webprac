CREATE TABLE individual_client (
  client_id SERIAL PRIMARY KEY,
  passport CHAR[10] UNIQUE NOT NULL,
  surname VARCHAR[100] NOT NULL,
  "name" VARCHAR[100] NOT NULL,
  patronymic VARCHAR[100],
  region VARCHAR[100] NOT NULL,
  contact_phone_number CHAR[12] NOT NULL,
  email VARCHAR[100]
);

CREATE TABLE entity_client (
  client_id SERIAL PRIMARY KEY,
  registration_number CHAR[13] UNIQUE NOT NULL,
  "name" VARCHAR[100] NOT NULL,
  region VARCHAR[100] NOT NULL,
  "address" TEXT NOT NULL,
  contact_surname VARCHAR[100] NOT NULL,
  contact_name VARCHAR[100] NOT NULL,
  contact_patronymic VARCHAR[100],
  contact_phone_number CHAR[12] NOT NULL,
  email VARCHAR[100]
);

CREATE TYPE ACCOUNT_STATUS AS ENUM ('Активен', 'Заблокирован', 'Закрыт');
CREATE TYPE OPERATION_TYPE AS ENUM ('Пополнение', 'Подключение услуги', 'Абонентская плата', 'Отключение услуги');
CREATE TYPE SUBSCRIPTION_TYPE AS ENUM ('Без а/п (единовременная)', 'Ежедневно', 'Ежемесячно', 'Ежегодно');

CREATE TABLE account (
  account_id SERIAL PRIMARY KEY,
  individual_client_id INT REFERENCES individual_client (client_id) ON DELETE CASCADE,
  entity_client_id INT REFERENCES entity_client (client_id) ON DELETE CASCADE,
  creation_time timestamp NOT NULL,
  balance NUMERIC NOT NULL,
  serviced_phone_number CHAR[12],
  status ACCOUNT_STATUS NOT NULL,
  credit_max NUMERIC,
  credit_interval INTERVAL,
  CHECK ( (individual_client_id IS NULL) != (entity_client_id IS NULL) )
);

CREATE TABLE operation (
  operation_id SERIAL PRIMARY KEY,
  operation_time TIMESTAMP NOT NULL,
  account_id INT NOT NULL REFERENCES account (account_id) ON DELETE CASCADE,
  "type" OPERATION_TYPE NOT NULL,
  money_amount NUMERIC NOT NULL CHECK (money_amount >= 0),
  service_id INT REFERENCES service (service_id) ON DELETE CASCADE,
  UNIQUE (operation_time, account_id, service_id),
  CHECK ( ("type" = 'Пополнение') = (service_id IS NULL) )
);

CREATE TABLE service (
  service_id SERIAL PRIMARY KEY,
  "name" VARCHAR[100] UNIQUE NOT NULL,
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
    (subscription_type = 'Без а/п (единовременная)') OR NOT (activation_fee IS NOT NULL AND duration IS NULL) AND
    (subscription_type IN ('Ежедневно', 'Ежемесячно', 'Ежегодно')) OR NOT (subscription_fee IS NOT NULL)
  )
);

CREATE TABLE connected_service (
  connection_id SERIAL PRIMARY KEY,
  account_id INT NOT NULL REFERENCES account (account_id) ON DELETE CASCADE,
  service_id INT NOT NULL REFERENCES service (service_id) ON DELETE CASCADE,
  connection_time TIMESTAMP NOT NULL,
  disconnection_time TIMESTAMP,
  CHECK (disconnection_time IS NULL OR disconnection_time > connection_time),
  UNIQUE (account_id, service_id, connection_time)
);






