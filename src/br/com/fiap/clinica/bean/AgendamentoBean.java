package br.com.fiap.clinica.bean;

import javax.faces.bean.ManagedBean;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.modelo.Agendamento;

@ManagedBean
public class AgendamentoBean {

	private Agendamento agendamento = new Agendamento();
	
	public Agendamento getAgendamento() {
		return this.agendamento;
	}
	
	public void grava() {
		DAO<Agendamento> dao = new DAO<Agendamento>(Agendamento.class);
		dao.adiciona(agendamento);
	}
	
	public void altera() {
		DAO<Agendamento> dao = new DAO<Agendamento>(Agendamento.class);
		dao.atualiza(agendamento);
	}
}
