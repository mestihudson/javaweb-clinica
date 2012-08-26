package br.com.fiap.clinica.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.dao.JPAUtil;
import br.com.fiap.clinica.modelo.Tratamento;

@ManagedBean
@ViewScoped
public class TratamentoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Tratamento tratamento = new Tratamento();
	private EntityManager em = new JPAUtil().getEntityManager();
	DAO<Tratamento> dao = new DAO<Tratamento>(Tratamento.class, em);
	
	private List<Tratamento> tratamentos;
	
	public TratamentoBean() {
		limpaForm();
	}
	
	public Tratamento getTratamento() {
		return this.tratamento;
	}
	
	public void grava() {
		try{
			em.getTransaction().begin();
				
			if (this.tratamento.getId() == null) {			
				dao.adiciona(tratamento);
			}else{			
				dao.atualiza(tratamento);
			}
			
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}
		
		limpaForm();
	}
	
	public void limpaForm(){
		this.tratamento = new Tratamento();
		this.tratamentos = dao.lista();
	}
	
	public List<Tratamento> getTratamentos(){
		return tratamentos;
	}
	
	public void edita(Tratamento aEditar){
		this.tratamento = aEditar;
	}
	
	public void remove(Tratamento aRemover){
		try{
			em.getTransaction().begin();
			
			this.tratamento = dao.buscaPeloId(aRemover.getId()); 
			dao.remove(this.tratamento);			
			
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}
		
		limpaForm();
	}
}
