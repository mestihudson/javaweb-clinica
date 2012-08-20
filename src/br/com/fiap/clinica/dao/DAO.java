package br.com.fiap.clinica.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private final Class<T> classe;

	private final EntityManager em;
	
	public DAO(Class<T> classe, EntityManager em) {
		this.classe = classe;
		this.em = em;
	}
	
	public void adiciona(T t) {
		em.persist(t);
	}
	
	public void atualiza(T t) {
		em.merge(t);
	}
	
	public void remove(T t) {
		em.remove(t);
	}
	
	public T buscaPeloId(Long id){
		return em.find(classe, id);
	}
	
	public List<T> lista() {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		
		List<T> lista = em.createQuery(query).getResultList();
		
		return lista;		
	}
}
