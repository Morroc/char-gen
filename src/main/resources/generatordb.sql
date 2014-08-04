CREATE  TABLE IF NOT EXISTS `generatordb`.`race` (
  `id` INT NOT NULL ,
  `name` VARCHAR(45) NULL ,
  `maxage` INT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

CREATE  TABLE IF NOT EXISTS `generatordb`.`character` (
  `id` INT NOT NULL ,
  `name` VARCHAR(45) NULL ,
  `age` VARCHAR(45) NULL ,
  `race_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `race_id`) ,
  INDEX `fk_character_race1` (`race_id` ASC) ,
  CONSTRAINT `fk_character_race1`
    FOREIGN KEY (`race_id` )
    REFERENCES `generatordb`.`race` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE  TABLE IF NOT EXISTS `generatordb`.`triggerskill` (
  `id` INT NOT NULL ,
  `name` VARCHAR(45) NULL ,
  `type` VARCHAR(45) NULL ,
  `nongeneratingcostcoefficient` DOUBLE NULL ,
  `level` VARCHAR(45) NULL ,
  `character_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `character_id`) ,
  INDEX `fk_triggerskill_character1` (`character_id` ASC) ,
  CONSTRAINT `fk_triggerskill_character1`
    FOREIGN KEY (`character_id` )
    REFERENCES `generatordb`.`character` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE  TABLE IF NOT EXISTS `generatordb`.`attachedskill` (
  `id` INT NOT NULL ,
  `name` VARCHAR(45) NULL ,
  `value` INT NULL ,
  `basecost` INT NULL ,
  `default` TINYINT(1) NULL ,
  `difficult` TINYINT(1) NULL ,
  `theoretical` TINYINT(1) NULL ,
  `acquiringcost` INT NULL ,
  `character_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `character_id`) ,
  INDEX `fk_attachedskill_character1` (`character_id` ASC) ,
  CONSTRAINT `fk_attachedskill_character1`
    FOREIGN KEY (`character_id` )
    REFERENCES `generatordb`.`character` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE  TABLE IF NOT EXISTS `generatordb`.`merit` (
  `id` INT NOT NULL ,
  `name` VARCHAR(45) NULL ,
  `cost` INT NULL ,
  `description` TEXT NULL ,
  `actionbonus` TEXT NULL ,
  `preconditions` TEXT NULL ,
  `race_id` INT NOT NULL ,
  `character_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `race_id`, `character_id`) ,
  INDEX `fk_merit_race1` (`race_id` ASC) ,
  INDEX `fk_merit_character1` (`character_id` ASC) ,
  CONSTRAINT `fk_merit_race1`
    FOREIGN KEY (`race_id` )
    REFERENCES `generatordb`.`race` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_merit_character1`
    FOREIGN KEY (`character_id` )
    REFERENCES `generatordb`.`character` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE  TABLE IF NOT EXISTS `generatordb`.`birthmerit` (
  `id` INT NOT NULL ,
  `name` VARCHAR(45) NULL ,
  `probability` DOUBLE NULL ,
  `value` INT NULL ,
  `cost` INT NULL ,
  `description` TEXT NULL ,
  `actionbonus` TEXT NULL ,
  `race_id` INT NOT NULL ,
  `character_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `race_id`, `character_id`) ,
  INDEX `fk_birthmerit_race1` (`race_id` ASC) ,
  INDEX `fk_birthmerit_character1` (`character_id` ASC) ,
  CONSTRAINT `fk_birthmerit_race1`
    FOREIGN KEY (`race_id` )
    REFERENCES `generatordb`.`race` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_birthmerit_character1`
    FOREIGN KEY (`character_id` )
    REFERENCES `generatordb`.`character` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE  TABLE IF NOT EXISTS `generatordb`.`attribute` (
  `id` INT NOT NULL ,
  `cost` INT NULL ,
  `value` INT NULL ,
  `maxvalue` INT NULL ,
  `minvalue` INT NULL ,
  `from1to3nongeneratingcost` INT NULL ,
  `costrisecoefficient` DOUBLE NULL ,
  `nongeneratingrisecoefficient` DOUBLE NULL ,
  `actionlevelbonus` TEXT NULL ,
  `race_id` INT NOT NULL ,
  `character_id` INT NOT NULL ,
  `character_race_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `race_id`, `character_id`, `character_race_id`) ,
  INDEX `fk_attribute_race1` (`race_id` ASC) ,
  INDEX `fk_attribute_character1` (`character_id` ASC, `character_race_id` ASC) ,
  CONSTRAINT `fk_attribute_race1`
    FOREIGN KEY (`race_id` )
    REFERENCES `generatordb`.`race` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_attribute_character1`
    FOREIGN KEY (`character_id` , `character_race_id` )
    REFERENCES `generatordb`.`character` (`id` , `race_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE  TABLE IF NOT EXISTS `generatordb`.`flaw` (
  `id` INT NOT NULL ,
  `name` VARCHAR(45) NULL ,
  `cost` INT NULL ,
  `description` TEXT NULL ,
  `turnoffpreconditions` TEXT NULL ,
  `character_id` INT NOT NULL ,
  `race_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `character_id`, `race_id`) ,
  INDEX `fk_flaw_character1` (`character_id` ASC) ,
  INDEX `fk_flaw_race1` (`race_id` ASC) ,
  CONSTRAINT `fk_flaw_character1`
    FOREIGN KEY (`character_id` )
    REFERENCES `generatordb`.`character` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_flaw_race1`
    FOREIGN KEY (`race_id` )
    REFERENCES `generatordb`.`race` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;