package br.com.fiap.clinica.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.dao.JPAUtil;
import br.com.fiap.clinica.modelo.Area;

@ManagedBean
@ViewScoped
public class AreaBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Area area = new Area();
	private EntityManager em = new JPAUtil().getEntityManager();
	DAO<Area> dao = new DAO<Area>(Area.class, em);
	
	private List<Area> areas;
	
	public AreaBean() {
		limpaForm();
	}
	
	public Area getArea() {
		return this.area;
	}
	
	public void grava() {
		try{
			em.getTransaction().begin();
			
			if (this.area.getId() == null) {			
				dao.adiciona(area);
			}else{			
				dao.atualiza(area);
			}
			
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}
		
		limpaForm();
	}
	
	public void limpaForm(){
		this.area = new Area();
		this.areas = dao.lista();
	}
	
	public List<Area> getAreas(){
		return areas;
	}
	
	public void edita(Area aEditar){
		this.area = aEditar;
	}
	
	public void remove(Area aRemover){
		try{
			em.getTransaction().begin();
			
			this.area = dao.buscaPeloId(aRemover.getId()); 
			dao.remove(this.area);			
			
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}
		
		limpaForm();
	}
}
