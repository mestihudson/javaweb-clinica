package br.com.fiap.clinica.bean;

import javax.faces.bean.ManagedBean;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.modelo.Usuario;

@ManagedBean
public class UsuarioBean {

	private Usuario usuario;
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public void grava() {
		DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
		dao.adiciona(usuario);
	}
	
	public void altera() {
		DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
		dao.atualiza(usuario);
	}
	
	public void remove() {
		DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
		dao.remove(usuario);
	}
}
