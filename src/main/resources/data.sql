-- Справочник №1
INSERT INTO Doc_type (id, version, doc_code, doc_name) VALUES (1, 0, 433, 'Passport');
INSERT INTO Doc_type (id, version, doc_code, doc_name) VALUES (2, 0, 24, 'Military ID');

-- Справочник №2
INSERT INTO Country (id, version, citizenship_code, citizenship_name) VALUES (1, 0, 64, 'USA');
INSERT INTO Country (id, version, citizenship_code, citizenship_name) VALUES (2, 0, 77, 'Japan');

-- Организация №1
INSERT INTO Organization (id, version, name,  full_name, inn, kpp, address, phone, is_active)
VALUES (1, 0, 'Microsoft', 'Microsoft Corporation', '6449013711', '644901001', 'USA', '12121234567', true);

-- Офис №1
INSERT INTO Office (id, version, org_id, name, address, phone, is_active)
VALUES (1, 0, 1, 'Microsoft Washington', 'Washington 12', '12126458365', true);

--Документ работника №1
INSERT INTO Doc_user(id, version, doc_id, doc_date, doc_number)
VALUES (1, 0, 1, '2015-09-28', '404426');

-- Работник №1
INSERT INTO User (id, version, office_id, first_name, second_name, middle_name, doc_user_id,
                  citizenship_id, position, phone, is_identified)
VALUES (1, 0, 1, 'Ivan', 'Ivanov', 'Ivanovich', 1, 1, 'Manager', '12125463456', true);

-- Офис №2
INSERT INTO Office (id, version, org_id,  name, address, phone, is_active)
VALUES (2, 0, 1, 'Microsoft New York', 'New York 34', '12127265432', true);

--Документ работника №2
INSERT INTO Doc_user(id, version, doc_id, doc_date, doc_number)
VALUES (2, 0,  1, '2016-07-18', '434141');

-- Работник №2
INSERT INTO User (id, version, office_id, first_name, second_name, middle_name, doc_user_id,
                  citizenship_id, position, phone, is_identified)
VALUES (2, 0, 2, 'Max', 'Smirnov', 'Alekseevich', 2, 2, 'Consultant', '12125463456', true);

-- Организация №2
INSERT INTO Organization (id, version, name,  full_name, inn, kpp, address, phone, is_active)
VALUES (2, 0, 'Sony', 'Sony Corporation', '7722679627', '772201001', 'Japan', '31234567', false);

-- Офис №3
INSERT INTO Office (id, version, org_id,  name, address, phone, is_active)
VALUES (3, 0, 2, 'Sony Tokio', 'Tokio 112', '3127354', true);

--Документ работника №3
INSERT INTO Doc_user(id, version, doc_id, doc_date, doc_number)
VALUES (3, 0,  2, '2013-11-14', '394426');

-- Работник №3
INSERT INTO User (id, version, office_id, first_name, second_name, middle_name, doc_user_id,
                  citizenship_id, position, phone)
VALUES (3, 0, 3, 'Anastasia', 'Titova', 'Vassilievna', 3, 1, 'Java Developer', '3127355');

-- Офис №4
INSERT INTO Office (id, version, org_id,  name, address, phone, is_active)
VALUES (4, 0, 2, 'Sony New York', 'New York 36', '12126458365', false);

--Документ работника №4
INSERT INTO Doc_user(id, version, doc_id, doc_date, doc_number)
VALUES (4, 0,  2, '2013-12-28', '414356');

-- Работник №4
INSERT INTO User (id, version, office_id, first_name, second_name, doc_user_id,
                  citizenship_id, position, phone)
VALUES (4, 0, 3, 'Aleksandr', 'Kafidov', 4, 2, 'UI', '12126458367');