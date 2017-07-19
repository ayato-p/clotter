CREATE TABLE IF NOT EXISTS `clotter`.`tweet_favorite` (
  `user_id` VARCHAR(20) NOT NULL,
  `tweet_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`, `tweet_id`),
  INDEX `fk_tweet_favorite_tweet1_idx` (`tweet_id` ASC),
  CONSTRAINT `fk_tweet_favorite_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `clotter`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tweet_favorite_tweet1`
    FOREIGN KEY (`tweet_id`)
    REFERENCES `clotter`.`tweet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
