CREATE TABLE `user` (
  `id`   BIGINT(22) NOT NULL AUTO_INCREMENT
  COMMENT '用户id',
  `name` VARCHAR(255)        DEFAULT NULL
  COMMENT '用户姓名',
  `age`  INT(5)              DEFAULT '0'
  COMMENT '用户年龄',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;