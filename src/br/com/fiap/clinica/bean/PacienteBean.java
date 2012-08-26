package br.com.fiap.clinica.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.dao.JPAUtil;
import br.com.fiap.clinica.modelo.Paciente;

@ManagedBean
@ViewScoped
public class PacienteBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Paciente paciente = new Paciente();
	private EntityManager em = new JPAUtil().getEntityManager();
	DAO<Paciente> dao = new DAO<Paciente>(Paciente.class, em);
	
	private List<Paciente> pacientes;
	
	public PacienteBean() {
		limpaForm();
	}
	
	public Paciente getPaciente() {
		return this.paciente;
	}
	
	public void grava() {
		try{
			em.getTransaction().begin();
			
			if (this.paciente.getId() == null) {			
				dao.adiciona(paciente);
			}else{			
				dao.atualiza(paciente);
			}
			
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}
		
		limpaForm();
	}
	
	public void limpaForm(){
		this.paciente = new Paciente();
		this.pacientes = dao.lista();
	}
	
	public List<Paciente> getPacientes(){
		return pacientes;
	}
	
	public void edita(Paciente aEditar){
		this.paciente = aEditar;
	}
	
	public void remove(Paciente aRemover){
		try{
			em.getTransaction().begin();
			
			this.paciente = dao.buscaPeloId(aRemover.getId()); 
			dao.remove(this.paciente);			
			
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}
		
		limpaForm();
	}
}
