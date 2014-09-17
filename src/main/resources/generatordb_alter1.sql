SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

ALTER TABLE `generatordb`.`merit` DROP COLUMN `preconditions` ;

CREATE  TABLE IF NOT EXISTS `generatordb`.`merit_has_attribute_preconditions` (
  `id` INT(11) NOT NULL ,
  `merit_id` INT(11) NOT NULL ,
  `attribute_id` INT(11) NOT NULL ,
  `needed_value` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`, `merit_id`, `attribute_id`) ,
  INDEX `fk_merit_has_attribute_attribute1` (`attribute_id` ASC) ,
  INDEX `fk_merit_has_attribute_merit1` (`merit_id` ASC) ,
  CONSTRAINT `fk_merit_has_attribute_merit1`
    FOREIGN KEY (`merit_id` )
    REFERENCES `generatordb`.`merit` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_merit_has_attribute_attribute1`
    FOREIGN KEY (`attribute_id` )
    REFERENCES `generatordb`.`attribute` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
