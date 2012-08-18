package br.com.fiap.clinica.bean;

import javax.faces.bean.ManagedBean;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.modelo.Especialidade;

@ManagedBean
public class EspecialidadeBean {

	private Especialidade especialidade;
	
	public Especialidade getEspecialidade() {
		return this.especialidade;
	}
	
	public void grava() {
		DAO<Especialidade> dao = new DAO<Especialidade>(Especialidade.class);
		dao.adiciona(especialidade);
	}
	
	public void altera() {
		DAO<Especialidade> dao = new DAO<Especialidade>(Especialidade.class);
		dao.atualiza(especialidade);
	}
	
}
