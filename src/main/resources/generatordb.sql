-- -----------------------------------------------------
-- Table `race`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `race` ;

CREATE  TABLE IF NOT EXISTS `race` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `max_age` INT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `personage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personage` ;

CREATE  TABLE IF NOT EXISTS `personage` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `age` INT NULL ,
  `race_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `race_id`) ,
  INDEX `fk_personage_race1` (`race_id` ASC) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_personage_race1`
    FOREIGN KEY (`race_id` )
    REFERENCES `race` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `attached_skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `attached_skill` ;

CREATE  TABLE IF NOT EXISTS `attached_skill` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `base_cost` INT NULL ,
  `default_skill` TINYINT(1) NULL ,
  `difficult` TINYINT(1) NULL ,
  `theoretical` TINYINT(1) NULL ,
  `acquiring_cost` INT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trigger_skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trigger_skill` ;

CREATE  TABLE IF NOT EXISTS `trigger_skill` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `type` VARCHAR(45) NULL ,
  `base_cost` INT NULL ,
  `expert_cost` INT NULL ,
  `master_cost` INT NULL ,
  `post_master_cost` INT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `attribute`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `attribute` ;

CREATE  TABLE IF NOT EXISTS `attribute` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `action_level_bonus` TEXT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `birth_merit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `birth_merit` ;

CREATE  TABLE IF NOT EXISTS `birth_merit` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `cost` INT NULL ,
  `description` TEXT NULL ,
  `action_bonus` TEXT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `merit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `merit` ;

CREATE  TABLE IF NOT EXISTS `merit` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `cost` INT NULL ,
  `description` TEXT NULL ,
  `action_bonus` TEXT NULL ,
  `preconditions` TEXT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flaw`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `flaw` ;

CREATE  TABLE IF NOT EXISTS `flaw` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `cost` INT NULL ,
  `description` TEXT NULL ,
  `turn_off_preconditions` TEXT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `race_has_merit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `race_has_merit` ;

