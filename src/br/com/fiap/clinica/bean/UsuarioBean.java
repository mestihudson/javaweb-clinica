package br.com.fiap.clinica.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.dao.JPAUtil;
import br.com.fiap.clinica.modelo.Usuario;

@ManagedBean
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario usuario ;
	private EntityManager em = new JPAUtil().getEntityManager();
	DAO<Usuario> dao = new DAO<Usuario>(Usuario.class, em);
	private List<Usuario> usuarios;
	
	public UsuarioBean() {
		limpaForm();
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public void grava() {
		
		try {
			em.getTransaction().begin();
			
			if (usuario.getId() == null) {
				dao.adiciona(usuario);
			} else {
				dao.atualiza(usuario);
			}
			
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();			
		}
		
		limpaForm();
	}
	
	public void edita(Usuario usuario){
		this.usuario = usuario;
	}
	
	public void remove() {
	
		try {
			em.getTransaction().begin();			
			dao.remove(usuario);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void limpaForm() {
		usuario = new Usuario();
		usuarios = dao.lista(); 
	}
}
