package br.com.fiap.clinica.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.fiap.clinica.modelo.Usuario;

public class UsuarioDAO {

	public Boolean existe(Usuario usuario) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("from Usuario usu where usu.login = :pLogin and usu.senha = :pSenha");
		query.setParameter("pLogin", usuario.getLogin());
		query.setParameter("pSenha", usuario.getSenha());
		boolean encontrado = !query.getResultList().isEmpty();
		
		em.getTransaction().commit();
		em.close();
		
		return encontrado;
	}
}