CREATE  TABLE IF NOT EXISTS `race_has_merit` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `merit_id` INT NOT NULL ,
  `race_id` INT NOT NULL ,
  `default_for_race` TINYINT(1) NULL ,
  `race_cost` INT NULL ,
  PRIMARY KEY (`id`, `merit_id`, `race_id`) ,
  INDEX `fk_race_has_merit_merit1` (`merit_id` ASC) ,
  INDEX `fk_race_has_merit_race1` (`race_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_race_has_merit_race1`
    FOREIGN KEY (`race_id` )
    REFERENCES `race` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_race_has_merit_merit1`
    FOREIGN KEY (`merit_id` )
    REFERENCES `merit` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `personage_has_merit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personage_has_merit` ;

CREATE  TABLE IF NOT EXISTS `personage_has_merit` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `merit_id` INT NOT NULL ,
  `personage_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `merit_id`, `personage_id`) ,
  INDEX `fk_personage_has_merit_merit1` (`merit_id` ASC) ,
  INDEX `fk_personage_has_merit_personage1` (`personage_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_personage_has_merit_personage1`
    FOREIGN KEY (`personage_id` )
    REFERENCES `personage` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personage_has_merit_merit1`
    FOREIGN KEY (`merit_id` )
    REFERENCES `merit` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `race_has_flaw`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `race_has_flaw` ;

CREATE  TABLE IF NOT EXISTS `race_has_flaw` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `flaw_id` INT NOT NULL ,
  `race_id` INT NOT NULL ,
  `default_for_race` TINYINT(1) NULL ,
  PRIMARY KEY (`id`, `flaw_id`, `race_id`) ,
  INDEX `fk_race_has_flaw_flaw1` (`flaw_id` ASC) ,
  INDEX `fk_race_has_flaw_race1` (`race_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_race_has_flaw_race1`
    FOREIGN KEY (`race_id` )
    REFERENCES `race` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_race_has_flaw_flaw1`
    FOREIGN KEY (`flaw_id` )
    REFERENCES `flaw` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `personage_has_flaw`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personage_has_flaw` ;

CREATE  TABLE IF NOT EXISTS `personage_has_flaw` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `flaw_id` INT NOT NULL ,
  `personage_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `flaw_id`, `personage_id`) ,
  INDEX `fk_personage_has_flaw_flaw1` (`flaw_id` ASC) ,
  INDEX `fk_personage_has_flaw_personage1` (`personage_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_personage_has_flaw_personage1`
    FOREIGN KEY (`personage_id` )
    REFERENCES `personage` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personage_has_flaw_flaw1`
    FOREIGN KEY (`flaw_id` )
    REFERENCES `flaw` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `race_has_attribute`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `race_has_attribute` ;

CREATE  TABLE IF NOT EXISTS `race_has_attribute` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `attribute_id` INT NOT NULL ,
  `race_id` INT NOT NULL ,
  `base_cost` INT NULL ,
  `from_1_to_3_non_generating_cost` INT NULL ,
  `from_3_to_6_non_generating_cost` INT NULL ,
  `from_6_to_9_non_generating_cost` INT NULL ,
  `from_9_to_12_non_generating_cost` INT NULL ,
  `max_value` INT NULL ,
  PRIMARY KEY (`id`, `attribute_id`, `race_id`) ,
  INDEX `fk_race_has_attribute_attribute1` (`attribute_id` ASC) ,
  INDEX `fk_race_has_attribute_race1` (`race_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_race_has_attribute_race1`
    FOREIGN KEY (`race_id` )
    REFERENCES `race` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_race_has_attribute_attribute1`
    FOREIGN KEY (`attribute_id` )
    REFERENCES `attribute` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `personage_has_attribute`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personage_has_attribute` ;

CREATE  TABLE IF NOT EXISTS `personage_has_attribute` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `attribute_id` INT NOT NULL ,
  `personage_id` INT NOT NULL ,
  `current_value` INT NULL ,
  PRIMARY KEY (`id`, `attribute_id`, `personage_id`) ,
  INDEX `fk_personage_has_attribute_attribute1` (`attribute_id` ASC) ,
  INDEX `fk_personage_has_attribute_personage1` (`personage_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_personage_has_attribute_personage1`
    FOREIGN KEY (`personage_id` )
    REFERENCES `personage` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personage_has_attribute_attribute1`
    FOREIGN KEY (`attribute_id` )
    REFERENCES `attribute` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `race_has_birth_merit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `race_has_birth_merit` ;

CREATE  TABLE IF NOT EXISTS `race_has_birth_merit` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `birth_merit_id` INT NOT NULL ,
  `race_id` INT NOT NULL ,
  `probability` INT NULL ,
  PRIMARY KEY (`id`, `birth_merit_id`, `race_id`) ,
  INDEX `fk_race_has_birthmerit_birthmerit1` (`birth_merit_id` ASC) ,
  INDEX `fk_race_has_birthmerit_race1` (`race_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_race_has_birthmerit_race1`
    FOREIGN KEY (`race_id` )
    REFERENCES `race` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_race_has_birthmerit_birthmerit1`
    FOREIGN KEY (`birth_merit_id` )
    REFERENCES `birth_merit` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `personage_has_birth_merit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personage_has_birth_merit` ;

CREATE  TABLE IF NOT EXISTS `personage_has_birth_merit` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `birth_merit_id` INT NOT NULL ,
  `personage_id` INT NOT NULL ,
  `current_value` INT NULL ,
  PRIMARY KEY (`id`, `birth_merit_id`, `personage_id`) ,
  INDEX `fk_personage_has_birthmerit_birthmerit1` (`birth_merit_id` ASC) ,
  INDEX `fk_personage_has_birthmerit_personage1` (`personage_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_personage_has_birthmerit_personage1`
    FOREIGN KEY (`personage_id` )
    REFERENCES `personage` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personage_has_birthmerit_birthmerit1`
    FOREIGN KEY (`birth_merit_id` )
    REFERENCES `birth_merit` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `personage_has_trigger_skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personage_has_trigger_skill` ;

CREATE  TABLE IF NOT EXISTS `personage_has_trigger_skill` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `trigger_skill_id` INT NOT NULL ,
  `personage_id` INT NOT NULL ,
  `current_level` VARCHAR(45) NULL ,
  `has_talent` TINYINT(1) NULL ,
  `has_teacher` TINYINT(1) NULL ,
  PRIMARY KEY (`id`, `trigger_skill_id`, `personage_id`) ,
  INDEX `fk_personage_has_triggerskill_triggerskill1` (`trigger_skill_id` ASC) ,
  INDEX `fk_personage_has_triggerskill_personage1` (`personage_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_personage_has_triggerskill_personage1`
    FOREIGN KEY (`personage_id` )
    REFERENCES `personage` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personage_has_triggerskill_triggerskill1`
    FOREIGN KEY (`trigger_skill_id` )
    REFERENCES `trigger_skill` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `personage_has_attached_skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personage_has_attached_skill` ;

CREATE  TABLE IF NOT EXISTS `personage_has_attached_skill` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `attached_skill_id` INT NOT NULL ,
  `personage_id` INT NOT NULL ,
  `current_value` INT NULL ,
  PRIMARY KEY (`id`, `attached_skill_id`, `personage_id`) ,
  INDEX `fk_personage_has_attachedskill_attachedskill1` (`attached_skill_id` ASC) ,
  INDEX `fk_personage_has_attachedskill_personage1` (`personage_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_personage_has_attachedskill_attachedskill1`
    FOREIGN KEY (`attached_skill_id` )
    REFERENCES `attached_skill` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personage_has_attachedskill_personage1`
    FOREIGN KEY (`personage_id` )
    REFERENCES `personage` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
