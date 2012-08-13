package br.com.fiap.clinica.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	private String senha;
	
	private String login;
	
	private Boolean admin;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}

	public Boolean isAdmin() {
		return admin;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Usuario)){
			return false;
		}
		
		Usuario user = (Usuario)obj;
		
		return this.id.equals(user.id) && this.nome.equals(user.nome);
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode() + (this.nome.hashCode() / 10);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
