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
  org_id    BIGINT        NOT NULL  COMMENT 'Уникальный идентификатор организации',
  name      VARCHAR(50)             COMMENT 'Название офиса',
  address   VARCHAR(255)            COMMENT 'Адрес офиса',
  phone     VARCHAR(12)             COMMENT 'Номер телефона офиса',
);
COMMENT ON TABLE Office IS 'Офис';

CREATE TABLE IF NOT EXISTS User (
  id                BIGINT        NOT NULL      COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
  version           INTEGER       NOT NULL      COMMENT 'Служебное поле Hibernate',
  office_id         BIGINT                      COMMENT 'Универсальный идентификатор офиса',
  first_name        VARCHAR(50)   NOT NULL      COMMENT 'Имя',
  second_name       VARCHAR(50)                 COMMENT 'Фамилия',
  middle_name       VARCHAR(50)                 COMMENT 'Отчество',
  position          VARCHAR(100)  NOT NULL      COMMENT 'Должность',
  citizenship_id    BIGINT                      COMMENT 'Код гражданства',
  phone             VARCHAR(12)                 COMMENT 'Номер телефона работника',
  is_identified     BOOLEAN                     COMMENT 'Действителен?'
);
COMMENT ON TABLE User IS 'Работник';

CREATE TABLE IF NOT EXISTS Doc_user (
  id          BIGINT      NOT NULL  COMMENT 'Идентификатор работника' PRIMARY KEY ,
  version     INTEGER     NOT NULL  COMMENT 'Служебное поле Hibernate',
  doc_id      BIGINT                COMMENT 'Код удостоверения',
  doc_date    DATE                  COMMENT 'Дата регистрации удостверения',
  doc_number  VARCHAR(20)           COMMENT 'Номер удостоверения',
);
COMMENT ON TABLE Doc_user IS 'Документ конкретного работника';

CREATE TABLE IF NOT EXISTS Doc_type (
  id       BIGINT      NOT NULL COMMENT 'Ид документа' PRIMARY KEY,
  version  INTEGER     NOT NULL COMMENT 'Служебное поле Hibernate',
  doc_code BIGINT      NOT NULL COMMENT 'Код документа',
  doc_name VARCHAR(50) NOT NULL COMMENT 'Наименомание удовстверения'
);
COMMENT ON TABLE Doc_type IS 'Справочник удовстверений';

CREATE TABLE IF NOT EXISTS Country (
  id               BIGINT      NOT NULL COMMENT 'Ид гражданства' PRIMARY KEY ,
  version          INTEGER     NOT NULL COMMENT 'Служебное поле Hibernate',
  citizenship_code BIGINT      NOT NULL COMMENT 'Код гражданства' ,
  citizenship_name VARCHAR(50) NOT NULL COMMENT 'Наименомание гражданства'
);
COMMENT ON TABLE Country IS 'Справочник гражданств';

CREATE INDEX IX_Office_Organization_Id ON Office (org_id);
ALTER TABLE Office ADD FOREIGN KEY (org_id) REFERENCES Organization(id);

CREATE INDEX IX_User_Office_Id ON User (office_id);
ALTER TABLE User ADD FOREIGN KEY (office_id) REFERENCES Office(id);

CREATE INDEX IX_User_Country_Id ON User (citizenship_id);
ALTER TABLE User ADD FOREIGN KEY (citizenship_id) REFERENCES Country(id);

CREATE INDEX IX_Doc_user_Doc_type_Id ON Doc_user (doc_id);
ALTER TABLE Doc_user ADD FOREIGN KEY (doc_id) REFERENCES Doc_type(id);

CREATE INDEX IX_User_Doc_user_Id ON User (id);
ALTER TABLE User ADD FOREIGN KEY (id) REFERENCES Doc_user(id);