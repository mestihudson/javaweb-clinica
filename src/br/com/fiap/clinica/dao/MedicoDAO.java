package br.com.fiap.clinica.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.fiap.clinica.modelo.Medico;

public class MedicoDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private final EntityManager em;
	private DAO<Medico> dao;
	
	public MedicoDAO(EntityManager em) {
		this.em = em;
		this.dao = new DAO<Medico>(Medico.class, em);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Medico> buscaMedicosPelaArea(Long id){
		String jpql = "SELECT m FROM Medico m WHERE m.especialidade.area.id = :idarea";
		Query query = em.createQuery(jpql);
		query.setParameter("idarea", id);
		
		return (List<Medico>)query.getResultList();
	}
	
	public List<Medico> lista(){
		return dao.lista();
	}
	
	public void adiciona(Medico aInserir){
		dao.adiciona(aInserir);
	}
	
	public void atualiza(Medico aEditar){
		dao.atualiza(aEditar);
	}
	
	public void remove(Medico aRemover){
		dao.remove(aRemover);
	}
	
	public Medico buscaPeloId(Long id){
		return dao.buscaPeloId(id);
	}
}
