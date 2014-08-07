CREATE  TABLE IF NOT EXISTS `generatordb`.`race` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `maxage` INT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

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

CREATE  TABLE IF NOT EXISTS `generatordb`.`triggerskill` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `type` VARCHAR(45) NULL ,
  `nongeneratingcostcoefficient` DOUBLE NULL ,
  `level` VARCHAR(45) NULL ,
  `personage_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `personage_id`) ,
  INDEX `fk_triggerskill_character1` (`personage_id` ASC) ,
  CONSTRAINT `fk_triggerskill_character1`
    FOREIGN KEY (`personage_id` )
    REFERENCES `generatordb`.`personage` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE  TABLE IF NOT EXISTS `generatordb`.`attachedskill` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `value` INT NULL ,
  `basecost` INT NULL ,
  `default` TINYINT(1) NULL ,
  `difficult` TINYINT(1) NULL ,
  `theoretical` TINYINT(1) NULL ,
  `acquiringcost` INT NULL ,
  `personage_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `personage_id`) ,
  INDEX `fk_attachedskill_character1` (`personage_id` ASC) ,
  CONSTRAINT `fk_attachedskill_character1`
    FOREIGN KEY (`personage_id` )
    REFERENCES `generatordb`.`personage` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE  TABLE IF NOT EXISTS `generatordb`.`birthmerit` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `probability` DOUBLE NULL ,
  `value` INT NULL ,
  `cost` INT NULL ,
  `description` TEXT NULL ,
  `actionbonus` TEXT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

CREATE  TABLE IF NOT EXISTS `generatordb`.`race_has_birthmerit` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `birthmerit_id` INT NOT NULL ,
  `race_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `birthmerit_id`, `race_id`) ,
  INDEX `fk_race_has_birthmerit_birthmerit1` (`birthmerit_id` ASC) ,
  INDEX `fk_race_has_birthmerit_race1` (`race_id` ASC) ,
  CONSTRAINT `fk_race_has_birthmerit_race1`
    FOREIGN KEY (`race_id` )
    REFERENCES `generatordb`.`race` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_race_has_birthmerit_birthmerit1`
    FOREIGN KEY (`birthmerit_id` )
    REFERENCES `generatordb`.`birthmerit` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE  TABLE IF NOT EXISTS `generatordb`.`personage_has_birthmerit` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `birthmerit_id` INT NOT NULL ,
  `personage_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `birthmerit_id`, `personage_id`) ,
  INDEX `fk_personage_has_birthmerit_birthmerit1` (`birthmerit_id` ASC) ,
  INDEX `fk_personage_has_birthmerit_personage1` (`personage_id` ASC) ,
  CONSTRAINT `fk_personage_has_birthmerit_personage1`
    FOREIGN KEY (`personage_id` )
    REFERENCES `generatordb`.`personage` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personage_has_birthmerit_birthmerit1`
    FOREIGN KEY (`birthmerit_id` )
    REFERENCES `generatordb`.`birthmerit` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE  TABLE IF NOT EXISTS `generatordb`.`attribute` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `cost` INT NULL ,
  `value` INT NULL ,
  `maxvalue` INT NULL ,
  `minvalue` INT NULL ,
  `from1to3nongeneratingcost` INT NULL ,
  `costrisecoefficient` DOUBLE NULL ,
  `nongeneratingrisecoefficient` DOUBLE NULL ,
  `actionlevelbonus` TEXT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

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

CREATE  TABLE IF NOT EXISTS `generatordb`.`personage_has_attribute` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `attribute_id` INT NOT NULL ,
  `personage_id` INT NOT NULL ,
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

CREATE  TABLE IF NOT EXISTS `generatordb`.`flaw` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `cost` INT NULL ,
  `description` TEXT NULL ,
  `turnoffpreconditions` TEXT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

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

CREATE  TABLE IF NOT EXISTS `generatordb`.`race_has_flaw` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `flaw_id` INT NOT NULL ,
  `race_id` INT NOT NULL ,
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

CREATE  TABLE IF NOT EXISTS `generatordb`.`merit` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `cost` INT NULL ,
  `description` TEXT NULL ,
  `actionbonus` TEXT NULL ,
  `preconditions` TEXT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

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

CREATE  TABLE IF NOT EXISTS `generatordb`.`race_has_merit` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `merit_id` INT NOT NULL ,
  `race_id` INT NOT NULL ,
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