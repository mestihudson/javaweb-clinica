package br.com.fiap.clinica.bean;

import javax.faces.bean.ManagedBean;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.modelo.Paciente;

@ManagedBean
public class PacienteBean {

	private Paciente paciente = new Paciente();
	
	public Paciente getPaciente() {
		return this.paciente;
	}
	
	public void grava() {
		DAO<Paciente> dao = new DAO<Paciente>(Paciente.class);
		dao.adiciona(paciente);
	}
	
	public void altera() {
		DAO<Paciente> dao = new DAO<Paciente>(Paciente.class);
		dao.atualiza(paciente);
	}
}
