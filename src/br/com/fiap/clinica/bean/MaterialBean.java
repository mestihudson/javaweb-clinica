package br.com.fiap.clinica.bean;

import javax.faces.bean.ManagedBean;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.modelo.Material;

@ManagedBean
public class MaterialBean {

	private Material material;
	
	public Material getMaterial() {
		return this.material;
	}
	
	public void grava() {
		DAO<Material> dao = new DAO<Material>(Material.class);
		dao.adiciona(material);
	}
	
	public void altera() {
		DAO<Material> dao = new DAO<Material>(Material.class);
		dao.atualiza(material);
	}

}
