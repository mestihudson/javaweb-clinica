package br.com.fiap.clinica.bean;

import javax.faces.bean.ManagedBean;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.modelo.Convenio;

@ManagedBean
public class ConvenioBean {

	private Convenio convenio = new Convenio();
	
	public Convenio getConvenio() {
		return this.convenio;
	}
	
	public void grava() {
		DAO<Convenio> dao = new DAO<Convenio>(Convenio.class);
		dao.adiciona(convenio);
	}
	
	public void altera() {
		DAO<Convenio> dao = new DAO<Convenio>(Convenio.class);
		dao.atualiza(convenio);
	}
}
