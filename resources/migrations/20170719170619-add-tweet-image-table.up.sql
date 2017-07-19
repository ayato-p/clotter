CREATE TABLE IF NOT EXISTS `clotter`.`tweet_image` (
  `tweet_id` INT UNSIGNED NOT NULL,
  `image` VARCHAR(255) NOT NULL COMMENT 'base64化した画像',
  `user_id` VARCHAR(20) NOT NULL,
  INDEX `fk_tweet_image_tweet1_idx` (`tweet_id` ASC),
  PRIMARY KEY (`tweet_id`, `user_id`),
  INDEX `fk_tweet_image_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_tweet_image_tweet1`
    FOREIGN KEY (`tweet_id`)
    REFERENCES `clotter`.`tweet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tweet_image_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `clotter`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
