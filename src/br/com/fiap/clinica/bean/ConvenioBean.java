package br.com.fiap.clinica.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.dao.JPAUtil;
import br.com.fiap.clinica.modelo.Convenio;

@ManagedBean
@ViewScoped
public class ConvenioBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Convenio convenio;
	private EntityManager em = new JPAUtil().getEntityManager();
	DAO<Convenio> dao = new DAO<Convenio>(Convenio.class, em);
	
	
	private List<Convenio> convenios;
	
	public ConvenioBean() {
		limpaForm();
	}
	
	public Convenio getConvenio() {
		return this.convenio;
	}
	
	public void grava() {
		try{
			em.getTransaction().begin();
			
			if (this.convenio.getId() == null) {			
				dao.adiciona(convenio);
			}else{			
				dao.atualiza(convenio);
			}
			
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}
		
		limpaForm();
	}
	
	public void limpaForm(){
		this.convenio = new Convenio();
		this.convenios = dao.lista();
	}
	
	public List<Convenio> getConvenios(){
		return convenios;
	}
	
	public void edita(Convenio aEditar){
		this.convenio = aEditar;
	}
	
	public void remove(Convenio aRemover){
		try{
			em.getTransaction().begin();
			
			this.convenio = dao.buscaPeloId(aRemover.getId()); 
			dao.remove(this.convenio);			
			
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}
		
		limpaForm();
	}
}
