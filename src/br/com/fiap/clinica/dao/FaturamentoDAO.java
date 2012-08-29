package br.com.fiap.clinica.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.fiap.clinica.modelo.Convenio;
import br.com.fiap.clinica.modelo.Medico;

public class FaturamentoDAO {

	private EntityManager em = new JPAUtil().getEntityManager();
	
	public List<Convenio> getConveniosFaturados() {
		
		Query query = em.createQuery("from Convenio convenio");
		return query.getResultList();
	}
	
	public List<Medico> getMedicosFaturados() {
		
		Query query = em.createQuery("from Medico medico");
		return query.getResultList();
	}
}
