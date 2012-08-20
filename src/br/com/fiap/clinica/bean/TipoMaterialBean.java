package br.com.fiap.clinica.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.dao.JPAUtil;
import br.com.fiap.clinica.modelo.MaterialTipo;

@ManagedBean
@ViewScoped
public class TipoMaterialBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private MaterialTipo tipoMaterial;
	private EntityManager em = new JPAUtil().getEntityManager();
	private DAO<MaterialTipo> dao = new DAO<MaterialTipo>(MaterialTipo.class, em);
	
	private List<MaterialTipo> tipos;
	
	public TipoMaterialBean() {
		limpaForm();
	}
	
	public MaterialTipo getTipoMaterial() {
		return this.tipoMaterial;
	}
	
	public void grava() {
		try{
			em.getTransaction().begin();
			
			if (this.tipoMaterial.getId() == null) {			
				dao.adiciona(tipoMaterial);
			}else{			
				dao.atualiza(tipoMaterial);
			}
			
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}
		
		limpaForm();
	}
	
	public void limpaForm(){
		this.tipoMaterial = new MaterialTipo();
		this.tipos = dao.lista();
	}
	
	public List<MaterialTipo> getTipos(){
		return tipos;
	}
	
	public void edita(MaterialTipo aEditar){
		this.tipoMaterial = aEditar;
	}
	
	public void remove(MaterialTipo aRemover){
		try{
			em.getTransaction().begin();
			
			this.tipoMaterial = dao.buscaPeloId(aRemover.getId()); 
			dao.remove(this.tipoMaterial);			
			
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}
		
		limpaForm();
	}
}
