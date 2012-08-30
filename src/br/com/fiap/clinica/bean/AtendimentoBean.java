package br.com.fiap.clinica.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.dao.JPAUtil;
import br.com.fiap.clinica.modelo.Agendamento;
import br.com.fiap.clinica.modelo.Atendimento;
import br.com.fiap.clinica.modelo.Material;
import br.com.fiap.clinica.modelo.Tratamento;

@ManagedBean
@SessionScoped
public class AtendimentoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Atendimento atendimento;
	private EntityManager em = new JPAUtil().getEntityManager();
	
	private DAO<Atendimento> dao = new DAO<Atendimento>(Atendimento.class, em);
	private DAO<Agendamento> daoAg = new DAO<Agendamento>(Agendamento.class, em);
	private DAO<Tratamento>  daoTr = new DAO<Tratamento>(Tratamento.class, em);
	private DAO<Material>  daoMedicamento = new DAO<Material>(Material.class, em);
	
	private List<Material> medicamentos;
	private List<Tratamento> tratamentos;
	
	private List<Long> tratamentosSelecionados;
	private List<Long> medicamentosSelecionados;

	public AtendimentoBean(){
		this.atendimento = new Atendimento();
		limpaForm();
	}
	
	public void atende(Agendamento agendamento){
		this.atendimento.setAgendamento(agendamento);
	}
	
	public Atendimento getAtendimento() {
		return this.atendimento;
	}
	
	public List<Tratamento> getTratamentos(){
		return this.tratamentos;
	}
	
	public List<Material> getMedicamentos(){
		return this.medicamentos;
	}
	
	public List<Long> getTratamentosSelecionados(){
		return this.tratamentosSelecionados;
	}
	
	public void setTratamentosSelecionados(List<String> itensSelecionados){
		this.tratamentosSelecionados.clear();
		for (String s : itensSelecionados) {
			this.tratamentosSelecionados.add(Long.parseLong(s));
		}
	}
	
	public List<Long> getMedicamentosSelecionados(){
		return this.medicamentosSelecionados;
	}
	
	public void setMedicamentosSelecionados(List<String> itensSelecionados){
		this.medicamentosSelecionados.clear();
		for (String s : itensSelecionados) {
			this.medicamentosSelecionados.add(Long.parseLong(s));
		}
	}
	
	public void grava() {
		try {
			em.getTransaction().begin();
			
			this.atendimento.setAtivo(true);
			
			for (Long tra : this.tratamentosSelecionados) {				
				relacionaTratamentoAoAtendimento(daoTr.buscaPeloId(tra));
			}

			for (Long material : this.medicamentosSelecionados) {				
				relacionaMedicamentoAoAtendimento(daoMedicamento.buscaPeloId(material));
			}
			
			if (this.atendimento.getId() == null) {
				dao.adiciona(atendimento);
			} else {
				dao.atualiza(atendimento);
			}
			
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		limpaForm();
	}
	
	public void limpaForm(){
		this.tratamentos  = daoTr.lista();
		this.medicamentos = daoMedicamento.lista();
		this.tratamentosSelecionados = new ArrayList<Long>();
		this.medicamentosSelecionados = new ArrayList<Long>();
	}
	
	private void relacionaTratamentoAoAtendimento(Tratamento tratamento){
		this.atendimento.getTratamentos().add(tratamento);
	}
	
	private void relacionaMedicamentoAoAtendimento(Material material){
		this.atendimento.getMateriais().add(material);
	}
}