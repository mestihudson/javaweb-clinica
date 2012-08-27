package br.com.fiap.clinica.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import java.io.Serializable;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.dao.JPAUtil;
import br.com.fiap.clinica.modelo.Agendamento;
import br.com.fiap.clinica.modelo.Atendimento;

@ManagedBean
@ViewScoped
public class AtendimentoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Atendimento atendimento = new Atendimento();
	private EntityManager em = new JPAUtil().getEntityManager();
	private DAO<Atendimento> dao = new DAO<Atendimento>(Atendimento.class, em);
	private DAO<Agendamento> daoAg = new DAO<Agendamento>(Agendamento.class, em);

	public AtendimentoBean(){
	//public AtendimentoBean(Long agendamentoId){
		//Long id = new Long(2);
		//this.atendimento = new Atendimento();
		//this.atendimento.setAgendamento(daoAg.buscaPeloId(id));
	}
	
	public Atendimento getAtendimento() {
		return this.atendimento;
	}
	
	public void grava() {
		try {
			em.getTransaction().begin();
			if (this.atendimento.getId() == null) {
				dao.adiciona(atendimento);
			} else {
				dao.atualiza(atendimento);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
				em.getTransaction().rollback();
		}
		limpaForm();
	}
	
	public void limpaForm(){
		this.atendimento = new Atendimento();
	}
}
