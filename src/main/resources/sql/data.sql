INSERT INTO individual_client (passport, surname, name, patronymic, region, contact_phone_number, email) VALUES
    ('4093719687', 'Каширин', 'Алексей', 'Евгеньевич', 'Волгоградская область', '+77480144231', 'yufe-gikucu62@aol.com'),
    ('4516539143', 'Киреев', 'Иван', 'Макарович', 'г. Москва', '+71149956344', 'mutoper-ime79@yandex.ru'),
    ('4035759570', 'Гуляева', 'Ксения', 'Даниловна', 'респ. Татарстан', '+74997714077', 'geentbbt94@yahoo.com'),
    ('4744670739', 'Осипов', 'Александр', 'Макарович', 'Ленинградская область', '+74160882578', NULL),
    ('4877458517', 'Ковалева', 'Алёна', 'Платоновна', 'г. Москва', '+76538282767', 'roli_bayiko73@mail.ru');

INSERT INTO entity_client (registration_number, name, region, address, contact_surname, contact_name, contact_patronymic, contact_phone_number, email) VALUES
('7081321623541', 'АО "СТНГ"', 'Краснодарский край',
    '354340, КРАСНОДАРСКИЙ КРАЙ, ПГТ. СИРИУС, ПР-КТ ОЛИМПИЙСКИЙ, Д. 40',
    'Козлов', 'Максим', 'Маратович', '+74957756978', 'fetum-iwegu55@yandex.ru'),
('5040841397328', 'ООО "ТПС"', 'Ленинградская область',
    '188304, Ленинградская область, Г. ГАТЧИНА, УЛ. ЧКАЛОВА, Д.7',
    'Зайцева', 'Маргарита', 'Тимофеевна', '+74953206081', 'rufu_gixuke92@mail.ru'),
('4118464792530', 'ЗАО "ВЯТКТВ"', 'Тюменская область',
    '627753, ТЮМЕНСКАЯ ОБЛАСТЬ, Г. ИШИМ, УЛ. ШАРОНОВА, Д. 5, К. Б, КВ. 2',
    'Миронов', 'Артём', 'Артёмович', '+74954575386', 'vetayit-ido57@bk.ru'),
('8080650538818', 'ЗАО НПП "СИНТЕЗ"', 'Калужская область',
    '249031, КАЛУЖСКАЯ ОБЛАСТЬ, Г ОБНИНСК, УЛ КУРЧАТОВА, Д. 21',
    'Тихомиров', 'Платон', 'Сергеевич', '+74958493612', 'rajic_ewehu98@yahoo.com'),
('6053213748440', 'ЧУ ДО "ОНЛАЙН-ШКОЛА ПОДГОТОВКИ К ЭКЗАМЕНАМ "УМНАЯ ШКОЛА"', 'г. Москва',
    '109012, Г.МОСКВА, ВНУТРИГОРОДСКАЯ ТЕРРИТОРИЯ ГОРОДА ФЕДЕРАЛЬНОГО ЗНАЧЕНИЯ МУНИЦИПАЛЬНЫЙ ОКРУГ ТАГАНСКИЙ, ПРОЕЗД КИТАЙГОРОДСКИЙ, Д. 7, СТР. 1',
    'Аксенова', 'Виктория', 'Максимовна', '+74955066010', 'zakig-ipeje28@bk.ru');

--('', '', '', '', '', '', '', '')


INSERT INTO service (name, subscription_type, activation_fee, subscription_fee, deactivation_fee, duration, package_phone, package_internet, package_message, description) VALUES
('Тариф "Выгодный"', 'MONTHLY', '10', '580', '10', NULL, '1000', '65', '300', ''),
('"Больше интернета"', 'ONETIME', '150', NULL, NULL, NULL, NULL, '20', NULL, 'Дополнительный пакет интернета'),
('Музыка', 'DAILY', '0', '8', '0', NULL, NULL, NULL, NULL, 'Подключение к музыкальному стриминговому сервису от Yandex');

INSERT INTO account (individual_client_id, entity_client_id, creation_time, balance, serviced_phone_number, status, credit_max, credit_interval) VALUES
('3', NULL, '2014-07-02 23:49:55', '87', '+79824287044', 'ACTIVE', '1000', NULL),
('1', NULL, '2018-01-28 16:31:47', '45', '+79827996472', 'ACTIVE', '100', NULL),
('3', NULL, '2019-10-18 00:46:09', '0', '+79829846190', 'ACTIVE', '100', NULL),  -- зак
(NULL, '2', '2021-03-16 19:51:59', '0', '+79823448263', 'ACTIVE', '100', NULL),  -- зак
('5', NULL, '2022-10-07 08:55:45', '0', '+79828325935', 'ACTIVE', '500', NULL), -- -50
(NULL, '5', '2022-12-22 15:43:50', '1000', '+79821702152', 'ACTIVE', '100', NULL),
(NULL, '4', '2023-08-20 09:11:07', '0', '+79825886899', 'ACTIVE', '50', NULL);  -- заб -100

UPDATE account SET status = 'CLOSED' WHERE account_id IN (3, 4);
UPDATE account SET balance = balance - 50 WHERE account_id = 5;
UPDATE account SET balance = balance - 100 WHERE account_id = 7;


INSERT INTO connected_service (account_id, service_id, connection_time) VALUES
('6', '2', NOW()),
('5', '3', NOW()),
('1', '1', NOW());

DELETE FROM connected_service WHERE connection_id = 3;