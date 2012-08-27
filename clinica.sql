SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `clinica` ;
CREATE SCHEMA IF NOT EXISTS `clinica` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `clinica` ;

-- -----------------------------------------------------
-- Table `convenio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `convenio` ;

CREATE  TABLE IF NOT EXISTS `convenio` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `descricao` VARCHAR(45) NOT NULL ,
  `ativo` TINYINT(1) NOT NULL DEFAULT 1 COMMENT 'Flag ativo/inativo - 1/0' ,
  `data_cadastro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `area`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `area` ;

CREATE  TABLE IF NOT EXISTS `area` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `descricao` VARCHAR(45) NOT NULL ,
  `ativa` TINYINT(1) NOT NULL DEFAULT 1 ,
  `data_cadastro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `especialidade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `especialidade` ;

CREATE  TABLE IF NOT EXISTS `especialidade` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `descricao` VARCHAR(45) NOT NULL ,
  `id_area` INT NOT NULL ,
  `ativa` TINYINT(1) NOT NULL DEFAULT 1 COMMENT 'Flag ativo/inativo - 1/0' ,
  `data_cadastro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_medico_especialidade_medico_especialidade_area1` (`id_area` ASC) ,
  CONSTRAINT `fk_medico_especialidade_medico_especialidade_area1`
    FOREIGN KEY (`id_area` )
    REFERENCES `area` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `medico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medico` ;

CREATE  TABLE IF NOT EXISTS `medico` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(45) NOT NULL ,
  `sobrenome` VARCHAR(45) NOT NULL ,
  `id_especialidade` INT NOT NULL COMMENT 'Especialidade do medico' ,
  `ativo` TINYINT(1) NOT NULL DEFAULT 1 COMMENT 'Flag ativo/inativo - 1/0' ,
  `data_cadastro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_medico_medico_especialidade` (`id_especialidade` ASC) ,
  CONSTRAINT `fk_medico_medico_especialidade`
    FOREIGN KEY (`id_especialidade` )
    REFERENCES `especialidade` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paciente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paciente` ;

CREATE  TABLE IF NOT EXISTS `paciente` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(45) NOT NULL ,
  `sobrenome` VARCHAR(45) NOT NULL ,
  `endereco` VARCHAR(45) NOT NULL ,
  `cpf` VARCHAR(14) NOT NULL ,
  `ativo` TINYINT(1) NOT NULL DEFAULT 1 COMMENT 'Flag ativo/inativo - 1/0' ,
  `data_cadastro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `cpf` (`cpf` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tratamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tratamento` ;

CREATE  TABLE IF NOT EXISTS `tratamento` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `descricao` VARCHAR(45) NOT NULL ,
  `valor_remuneracao` FLOAT NOT NULL ,
  `ativo` TINYINT(1) NOT NULL DEFAULT 1 ,
  `data_cadastro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `especialidade_tratamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `especialidade_tratamento` ;

CREATE  TABLE IF NOT EXISTS `especialidade_tratamento` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_tratamento` INT NOT NULL ,
  `id_especialidade` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_medico_especialidade_tratamento_tratamento1` (`id_tratamento` ASC) ,
  INDEX `fk_medico_especialidade_tratamento_especialidade1` (`id_especialidade` ASC) ,
  CONSTRAINT `fk_medico_especialidade_tratamento_tratamento1`
    FOREIGN KEY (`id_tratamento` )
    REFERENCES `tratamento` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_medico_especialidade_tratamento_especialidade1`
    FOREIGN KEY (`id_especialidade` )
    REFERENCES `especialidade` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `material_tipo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `material_tipo` ;

CREATE  TABLE IF NOT EXISTS `material_tipo` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `descricao` VARCHAR(45) NOT NULL ,
  `ativo` TINYINT(1) NOT NULL DEFAULT 1 ,
  `data_cadastro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `material`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `material` ;

CREATE  TABLE IF NOT EXISTS `material` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `descricao` VARCHAR(45) NOT NULL ,
  `valor_remuneracao` FLOAT NOT NULL ,
  `id_material_tipo` INT NOT NULL ,
  `ativo` TINYINT(1) NOT NULL DEFAULT 1 ,
  `data_cadastro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_material_material_tipo1` (`id_material_tipo` ASC) ,
  CONSTRAINT `fk_material_material_tipo1`
    FOREIGN KEY (`id_material_tipo` )
    REFERENCES `material_tipo` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agendamento_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `agendamento_status` ;

CREATE  TABLE IF NOT EXISTS `agendamento_status` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `descricao` VARCHAR(45) NOT NULL ,
  `ativo` TINYINT(1) NOT NULL DEFAULT 1 ,
  `data_cadastro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agendamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `agendamento` ;

CREATE  TABLE IF NOT EXISTS `agendamento` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `data_consulta` DATETIME NOT NULL ,
  `numero_convenio` BIGINT(14) NOT NULL ,
  `id_paciente` INT NOT NULL ,
  `id_convenio` INT NOT NULL ,
  `id_medico` INT NOT NULL ,
  `id_agendamento_status` INT NOT NULL ,
  `ativo` TINYINT(1) NOT NULL DEFAULT 1 ,
  `data_cadastro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_agendamento_paciente1` (`id_paciente` ASC) ,
  INDEX `fk_agendamento_convenio1` (`id_convenio` ASC) ,
  INDEX `fk_agendamento_medico1` (`id_medico` ASC) ,
  INDEX `fk_agendamento_agendamento_status1` (`id_agendamento_status` ASC) ,
  CONSTRAINT `fk_agendamento_paciente1`
    FOREIGN KEY (`id_paciente` )
    REFERENCES `paciente` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_agendamento_convenio1`
    FOREIGN KEY (`id_convenio` )
    REFERENCES `convenio` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_agendamento_medico1`
    FOREIGN KEY (`id_medico` )
    REFERENCES `medico` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_agendamento_agendamento_status1`
    FOREIGN KEY (`id_agendamento_status` )
    REFERENCES `agendamento_status` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `atendimento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `atendimento` ;

CREATE  TABLE IF NOT EXISTS `atendimento` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_agendamento` INT NOT NULL ,
  `ativo` TINYINT(1) NOT NULL DEFAULT 1 ,
  `data_cadastro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_atendimento_agendamento1` (`id_agendamento` ASC) ,
  CONSTRAINT `fk_atendimento_agendamento1`
    FOREIGN KEY (`id_agendamento` )
    REFERENCES `agendamento` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `atendimento_material`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `atendimento_material` ;

CREATE  TABLE IF NOT EXISTS `atendimento_material` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_atendimento` INT NOT NULL ,
  `id_material` INT NOT NULL DEFAULT 1 ,
  `data_cadastro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_agendamento_material_material1` (`id_material` ASC) ,
  INDEX `fk_atendimento_material_atendimento1` (`id_atendimento` ASC) ,
  CONSTRAINT `fk_agendamento_material_material1`
    FOREIGN KEY (`id_material` )
    REFERENCES `material` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_atendimento_material_atendimento1`
    FOREIGN KEY (`id_atendimento` )
    REFERENCES `atendimento` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `usuario` ;

CREATE  TABLE IF NOT EXISTS `usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NULL ,
  `login` VARCHAR(45) NULL ,
  `senha` VARCHAR(45) NULL ,
  `ativo` TINYINT(1) NULL ,
  `admin` TINYINT(1) NULL ,
  `dt_cadastro` TIMESTAMP NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
