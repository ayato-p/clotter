CREATE TABLE IF NOT EXISTS `tweet` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_timestamp` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT 'ツイートの時間',
  `user_id` VARCHAR(20) NOT NULL,
  `content` VARCHAR(560) NOT NULL COMMENT '4バイト * 140字 = 560バイト\n',
  PRIMARY KEY (`id`),
  INDEX `fk_TWEET_USER_idx` (`user_id` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `created_datetime` (`created_timestamp` ASC),
  CONSTRAINT `fk_TWEET_USER`
    FOREIGN KEY (`user_id`)
    REFERENCES `clotter`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
