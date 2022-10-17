-- -----------------------------------------------------
-- Table `shop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `artefact_id` BIGINT(20) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  `price` DECIMAL(20,2) NOT NULL,
  `picture` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_shop_artefact1_idx` (`artefact_id` ASC) VISIBLE)
ENGINE = InnoDB;