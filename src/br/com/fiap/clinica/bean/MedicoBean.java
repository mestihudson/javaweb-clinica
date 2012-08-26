package br.com.fiap.clinica.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.dao.JPAUtil;
import br.com.fiap.clinica.modelo.MaterialTipo;
import br.com.fiap.clinica.modelo.Medico;

@ManagedBean
public class MedicoBean implements Serializable {

	private Medico medico;
	private EntityManager em = new JPAUtil().getEntityManager();
	private DAO<Medico> dao = new DAO<Medico>(Medico.class, em);
	
	private List<Medico> medicos;
	
	public MedicoBean() {
		limpaForm();
	}
	
	public void limpaForm() {
		this.medico = new Medico();
	}
	
	public Medico getMedico() {
		return this.medico;
	}
	
	public void grava() {
		try {
			em.getTransaction().begin();
			if (this.medico.getId() == null){
				dao.adiciona(medico);
			} else {
				dao.atualiza(medico);
			}
			
			em.getTransaction().commit();
		} catch (Exception e) {
			limpaForm();
		}
	}
	
	public void altera() {
		dao.atualiza(medico);
	}
	
	public void edita(Medico aEditar){
		this.medico = aEditar;
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
