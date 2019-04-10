CREATE TABLE IF NOT EXISTS Organization (
  id        BIGINT NOT NULL         COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
  version   INTEGER NOT NULL        COMMENT 'Служебное поле Hibernate',
  name      VARCHAR(50) NOT NULL    COMMENT 'Название организации',
  full_name VARCHAR(255) NOT NULL   COMMENT 'Полное название организации',
  inn       VARCHAR(10) NOT NULL    COMMENT 'Номер ИНН',
  kpp       VARCHAR(10) NOT NULL    COMMENT 'Номер КПП',
  address   VARCHAR(255) NOT NULL   COMMENT 'Адрес организации',
  phone     VARCHAR(12)             COMMENT 'Номер телефона организации',
  is_active BOOLEAN                 COMMENT 'Действует организция?'
);
COMMENT ON TABLE Organization IS 'Организация';

CREATE TABLE IF NOT EXISTS Office (
  id        BIGINT        NOT NULL  COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
  version   INTEGER       NOT NULL  COMMENT 'Служебное поле Hibernate',
  org_id    INTEGER       NOT NULL  COMMENT 'Уникальный идентификатор организации',
  name      VARCHAR(50)             COMMENT 'Название офиса',
  address   VARCHAR(250)            COMMENT 'Адрес офиса',
  phone     VARCHAR(12)             COMMENT 'Номер телефона офиса',
);
COMMENT ON TABLE Office IS 'Офис';

CREATE TABLE IF NOT EXISTS User (
  id                BIGINT        NOT NULL      COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
  version           INTEGER       NOT NULL      COMMENT 'Служебное поле Hibernate',
  office_id         INTEGER                     COMMENT 'Универсальный идентификатор офиса',
  first_name        VARCHAR(50)   NOT NULL      COMMENT 'Имя',
  second_name       VARCHAR(50)                 COMMENT 'Фамилия',
  middle_name       VARCHAR(50)                 COMMENT 'Отчество',
  position          VARCHAR(100)  NOT NULL      COMMENT 'Должность',
  doc_code          BIGINT                      COMMENT 'Код удостоверения',
  doc_date          DATE                        COMMENT 'Дата регистрации удостверения',
  doc_number        VARCHAR(250)                COMMENT 'Номер удостоверения',
  citizenship_code  BIGINT                      COMMENT 'Код гражданства',
  phone             VARCHAR(12)                 COMMENT 'Номер телефона работника',
  is_identified     BOOLEAN                     COMMENT 'Идантифицирован?'
);
COMMENT ON TABLE User IS 'Работник';

CREATE TABLE IF NOT EXISTS Doc (
  doc_code BIGINT     NOT NULL COMMENT 'Код документа' PRIMARY KEY,
  version  INTEGER     NOT NULL COMMENT 'Служебное поле Hibernate',
  doc_name VARCHAR(50) NOT NULL COMMENT 'Наименомание удовстверения'
);
COMMENT ON TABLE Doc IS 'Справочник удовстверений';

CREATE TABLE IF NOT EXISTS Country (
  citizenship_code BIGINT     NOT NULL COMMENT 'Код гражданства' PRIMARY KEY ,
  version          INTEGER     NOT NULL COMMENT 'Служебное поле Hibernate',
  citizenship_name VARCHAR(50) NOT NULL COMMENT 'Наименомание гражданства'
);
COMMENT ON TABLE Country IS 'Справочник гражданств';

CREATE INDEX IX_User_Docs_Id ON User (doc_code, citizenship_code);
ALTER TABLE User ADD FOREIGN KEY (doc_code) REFERENCES Doc(doc_code);
ALTER TABLE User ADD FOREIGN KEY (citizenship_code) REFERENCES Country(citizenship_code);

CREATE INDEX IX_User_Office_Id ON User (office_id);
ALTER TABLE User ADD FOREIGN KEY (office_id) REFERENCES Office(id);

CREATE INDEX IX_Office_Organization_Id ON Office (org_id);
ALTER TABLE Office ADD FOREIGN KEY (org_id) REFERENCES Organization(id);