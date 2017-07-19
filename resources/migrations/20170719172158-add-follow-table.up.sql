CREATE TABLE IF NOT EXISTS `clotter`.`follow` (
  `from_id` VARCHAR(20) NOT NULL,
  `to_id` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`from_id`, `to_id`),
  INDEX `fk_follow_user2_idx` (`to_id` ASC),
  CONSTRAINT `fk_follow_user1`
    FOREIGN KEY (`from_id`)
    REFERENCES `clotter`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_follow_user2`
    FOREIGN KEY (`to_id`)
    REFERENCES `clotter`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
