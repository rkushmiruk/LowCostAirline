SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `user_role`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `user` (
  `id`         INT(11)     NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(20) NOT NULL,
  `last_name`  VARCHAR(20) NOT NULL,
  `login`      VARCHAR(20) NOT NULL UNIQUE,
  `password`   VARCHAR(20) NOT NULL,
  `e-mail`     VARCHAR(20) NOT NULL UNIQUE,
  `role_id`       INT(11)     NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT user_role_fk FOREIGN KEY (role_id) REFERENCES user_role (id)
);

CREATE TABLE `user_role` (
  `id`   INT(11)     NOT NULL,
  `role` VARCHAR(20) NOT NULL
);

