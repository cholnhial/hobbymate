-- -----------------------------------------------------
-- Table `artefact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artefact` (
  `id` BIGINT(20) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  `price` DECIMAL(20,2) NOT NULL,
  `is_listed` INT(1) NOT NULL,
  `picture` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project` (
  `id` BIGINT(20) NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NOT NULL,
  `artefact_id` BIGINT(20) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `status` ENUM('IN_PROGRESS', 'COMPLETE') NOT NULL,
  `description` TEXT NOT NULL,
  INDEX `fk_project_user_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_project_artefact1_idx` (`artefact_id` ASC) VISIBLE,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `collaborator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `collaborator` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `project_id` BIGINT(20) NOT NULL,
  `fullName` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `mobile` VARCHAR(45) NOT NULL,
  `suburb` VARCHAR(255) NOT NULL,
  `bio` TEXT NOT NULL,
  `user_id` BIGINT(20) NOT NULL,
  INDEX `fk_collaborator_project1_idx` (`project_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  INDEX `fk_collaborator_user1_idx` (`user_id` ASC) VISIBLE)
ENGINE = InnoDB;