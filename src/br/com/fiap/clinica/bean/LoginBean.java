package br.com.fiap.clinica.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.fiap.clinica.dao.UsuarioDAO;
import br.com.fiap.clinica.modelo.Usuario;


@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario = new Usuario();
		
	
	public String login() {
		UsuarioDAO dao = new UsuarioDAO();
		boolean loginOk = dao.existe(usuario);
		
		if (loginOk) {
			return "inicioAdministrador?faces-redirect=true";			
		} else {
			return "login";
		}
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public Boolean isLogado() {
		return usuario.getLogin() != null;
	}
}
