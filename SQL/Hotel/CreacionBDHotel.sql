-- MySQL Script generated by MySQL Workbench
-- sáb 09 nov 2019 23:15:52 CST
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Hotel
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Hotel
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Hotel` DEFAULT CHARACTER SET utf8;
USE `Hotel` ;

-- -----------------------------------------------------
-- Table `Hotel`.`Hoteles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hotel`.`Hoteles` (
  `idHotel` INT NOT NULL,
  `NombreHotel` VARCHAR(45) NULL,
  PRIMARY KEY (`idHotel`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hotel`.`Habitaciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hotel`.`Habitaciones` (
  `idHabitacion` INT NOT NULL,
  `idHotel` INT NOT NULL,
  `CostoHabitacion` DECIMAL(4,2) UNSIGNED NOT NULL,
  PRIMARY KEY (`idHabitacion`),
  INDEX `fk_Habitaciones_Hoteles_idx` (`idHotel` ASC),
  CONSTRAINT `fk_Habitaciones_Hoteles`
    FOREIGN KEY (`idHotel`)
    REFERENCES `Hotel`.`Hoteles` (`idHotel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hotel`.`Reservaciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hotel`.`Reservaciones` (
  `idReservacion` INT NOT NULL,
  `idHabitacion` INT NOT NULL,
  `EmailUsuario` VARCHAR(50) NOT NULL,
  `FechaReservacion` DATE NOT NULL,
  PRIMARY KEY (`idReservacion`),
  INDEX `fk_Reservaciones_Habitaciones_idx` (`idHabitacion` ASC),
  CONSTRAINT `fk_Reservaciones_Habitaciones`
    FOREIGN KEY (`idHabitacion`)
    REFERENCES `Hotel`.`Habitaciones` (`idHabitacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE USER 'usuario' IDENTIFIED BY 'oracle';

GRANT SELECT, INSERT, TRIGGER ON TABLE `Hotel`.* TO 'usuario';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `Hotel`.* TO 'usuario';
GRANT ALL ON `Hotel`.* TO 'usuario';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
