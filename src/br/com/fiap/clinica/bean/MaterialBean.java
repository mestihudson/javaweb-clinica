package br.com.fiap.clinica.bean;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.dao.JPAUtil;
import br.com.fiap.clinica.modelo.Material;

@ManagedBean
public class MaterialBean {

	private Material material = new Material();
	private EntityManager em = new JPAUtil().getEntityManager();
	DAO<Material> dao = new DAO<Material>(Material.class, em);
	
	public Material getMaterial() {
		return this.material;
	}
	
	public void grava() {
		dao.adiciona(material);
	}
	
	public void altera() {
		dao.atualiza(material);
	}

}
