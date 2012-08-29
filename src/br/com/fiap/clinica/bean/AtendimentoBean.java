package br.com.fiap.clinica.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.dao.JPAUtil;
import br.com.fiap.clinica.modelo.Agendamento;
import br.com.fiap.clinica.modelo.Atendimento;
import br.com.fiap.clinica.modelo.AtendimentoMaterial;
import br.com.fiap.clinica.modelo.Tratamento;

@ManagedBean
@ViewScoped
public class AtendimentoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Atendimento atendimento;
	private EntityManager em = new JPAUtil().getEntityManager();
	private DAO<Atendimento> dao = new DAO<Atendimento>(Atendimento.class, em);
	private DAO<Agendamento> daoAg = new DAO<Agendamento>(Agendamento.class, em);
	private DAO<Tratamento> daoTr = new DAO<Tratamento>(Tratamento.class, em);
	private List<AtendimentoMaterial> atendimentoMateriais;
	private List<Tratamento> tratamentos;

	public AtendimentoBean() {
		// public AtendimentoBean(Agendamento agendamento){
		this.atendimento = new Atendimento();
		this.atendimento.setAgendamento(daoAg.buscaPeloId(new Long(2)));
		this.tratamentos = daoTr.lista();
	}

	public Atendimento getAtendimento() {
		return this.atendimento;
	}

	public List<Tratamento> getMateriais() {
		return this.tratamentos;
	}

	public Collection<AtendimentoMaterial> getAtendimentoMateriais() {
		if (this.atendimento.getId() == null) {
			return new ArrayList();
		} else {
			return this.atendimento.getAtendimentoMateriais();
		}
	}

	public void setAtendimentoMateriais(AtendimentoMaterial item) {
		this.atendimentoMateriais.add(item);
	}

	public void grava() {
		try {
			em.getTransaction().begin();

			// for (int i = 0; i < this.atendimentoMateriais.size(); i++) {
			// adicionaSelecionado(this.atendimentoMateriais)
			// }
			this.atendimento.setAtendimentoMateriais(this.atendimentoMateriais);
			if (this.atendimento.getId() == null) {
				dao.adiciona(atendimento);
			} else {
				dao.atualiza(atendimento);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		// limpaForm();
	}

	public void limpaForm() {
		this.atendimento = new Atendimento();
	}
}
