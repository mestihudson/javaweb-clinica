package br.com.fiap.clinica.bean;

import javax.faces.bean.ManagedBean;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.modelo.Tratamento;

@ManagedBean
public class TratamentoBean {

	private Tratamento tratamento;
	
	public Tratamento getTratamento() {
		return this.tratamento;
	}
	
	public void grava() {
		DAO<Tratamento> dao = new DAO<Tratamento>(Tratamento.class);
		dao.adiciona(tratamento);
	}
	
	public void altera() {
		DAO<Tratamento> dao = new DAO<Tratamento>(Tratamento.class);
		dao.atualiza(tratamento);
	}
}
