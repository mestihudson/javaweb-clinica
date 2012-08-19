package br.com.fiap.clinica.bean;

import javax.faces.bean.ManagedBean;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.modelo.Atendimento;

@ManagedBean
public class AtendimentoBean {

	private Atendimento atendimento = new Atendimento();
	
	public Atendimento getAtendimento() {
		return this.atendimento;
	}
	
	public void grava() {
		DAO<Atendimento> dao = new DAO<Atendimento>(Atendimento.class);
		dao.adiciona(atendimento);
	}
}
