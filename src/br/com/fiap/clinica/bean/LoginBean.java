package br.com.fiap.clinica.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.fiap.clinica.modelo.Usuario;

@ManagedBean
@SessionScoped
public class LoginBean {

	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public String login(){
		return "cadastroConvenio";
	}
}
