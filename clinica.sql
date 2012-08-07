SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`convenio`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`convenio` (
  `cnv_id` INT NOT NULL AUTO_INCREMENT ,
  `cnv_descricao` VARCHAR(45) NOT NULL ,
  `cnv_flg_atv` TINYINT(1) NOT NULL DEFAULT 1 COMMENT 'Flag ativo/inativo - 1/0' ,
  `cnv_qdo` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`cnv_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`area`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`area` (
  `are_id` INT NOT NULL AUTO_INCREMENT ,
  `are_descricao` VARCHAR(45) NOT NULL ,
  `are_flg_atv` TINYINT(1) NOT NULL DEFAULT 1 ,
  `are_qdo` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`are_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`especialidade`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`especialidade` (
  `esp_id` INT NOT NULL AUTO_INCREMENT ,
  `esp_descricao` VARCHAR(45) NOT NULL ,
  `esp_are_id` INT NOT NULL ,
  `esp_flt_atv` TINYINT(1) NOT NULL DEFAULT 1 COMMENT 'Flag ativo/inativo - 1/0' ,
  `esp_qdo` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`esp_id`) ,
  INDEX `fk_medico_especialidade_medico_especialidade_area1` (`esp_are_id` ASC) ,
  CONSTRAINT `fk_medico_especialidade_medico_especialidade_area1`
    FOREIGN KEY (`esp_are_id` )
    REFERENCES `mydb`.`area` (`are_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`medico`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`medico` (
  `mdc_id` INT NOT NULL AUTO_INCREMENT ,
  `mdc_nome` VARCHAR(45) NOT NULL ,
  `mdc_sobrenome` VARCHAR(45) NOT NULL ,
  `mdc_esp_id` INT NOT NULL COMMENT 'Especialidade do medico' ,
  `mdc_flg_atv` TINYINT(1) NOT NULL DEFAULT 1 COMMENT 'Flag ativo/inativo - 1/0' ,
  `mdc_qdo` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`mdc_id`) ,
  INDEX `fk_medico_medico_especialidade` (`mdc_esp_id` ASC) ,
  CONSTRAINT `fk_medico_medico_especialidade`
    FOREIGN KEY (`mdc_esp_id` )
    REFERENCES `mydb`.`especialidade` (`esp_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`paciente`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`paciente` (
  `pct_id` INT NOT NULL AUTO_INCREMENT ,
  `pct_nome` VARCHAR(45) NOT NULL ,
  `pct_sobrenome` VARCHAR(45) NOT NULL ,
  `pct_endereco` VARCHAR(45) NOT NULL ,
  `pct_cpf` VARCHAR(14) NOT NULL ,
  `pct_flg_atv` TINYINT(1) NOT NULL DEFAULT 1 COMMENT 'Flag ativo/inativo - 1/0' ,
  `pct_qdo` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`pct_id`) ,
  UNIQUE INDEX `cpf` (`pct_cpf` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tratamento`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`tratamento` (
  `trt_id` INT NOT NULL AUTO_INCREMENT ,
  `trt_descricao` VARCHAR(45) NOT NULL ,
  `trt_valor_remuneracao` FLOAT NOT NULL ,
  `trt_flg_atv` TINYINT(1) NOT NULL DEFAULT 1 ,
  `trt_qdo` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`trt_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`especialidade_tratamento`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`especialidade_tratamento` (
  `est_id` INT NOT NULL AUTO_INCREMENT ,
  `est_trt_id` INT NOT NULL ,
  `est_esp_id` INT NOT NULL ,
  PRIMARY KEY (`est_id`) ,
  INDEX `fk_medico_especialidade_tratamento_tratamento1` (`est_trt_id` ASC) ,
  INDEX `fk_medico_especialidade_tratamento_especialidade1` (`est_esp_id` ASC) ,
  CONSTRAINT `fk_medico_especialidade_tratamento_tratamento1`
    FOREIGN KEY (`est_trt_id` )
    REFERENCES `mydb`.`tratamento` (`trt_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_medico_especialidade_tratamento_especialidade1`
    FOREIGN KEY (`est_esp_id` )
    REFERENCES `mydb`.`especialidade` (`esp_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`material_tipo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`material_tipo` (
  `mtt_id` INT NOT NULL AUTO_INCREMENT ,
  `mtt_descricao` VARCHAR(45) NOT NULL ,
  `mtt_flg_atv` TINYINT(1) NOT NULL DEFAULT 1 ,
  `mtt_qdo` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`mtt_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`material`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`material` (
  `mat_id` INT NOT NULL AUTO_INCREMENT ,
  `mat_descricao` VARCHAR(45) NOT NULL ,
  `mat_valor_remuneracao` FLOAT NOT NULL ,
  `mat_mtt_id` INT NOT NULL ,
  `mat_flg_atv` TINYINT(1) NOT NULL DEFAULT 1 ,
  `mat_qdo` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`mat_id`) ,
  INDEX `fk_material_material_tipo1` (`mat_mtt_id` ASC) ,
  CONSTRAINT `fk_material_material_tipo1`
    FOREIGN KEY (`mat_mtt_id` )
    REFERENCES `mydb`.`material_tipo` (`mtt_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`agendamento_status`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`agendamento_status` (
  `ags_id` INT NOT NULL AUTO_INCREMENT ,
  `ags_descricao` VARCHAR(45) NOT NULL ,
  `ags_flg_atv` TINYINT(1) NOT NULL DEFAULT 1 ,
  `ags_qdo` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`ags_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`agendamento`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`agendamento` (
  `agd_id` INT NOT NULL AUTO_INCREMENT ,
  `agd_data_consulta` DATETIME NOT NULL ,
  `agd_numero_convenio` BIGINT(14) NOT NULL ,
  `agd_pct_id` INT NOT NULL ,
  `agd_cnv_id` INT NOT NULL ,
  `agd_mdc_id` INT NOT NULL ,
  `agd_ags_id` INT NOT NULL ,
  `agd_flg_atv` TINYINT(1) NOT NULL DEFAULT 1 ,
  `agd_qdo` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`agd_id`) ,
  INDEX `fk_agendamento_paciente1` (`agd_pct_id` ASC) ,
  INDEX `fk_agendamento_convenio1` (`agd_cnv_id` ASC) ,
  INDEX `fk_agendamento_medico1` (`agd_mdc_id` ASC) ,
  INDEX `fk_agendamento_agendamento_status1` (`agd_ags_id` ASC) ,
  CONSTRAINT `fk_agendamento_paciente1`
    FOREIGN KEY (`agd_pct_id` )
    REFERENCES `mydb`.`paciente` (`pct_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_agendamento_convenio1`
    FOREIGN KEY (`agd_cnv_id` )
    REFERENCES `mydb`.`convenio` (`cnv_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_agendamento_medico1`
    FOREIGN KEY (`agd_mdc_id` )
    REFERENCES `mydb`.`medico` (`mdc_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_agendamento_agendamento_status1`
    FOREIGN KEY (`agd_ags_id` )
    REFERENCES `mydb`.`agendamento_status` (`ags_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`atendimento`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`atendimento` (
  `atd_id` INT NOT NULL AUTO_INCREMENT ,
  `atd_agd_id` INT NOT NULL ,
  `atd_flg_atv` TINYINT(1) NOT NULL DEFAULT 1 ,
  `atd_qdo` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`atd_id`) ,
  INDEX `fk_atendimento_agendamento1` (`atd_agd_id` ASC) ,
  CONSTRAINT `fk_atendimento_agendamento1`
    FOREIGN KEY (`atd_agd_id` )
    REFERENCES `mydb`.`agendamento` (`agd_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`atendimento_material`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`atendimento_material` (
  `atm_id` INT NOT NULL AUTO_INCREMENT ,
  `atm_atd_id` INT NOT NULL ,
  `atm_mat_id` INT NOT NULL DEFAULT 1 ,
  `atm_qdo` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`atm_id`) ,
  INDEX `fk_agendamento_material_material1` (`atm_mat_id` ASC) ,
  INDEX `fk_atendimento_material_atendimento1` (`atm_atd_id` ASC) ,
  CONSTRAINT `fk_agendamento_material_material1`
    FOREIGN KEY (`atm_mat_id` )
    REFERENCES `mydb`.`material` (`mat_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_atendimento_material_atendimento1`
    FOREIGN KEY (`atm_atd_id` )
    REFERENCES `mydb`.`atendimento` (`atd_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
