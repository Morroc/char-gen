SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `generatordb` ;
CREATE SCHEMA IF NOT EXISTS `generatordb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `generatordb` ;

-- -----------------------------------------------------
-- Table `generatordb`.`race`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `generatordb`.`race` ;

CREATE  TABLE IF NOT EXISTS `generatordb`.`race` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `maxage` INT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `generatordb`.`personage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `generatordb`.`personage` ;

CREATE  TABLE IF NOT EXISTS `generatordb`.`personage` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `age` INT NULL ,
  `race_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `race_id`) ,
  INDEX `fk_personage_race1` (`race_id` ASC) ,
  CONSTRAINT `fk_personage_race1`
    FOREIGN KEY (`race_id` )
    REFERENCES `generatordb`.`race` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `generatordb`.`attached_skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `generatordb`.`attached_skill` ;

CREATE  TABLE IF NOT EXISTS `generatordb`.`attached_skill` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `basecost` INT NULL ,
  `default` TINYINT(1) NULL ,
  `difficult` TINYINT(1) NULL ,
  `theoretical` TINYINT(1) NULL ,
  `acquiring_cost` INT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `generatordb`.`trigger_skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `generatordb`.`trigger_skill` ;

CREATE  TABLE IF NOT EXISTS `generatordb`.`trigger_skill` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `type` VARCHAR(45) NULL ,
  `non_generating_cost_coefficient` DOUBLE NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `generatordb`.`attribute`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `generatordb`.`attribute` ;

CREATE  TABLE IF NOT EXISTS `generatordb`.`attribute` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `cost` INT NULL ,
  `max_value` INT NULL ,
  `min_value` INT NULL ,
  `from_1_to_3_non_generating_cost` INT NULL ,
  `cost_rise_coefficient` DOUBLE NULL ,
  `non_generating_rise_coefficient` DOUBLE NULL ,
  `action_level_bonus` TEXT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `generatordb`.`birth_merit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `generatordb`.`birth_merit` ;

CREATE  TABLE IF NOT EXISTS `generatordb`.`birth_merit` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `probability` DOUBLE NULL ,
  `cost` INT NULL ,
  `description` TEXT NULL ,
  `action_bonus` TEXT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `generatordb`.`merit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `generatordb`.`merit` ;

CREATE  TABLE IF NOT EXISTS `generatordb`.`merit` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `cost` INT NULL ,
  `description` TEXT NULL ,
  `action_bonus` TEXT NULL ,
  `preconditions` TEXT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `generatordb`.`flaw`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `generatordb`.`flaw` ;

CREATE  TABLE IF NOT EXISTS `generatordb`.`flaw` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `cost` INT NULL ,
  `description` TEXT NULL ,
  `turn_off_preconditions` TEXT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `generatordb`.`race_has_merit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `generatordb`.`race_has_merit` ;

CREATE  TABLE IF NOT EXISTS `generatordb`.`race_has_merit` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `merit_id` INT NOT NULL ,
  `race_id` INT NOT NULL ,
  `default_for_race` TINYINT(1) NULL ,
  PRIMARY KEY (`id`, `merit_id`, `race_id`) ,
  INDEX `fk_race_has_merit_merit1` (`merit_id` ASC) ,
  INDEX `fk_race_has_merit_race1` (`race_id` ASC) ,
  CONSTRAINT `fk_race_has_merit_race1`
    FOREIGN KEY (`race_id` )
    REFERENCES `generatordb`.`race` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_race_has_merit_merit1`
    FOREIGN KEY (`merit_id` )
    REFERENCES `generatordb`.`merit` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `generatordb`.`personage_has_merit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `generatordb`.`personage_has_merit` ;

CREATE  TABLE IF NOT EXISTS `generatordb`.`personage_has_merit` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `merit_id` INT NOT NULL ,
  `personage_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `merit_id`, `personage_id`) ,
  INDEX `fk_personage_has_merit_merit1` (`merit_id` ASC) ,
  INDEX `fk_personage_has_merit_personage1` (`personage_id` ASC) ,
  CONSTRAINT `fk_personage_has_merit_personage1`
    FOREIGN KEY (`personage_id` )
    REFERENCES `generatordb`.`personage` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personage_has_merit_merit1`
    FOREIGN KEY (`merit_id` )
    REFERENCES `generatordb`.`merit` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `generatordb`.`race_has_flaw`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `generatordb`.`race_has_flaw` ;

