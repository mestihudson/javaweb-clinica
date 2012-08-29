package br.com.fiap.clinica.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.dao.JPAUtil;
import br.com.fiap.clinica.modelo.Material;
import br.com.fiap.clinica.modelo.MaterialTipo;

@ManagedBean	
@ViewScoped
public class MaterialBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Material material;
	private EntityManager em = new JPAUtil().getEntityManager();
	private DAO<Material> dao = new DAO<Material>(Material.class, em);
	private DAO<MaterialTipo> daoTipoMaterial= new DAO<MaterialTipo>(MaterialTipo.class, em);
	
	private List<Material> materiais;
	private List<MaterialTipo> tiposMateriais;
	
	private MaterialTipo tipo;
	
	public MaterialBean() {
		limpaForm();
	}
	
	public Material getMaterial() {
		return this.material;
	}
	
	public List<MaterialTipo> getTiposMateriais(){
		return this.tiposMateriais;
	}
	
	public MaterialTipo getTipo(){
		return this.tipo;
	}
	
	public void setTipo(MaterialTipo tipo){
		this.tipo = tipo;
	}
	
	public void grava() {
		try{
			em.getTransaction().begin();
			
			this.material.setMaterialTipo(daoTipoMaterial.buscaPeloId(this.tipo.getId())); 
			if (this.material.getId() == null) {			
				dao.adiciona(material);
			}else{
				dao.atualiza(material);
			}
			
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}
		
		limpaForm();
	}
	
	public void limpaForm(){
		this.material = new Material();
		this.materiais = dao.lista();
		this.tiposMateriais = daoTipoMaterial.lista();
		this.tipo = new MaterialTipo();
	}
	
	public List<Material> getMateriais(){
		return materiais;
	}
	
	public void edita(Material aEditar){
		this.material = aEditar;
		this.tipo.setId(aEditar.getMaterialTipo().getId());
	}
	
	public void remove(Material aRemover){
		try{
			em.getTransaction().begin();
			
			this.material = dao.buscaPeloId(aRemover.getId()); 
			dao.remove(this.material);			
			
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}
		
		limpaForm();
	}	
}
