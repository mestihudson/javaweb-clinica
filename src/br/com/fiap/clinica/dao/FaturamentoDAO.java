package br.com.fiap.clinica.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.clinica.modelo.Convenio;
import br.com.fiap.clinica.modelo.Medico;

public class FaturamentoDAO {

	private EntityManager em = new JPAUtil().getEntityManager();
	
	private List<Convenio> convenios;
	
	private List<Medico> medicos;
	
	
}
