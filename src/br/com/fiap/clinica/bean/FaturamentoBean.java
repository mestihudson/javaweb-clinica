package br.com.fiap.clinica.bean;

import java.io.Serializable;
import java.util.List;

import br.com.fiap.clinica.dao.FaturamentoDAO;
import br.com.fiap.clinica.modelo.Convenio;
import br.com.fiap.clinica.modelo.Medico;

public class FaturamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private FaturamentoDAO dao = new FaturamentoDAO();

	public List<Convenio> getFaturamentoPorConvenio() {
		
		return dao.getConveniosFaturados();
	}
	
	public List<Medico> getFaturamentoPorMedico() {
		
		return dao.getMedicosFaturados();
	}
}
