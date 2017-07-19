CREATE TABLE IF NOT EXISTS `clotter`.`tweet_at` (
  `tweet_id` INT UNSIGNED NOT NULL,
  `to_id` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`tweet_id`, `to_id`),
  INDEX `fk_tweet_at_user1_idx` (`to_id` ASC),
  CONSTRAINT `fk_tweet_at_tweet1`
    FOREIGN KEY (`tweet_id`)
    REFERENCES `clotter`.`tweet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tweet_at_user1`
    FOREIGN KEY (`to_id`)
    REFERENCES `clotter`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
