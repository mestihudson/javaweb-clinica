package br.com.fiap.clinica.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.dao.JPAUtil;
import br.com.fiap.clinica.modelo.Especialidade;
import br.com.fiap.clinica.modelo.Medico;

@ManagedBean
@ViewScoped
public class MedicoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Medico medico;
	private Especialidade especialidade; 
	private EntityManager      em     = new JPAUtil().getEntityManager();
	private DAO<Medico>        dao    = new DAO<Medico>(Medico.class, em);
	private DAO<Especialidade> daoEsp = new DAO<Especialidade>(Especialidade.class, em);
	
	private List<Medico> medicos;
	private List<Especialidade> especialidades;
	
	public MedicoBean() {
		limpaForm();
	}
	
	public Especialidade getEspecialidade(){
		return this.especialidade;
	}
	
	public void setEspecialidade(Especialidade especialidade){
		this.especialidade = especialidade;
	}
	
	public List<Especialidade> getEspecialidades() {
		return this.especialidades;
	}
	
	public List<Medico> getMedicos() {
		return this.medicos;
	}
	
	public void limpaForm() {
		this.medico = new Medico();
		this.medicos = dao.lista();
		this.especialidades = daoEsp.lista();
		this.especialidade = new Especialidade();
	}
	
	public Medico getMedico() {
		return this.medico;
	}
	
	public void grava() {
		try {
			em.getTransaction().begin();
			this.medico.setEspecialidade(daoEsp.buscaPeloId(this.especialidade.getId()));
			if (this.medico.getId() == null){
				dao.adiciona(medico);
			} else {
				dao.atualiza(medico);
			}
			
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		limpaForm();
	}
	
	public void altera() {
		dao.atualiza(medico);
	}
	
	public void edita(Medico aEditar){
		this.medico = aEditar;
		this.especialidade.setId(aEditar.getEspecialidade().getId());
	}
	
	public void remove(Medico aRemover){
		try {
			em.getTransaction().begin();
			this.medico = dao.buscaPeloId(aRemover.getId());
			dao.remove(this.medico);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		limpaForm();
	}
}
