CREATE TABLE IF NOT EXISTS `clotter`.`tweet_retweet` (
  `tweet_id` INT UNSIGNED NOT NULL,
  `user_id` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`tweet_id`, `user_id`),
  INDEX `fk_tweet_retweet_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_tweet_retweet_tweet1`
    FOREIGN KEY (`tweet_id`)
    REFERENCES `clotter`.`tweet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tweet_retweet_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `clotter`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
