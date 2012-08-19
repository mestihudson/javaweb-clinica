package br.com.fiap.clinica.bean;

import javax.faces.bean.ManagedBean;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.modelo.Area;

@ManagedBean
public class AreaBean {

	private Area area = new Area();
	
	public Area getArea() {
		return this.area;
	}
	
	public void grava() {
		DAO<Area> dao = new DAO<Area>(Area.class);
		dao.adiciona(area);
	}
	
	public void altera() {
		DAO<Area> dao = new DAO<Area>(Area.class);
		dao.atualiza(area);
	}
}
