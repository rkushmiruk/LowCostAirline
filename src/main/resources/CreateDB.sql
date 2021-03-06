SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `user_role`;
DROP TABLE IF EXISTS `user_authentication`;
DROP TABLE IF EXISTS `ticket_order`;
DROP TABLE IF EXISTS `ticket`;
DROP TABLE IF EXISTS `ticket_status`;
DROP TABLE IF EXISTS `extra_price`;
DROP TABLE IF EXISTS `baggage_type`;
DROP TABLE IF EXISTS `flight`;
DROP TABLE IF EXISTS `country`;
DROP TABLE IF EXISTS `city`;
DROP TABLE IF EXISTS `airport`;
SET FOREIGN_KEY_CHECKS = 1;


CREATE TABLE `user` (
  `id`         INT(11)      NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50)  NOT NULL,
  `last_name`  VARCHAR(50)  NOT NULL,
  `email`      VARCHAR(255) NOT NULL UNIQUE,
  `role_id`    INT(11)      NOT NULL,
  `auth_id`    INT(11)      NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `user_role` (
  `id`   INT(11)     NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(50) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
);

CREATE TABLE `user_authentication` (
  `id`       INT(11)      NOT NULL AUTO_INCREMENT,
  `login`    VARCHAR(255) NOT NULL UNIQUE,
  `password` VARCHAR(50)  NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `ticket_order` (
  `id`          INT(11)      NOT NULL AUTO_INCREMENT,
  `user_id`     INT(11),
  `email`       VARCHAR(255) NOT NULL,
  `datetime`    DATETIME default current_timestamp,
  PRIMARY KEY (`id`)
);

CREATE TABLE `ticket` (
  `id`                        INT(11) NOT NULL AUTO_INCREMENT,
  `order_id`                  INT(11) NOT NULL ,
  `flight_id`                 INT(11) NOT NULL,
  `status_id`                 INT(11) NOT NULL,
  `extra_price_id`            INT(11)  NOT NULL,
  `baggage_id`				INT(11) NOT NULL,
  `passenger_first_name`      VARCHAR(50) NOT NULL,
  `passenger_last_name`       VARCHAR(50) NOT NULL,
  `passenger_email`           VARCHAR(255) NOT NULL ,
  `has_priority_registration` BOOLEAN          DEFAULT FALSE,
  `has_baggage`               BOOLEAN          DEFAULT FALSE,
  `price`                     INT(11) NOT NULL,
  `seat_number`               INT(11) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE `ticket_status` (
  `id`     INT(11)     NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(50) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
);

CREATE TABLE `flight` (
  `id`                     INT(11)  NOT NULL AUTO_INCREMENT,
  `departure_airport_id`   INT(11)  NOT NULL,
  `destination_airport_id` INT(11)  NOT NULL,
  `departure_datetime`     DATETIME NOT NULL,
  `destination_datetime`   DATETIME,
  `flight_time`            INT(11)  NOT NULL,
  `total_seat_number`      INT(11)  NOT NULL,
  `start_price`  INT(11) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `extra_price` (
  `id`                          INT(11)  NOT NULL AUTO_INCREMENT,
  `priority_registration_price` INT(11)  NOT NULL,
  `days_before_flight`           INT(11) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `baggage_type` (
  `id`        INT(11) NOT NULL AUTO_INCREMENT,
  `type`   VARCHAR(50) NOT NULL,
  `price`     INT(11)     NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `country` (
  `id`   INT(11)     NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
);

CREATE TABLE `city` (
  `id`         INT(11)     NOT NULL AUTO_INCREMENT,
  `country_id` INT(11)     NOT NULL,
  `name`       VARCHAR(50) NOT NULL UNIQUE,
  `time_zone`  INT(11)     NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `airport` (
  `id`      INT(11)      NOT NULL AUTO_INCREMENT,
  `city_id` INT(11)      NOT NULL,
  `name`    VARCHAR(255) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
);

ALTER TABLE `user_authentication`
  ADD UNIQUE INDEX `user_unique_login_idx` (`login`);

ALTER TABLE `user`
  ADD CONSTRAINT `user_role_fk` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `user`
  ADD CONSTRAINT `user_auth_fk` FOREIGN KEY (`auth_id`) REFERENCES `user_authentication` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ticket_order`
  ADD CONSTRAINT `ticket_order_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_order_fk` FOREIGN KEY (`order_id`) REFERENCES `ticket_order` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_flight_fk` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_status_fk` FOREIGN KEY (`status_id`) REFERENCES `ticket_status` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_extra_price_fk` FOREIGN KEY (`extra_price_id`) REFERENCES `extra_price` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_baggage_fk` FOREIGN KEY (`baggage_id`) REFERENCES `baggage_type` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `flight`
  ADD CONSTRAINT `flight_departure_airport_fk` FOREIGN KEY (`departure_airport_id`) REFERENCES `airport` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `flight`
  ADD CONSTRAINT `flight_destination_airport_fk` FOREIGN KEY (`destination_airport_id`) REFERENCES `airport` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `city`
  ADD CONSTRAINT `city_country_fk` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `airport`
  ADD CONSTRAINT `airport_city_fk` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `user`
  AUTO_INCREMENT 1;
ALTER TABLE `user_role`
  AUTO_INCREMENT 1;
ALTER TABLE `ticket`
  AUTO_INCREMENT 1;
ALTER TABLE `ticket_order`
  AUTO_INCREMENT 1;
ALTER TABLE `ticket_status`
  AUTO_INCREMENT 1;
ALTER TABLE `flight`
  AUTO_INCREMENT 1;
ALTER TABLE `extra_price`
  AUTO_INCREMENT 1;
ALTER TABLE `baggage_type`
  AUTO_INCREMENT 1;
ALTER TABLE `country`
  AUTO_INCREMENT 1;
ALTER TABLE `city`
  AUTO_INCREMENT 1;
ALTER TABLE `airport`
  AUTO_INCREMENT 1;


INSERT INTO `user_role` (role) VALUES
  ('admin'),
  ('user');

INSERT INTO `user_authentication` (login, password) VALUES
  ('Roma','111'),
  ('Ivan','222'),
  ('Evgenia','333'),
  ('James','444'),
  ('Petr','555');


INSERT INTO `user` (first_name, last_name, email, role_id, auth_id) VALUES
('Roman', 'Kushmiruk', 'rKushmiruk@gmail.com', 1, 1),
('Ivan', 'Ivanov', 'Ivanov@gmail.com', 2,2),
('Evgeniya', 'Ermolaeva', 'Eva@gmail.com', 2,3),
('James', 'Bond', 'Bond@gmail.com', 2,4),
('Petr', 'Petrov', 'petrov@gmail.com', 2,5);

INSERT INTO `ticket_status` (status) VALUES
  ('OPENED'),
  ('BLOCKED'),
  ('PAID');

INSERT INTO `country` (name) VALUES
  ('Ukraine'),
  ('Belarus'),
  ('United Kingdom'),
  ('France'),
  ('USA'),
  ('Germany'),
  ('Italy');

INSERT INTO `city` (country_id, name, time_zone) VALUES
  (1, 'Kiev', 2),
  (2, 'Minsk', 3),
  (3, 'London', 0),
  (4, 'Paris', 1),
  (5, 'New-York', -5),
  (6, 'Berlin', 1),
  (7, 'Rome', 1);

INSERT INTO `airport` (city_id, name) VALUES
  (1, 'Boryspol Airport'),
  (2, 'Minsk Airport'),
  (3, 'Heathrow Airport'),
  (3, 'London City Airport'),
  (4, 'Paris-Charles-de-Gaulle Airport'),
  (4, 'Paris-Orly a]Airport'),
  (5, 'John F. Kennedy International Airport'),
  (5, 'LaGuardia Airport'),
  (6, 'Berlin Tegel Airport'),
  (7, 'Fiumincino Airport');


INSERT INTO `flight` (departure_airport_id, destination_airport_id, departure_datetime, flight_time, total_seat_number,start_price)
VALUES
  (1, 2, '2017-07-01 07:30', 140, 150,30),
  (1, 2, '2017-07-01 08:30', 140, 350,40),
  (2, 1, '2017-07-02 09:00', 140, 150,30),
  (1, 3, '2017-07-03 09:00', 300, 150,30),
  (3, 1, '2017-07-04 09:00', 300, 150,30),
  (2, 4, '2017-07-05 09:00', 280, 150,30),
  (2, 5, '2017-07-04 09:00', 280, 150,30),
  (3, 5, '2017-07-04 09:00', 180, 150,30),
  (3, 6, '2017-07-06 09:00', 240, 150,40),
  (6, 1, '2017-07-05 09:00', 250, 150,50),
  (6, 2, '2017-07-02 09:00', 260, 150,50),
  (1, 7, '2017-07-01 09:00', 210, 150,30);
  

  
  
  
