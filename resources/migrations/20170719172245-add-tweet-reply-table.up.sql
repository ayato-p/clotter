CREATE TABLE IF NOT EXISTS `clotter`.`tweet_reply` (
  `to_id` INT UNSIGNED NOT NULL,
  `from_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`to_id`, `from_id`),
  INDEX `fk_tweet_reply_tweet2_idx` (`to_id` ASC),
  CONSTRAINT `fk_tweet_reply_tweet1`
    FOREIGN KEY (`from_id`)
    REFERENCES `clotter`.`tweet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tweet_reply_tweet2`
    FOREIGN KEY (`to_id`)
    REFERENCES `clotter`.`tweet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
