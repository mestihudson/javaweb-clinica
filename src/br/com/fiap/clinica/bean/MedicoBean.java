package br.com.fiap.clinica.bean;

import javax.faces.bean.ManagedBean;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.modelo.Medico;

@ManagedBean
public class MedicoBean {

	private Medico medico = new Medico();
	
	public Medico getMedico() {
		return this.medico;
	}
	
	public void grava() {
		DAO<Medico> dao = new DAO<Medico>(Medico.class);
		dao.adiciona(medico);
	}
	
	public void altera() {
		DAO<Medico> dao = new DAO<Medico>(Medico.class);
		dao.atualiza(medico);
	}
	
}
