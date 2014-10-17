ALTER TABLE `personage` ADD COLUMN `generated` TINYINT(1) NULL DEFAULT NULL  AFTER `age` , CHANGE COLUMN `race_id` `race_id` INT(11) NOT NULL  AFTER `id` ;

ALTER TABLE `personage_has_attribute` ADD COLUMN `priority` VARCHAR(45) NULL DEFAULT NULL  AFTER `current_value` ;
