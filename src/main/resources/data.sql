-- Справочник №1
INSERT INTO Doc (doc_code, version, doc_name) VALUES (433, 0, 'Passport');
INSERT INTO Doc (doc_code, version, doc_name) VALUES (24, 0, 'Military ID');

-- Справочник №2
INSERT INTO Country (citizenship_code, version, citizenship_name) VALUES (64, 0, 'USA');
INSERT INTO Country (citizenship_code, version, citizenship_name) VALUES (77, 0, 'Japan');

-- Организация №1
INSERT INTO Organization (id, version, name,  full_name, inn, kpp, address, phone)
VALUES (1, 0, 'Microsoft', 'Microsoft Corporation', '6449013711', '644901001', 'USA', '12121234567');

-- Офис №1
INSERT INTO Office (id, version, org_id,  name, address, phone)
VALUES (1, 0, 1, 'Microsoft Washington', 'Washington 12', '12126458365');

-- Работник №1
INSERT INTO User (id, version, office_id, first_name, second_name, middle_name,
                  citizenship_code, doc_code, doc_date, doc_number, position, phone)
VALUES (1, 0, 1, 'Ivan', 'Ivanov', 'Ivanovich', 64, 433, '2015-09-28', 404426, 'Manager', '12125463456');

-- Офис №2
INSERT INTO Office (id, version, org_id,  name, address, phone)
VALUES (2, 0, 1, 'Microsoft New York', 'New York 34', '12127265432');

-- Работник №2
INSERT INTO User (id, version, office_id, first_name, second_name, middle_name,
                  citizenship_code, doc_code, doc_date, doc_number, position, phone)
VALUES (2, 0, 2, 'Max', 'Smirnov', 'Alekseevich', 77, 433, '2016-07-18', 434141, 'Consultant', '12125463456');

-- Организация №2
INSERT INTO Organization (id, version, name,  full_name, inn, kpp, address, phone)
VALUES (2, 0, 'Sony', 'Sony Corporation', '7722679627', '772201001', 'Japan', '31234567');

-- Офис №3
INSERT INTO Office (id, version, org_id,  name, address, phone)
VALUES (3, 0, 2, 'Sony Tokio', 'Tokio 112', '3127354');

-- Работник №3
INSERT INTO User (id, version, office_id, first_name, second_name, middle_name,
                  citizenship_code, doc_code, doc_date, doc_number, position, phone)
VALUES (3, 0, 1, 'Anastasia', 'Titova', 'Vassilievna', 64, 24, '2013-11-14', 394426, 'Java Developer', '3127355');

-- Офис №4
INSERT INTO Office (id, version, org_id,  name, address, phone)
VALUES (4, 0, 2, 'Sony New York', 'New York 36', '12126458365');

-- Работник №4
INSERT INTO User (id, version, office_id, first_name, second_name,
                  citizenship_code, doc_code, doc_date, doc_number, position, phone)
VALUES (4, 0, 2, 'Aleksandr', 'Kafidov', 77, 24, '2013-12-28', 414356, 'UI', '12126458367');