CREATE  TABLE IF NOT EXISTS `generatordb`.`race_has_flaw` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `flaw_id` INT NOT NULL ,
  `race_id` INT NOT NULL ,
  `default_for_race` TINYINT(1) NULL ,
  PRIMARY KEY (`id`, `flaw_id`, `race_id`) ,
  INDEX `fk_race_has_flaw_flaw1` (`flaw_id` ASC) ,
  INDEX `fk_race_has_flaw_race1` (`race_id` ASC) ,
  CONSTRAINT `fk_race_has_flaw_race1`
    FOREIGN KEY (`race_id` )
    REFERENCES `generatordb`.`race` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_race_has_flaw_flaw1`
    FOREIGN KEY (`flaw_id` )
    REFERENCES `generatordb`.`flaw` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `generatordb`.`personage_has_flaw`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `generatordb`.`personage_has_flaw` ;

CREATE  TABLE IF NOT EXISTS `generatordb`.`personage_has_flaw` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `flaw_id` INT NOT NULL ,
  `personage_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `flaw_id`, `personage_id`) ,
  INDEX `fk_personage_has_flaw_flaw1` (`flaw_id` ASC) ,
  INDEX `fk_personage_has_flaw_personage1` (`personage_id` ASC) ,
  CONSTRAINT `fk_personage_has_flaw_personage1`
    FOREIGN KEY (`personage_id` )
    REFERENCES `generatordb`.`personage` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personage_has_flaw_flaw1`
    FOREIGN KEY (`flaw_id` )
    REFERENCES `generatordb`.`flaw` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `generatordb`.`race_has_attribute`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `generatordb`.`race_has_attribute` ;

CREATE  TABLE IF NOT EXISTS `generatordb`.`race_has_attribute` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `attribute_id` INT NOT NULL ,
  `race_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `attribute_id`, `race_id`) ,
  INDEX `fk_race_has_attribute_attribute1` (`attribute_id` ASC) ,
  INDEX `fk_race_has_attribute_race1` (`race_id` ASC) ,
  CONSTRAINT `fk_race_has_attribute_race1`
    FOREIGN KEY (`race_id` )
    REFERENCES `generatordb`.`race` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_race_has_attribute_attribute1`
    FOREIGN KEY (`attribute_id` )
    REFERENCES `generatordb`.`attribute` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `generatordb`.`personage_has_attribute`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `generatordb`.`personage_has_attribute` ;

CREATE  TABLE IF NOT EXISTS `generatordb`.`personage_has_attribute` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `attribute_id` INT NOT NULL ,
  `personage_id` INT NOT NULL ,
  `value` INT NULL ,
  PRIMARY KEY (`id`, `attribute_id`, `personage_id`) ,
  INDEX `fk_personage_has_attribute_attribute1` (`attribute_id` ASC) ,
  INDEX `fk_personage_has_attribute_personage1` (`personage_id` ASC) ,
  CONSTRAINT `fk_personage_has_attribute_personage1`
    FOREIGN KEY (`personage_id` )
    REFERENCES `generatordb`.`personage` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personage_has_attribute_attribute1`
    FOREIGN KEY (`attribute_id` )
    REFERENCES `generatordb`.`attribute` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `generatordb`.`race_has_birthmerit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `generatordb`.`race_has_birthmerit` ;

CREATE  TABLE IF NOT EXISTS `generatordb`.`race_has_birthmerit` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `birth_merit_id` INT NOT NULL ,
  `race_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `birth_merit_id`, `race_id`) ,
  INDEX `fk_race_has_birthmerit_birthmerit1` (`birth_merit_id` ASC) ,
  INDEX `fk_race_has_birthmerit_race1` (`race_id` ASC) ,
  CONSTRAINT `fk_race_has_birthmerit_race1`
    FOREIGN KEY (`race_id` )
    REFERENCES `generatordb`.`race` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_race_has_birthmerit_birthmerit1`
    FOREIGN KEY (`birth_merit_id` )
    REFERENCES `generatordb`.`birth_merit` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `generatordb`.`personage_has_birthmerit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `generatordb`.`personage_has_birthmerit` ;

CREATE  TABLE IF NOT EXISTS `generatordb`.`personage_has_birthmerit` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `birth_merit_id` INT NOT NULL ,
  `personage_id` INT NOT NULL ,
  `value` INT NULL ,
  PRIMARY KEY (`id`, `birth_merit_id`, `personage_id`) ,
  INDEX `fk_personage_has_birthmerit_birthmerit1` (`birth_merit_id` ASC) ,
  INDEX `fk_personage_has_birthmerit_personage1` (`personage_id` ASC) ,
  CONSTRAINT `fk_personage_has_birthmerit_personage1`
    FOREIGN KEY (`personage_id` )
    REFERENCES `generatordb`.`personage` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personage_has_birthmerit_birthmerit1`
    FOREIGN KEY (`birth_merit_id` )
    REFERENCES `generatordb`.`birth_merit` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `generatordb`.`personage_has_triggerskill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `generatordb`.`personage_has_triggerskill` ;

CREATE  TABLE IF NOT EXISTS `generatordb`.`personage_has_triggerskill` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `trigger_skill_id` INT NOT NULL ,
  `personage_id` INT NOT NULL ,
  `level` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`, `trigger_skill_id`, `personage_id`) ,
  INDEX `fk_personage_has_triggerskill_triggerskill1` (`trigger_skill_id` ASC) ,
  INDEX `fk_personage_has_triggerskill_personage1` (`personage_id` ASC) ,
  CONSTRAINT `fk_personage_has_triggerskill_personage1`
    FOREIGN KEY (`personage_id` )
    REFERENCES `generatordb`.`personage` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personage_has_triggerskill_triggerskill1`
    FOREIGN KEY (`trigger_skill_id` )
    REFERENCES `generatordb`.`trigger_skill` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `generatordb`.`personage_has_attachedskill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `generatordb`.`personage_has_attachedskill` ;

CREATE  TABLE IF NOT EXISTS `generatordb`.`personage_has_attachedskill` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `attached_skill_id` INT NOT NULL ,
  `personage_id` INT NOT NULL ,
  `value` INT NULL ,
  PRIMARY KEY (`id`, `attached_skill_id`, `personage_id`) ,
  INDEX `fk_personage_has_attachedskill_attachedskill1` (`attached_skill_id` ASC) ,
  INDEX `fk_personage_has_attachedskill_personage1` (`personage_id` ASC) ,
  CONSTRAINT `fk_personage_has_attachedskill_attachedskill1`
    FOREIGN KEY (`attached_skill_id` )
    REFERENCES `generatordb`.`attached_skill` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personage_has_attachedskill_personage1`
    FOREIGN KEY (`personage_id` )
    REFERENCES `generatordb`.`personage` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
