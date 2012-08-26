package br.com.fiap.clinica.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.dao.JPAUtil;
import br.com.fiap.clinica.modelo.Area;
import br.com.fiap.clinica.modelo.Especialidade;

@ManagedBean	
@ViewScoped
public class EspecialidadeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Especialidade especialidade;
	private EntityManager em = new JPAUtil().getEntityManager();
	private DAO<Especialidade> dao = new DAO<Especialidade>(Especialidade.class, em);
	private DAO<Area> daoArea= new DAO<Area>(Area.class, em);
	
	private List<Especialidade> especialidades;
	private List<Area> areas;
	
	private Area area;
	
	public EspecialidadeBean() {
		limpaForm();
	}
	
	public Especialidade getEspecialidade() {
		return this.especialidade;
	}
	
	public List<Area> getAreas(){
		return this.areas;
	}
	
	public Area getArea(){
		return this.area;
	}
	
	public void setArea(Area area){
		this.area = area;
	}
	
	public void grava() {
		try{
			em.getTransaction().begin();
			
			this.especialidade.setArea(daoArea.buscaPeloId(this.area.getId())); 
			if (this.especialidade.getId() == null) {			
				dao.adiciona(especialidade);
			}else{
				dao.atualiza(especialidade);
			}
			
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
		
		limpaForm();
	}
	
	public void limpaForm(){
		this.especialidade = new Especialidade();
		this.especialidades = dao.lista();
		this.areas = daoArea.lista();
		this.area = new Area();
	}
	
	public List<Especialidade> getEspecialidades(){
		return especialidades;
	}
	
	public void edita(Especialidade aEditar){
		this.especialidade = aEditar;
		this.area.setId(aEditar.getArea().getId());
	}
	
	public void remove(Especialidade aRemover){
		try{
			em.getTransaction().begin();
			
			this.especialidade = dao.buscaPeloId(aRemover.getId()); 
			dao.remove(this.especialidade);			
			
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}
		
		limpaForm();
	}	
}
