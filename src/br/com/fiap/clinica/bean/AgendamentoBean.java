package br.com.fiap.clinica.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.com.fiap.clinica.dao.DAO;
import br.com.fiap.clinica.dao.JPAUtil;
import br.com.fiap.clinica.dao.MedicoDAO;
import br.com.fiap.clinica.modelo.Agendamento;
import br.com.fiap.clinica.modelo.AgendamentoStatus;
import br.com.fiap.clinica.modelo.Area;
import br.com.fiap.clinica.modelo.Convenio;
import br.com.fiap.clinica.modelo.Especialidade;
import br.com.fiap.clinica.modelo.Medico;
import br.com.fiap.clinica.modelo.Paciente;

@ManagedBean	
@ViewScoped
public class AgendamentoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = new JPAUtil().getEntityManager();

	private DAO<Agendamento> dao = new DAO<Agendamento>(Agendamento.class, em);
	private DAO<Area> daoArea= new DAO<Area>(Area.class, em);
	private MedicoDAO daoMedico = new MedicoDAO(em);
	private DAO<Paciente> daoPaciente= new DAO<Paciente>(Paciente.class, em);
	private DAO<Convenio> daoConvenio= new DAO<Convenio>(Convenio.class, em);
	
	private List<Area> areas;
	private List<Convenio> convenios;
	private List<Paciente> pacientes;
	private List<Medico> medicos;
	private List<Agendamento> agendamentos;
	
	private Area area;
	private Agendamento agendamento;
	private AgendamentoStatus agendamentoStatus;
	private Convenio convenio;
	private Medico medico;
	private Paciente paciente;
	
	public AgendamentoBean() {
		limpaForm();
	}
	
	public void grava() {
		try{
			em.getTransaction().begin();
			
			this.agendamento.setConvenio(daoConvenio.buscaPeloId(this.convenio.getId()));
			this.agendamento.setPaciente(daoPaciente.buscaPeloId(this.paciente.getId()));
			this.agendamento.setMedico(daoMedico.buscaPeloId(this.medico.getId()));
			this.agendamento.setAgendamentoStatus(this.agendamentoStatus);
			this.agendamento.setNumeroConvenio(this.agendamento.getNumeroConvenio());
			this.agendamento.setAtivo(true);
			
			if (this.agendamento.getId() == null) {			
				dao.adiciona(agendamento);
			}else{
				dao.atualiza(agendamento);
			}
			
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();			
		}
		
		limpaForm();
	}
	
	public void edita(Agendamento aEditar){
		this.agendamento = aEditar;
		this.convenio.setId(aEditar.getConvenio().getId());
		this.paciente.setId(aEditar.getPaciente().getId());
		this.medico.setId(aEditar.getMedico().getId());
		this.agendamentoStatus = aEditar.getAgendamentoStatus();
	}
	
	public void remove(Especialidade aRemover){
		try{
			em.getTransaction().begin();
			
			this.agendamento = dao.buscaPeloId(aRemover.getId());
			this.agendamento.setAtivo(false);
			dao.atualiza(this.agendamento);			
			
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}
		
		limpaForm();
	}
	
	public void buscaMedicos(){
		this.medicos = daoMedico.buscaMedicosPelaArea(this.area.getId());
	}
	
	public void limpaForm(){
		this.area = new Area();
		this.agendamento = new Agendamento();
		this.agendamentoStatus = AgendamentoStatus.CANCELADO;
		this.convenio = new Convenio();
		this.paciente = new Paciente();
		this.medico = new Medico();
		
		this.areas = daoArea.lista();
		this.convenios = daoConvenio.lista();
		this.pacientes = daoPaciente.lista();
		this.medicos = new ArrayList<Medico>();
		this.agendamentos = dao.lista();
	}

	public List<Area> getAreas() {
		return areas;
	}

	public List<Convenio> getConvenios() {
		return convenios;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public Area getArea() {
		return area;
	}
	
	public Agendamento getAgendamento() {
		return agendamento;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public Medico getMedico() {
		return medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}
	
	public AgendamentoStatus[] getStatus(){
		return AgendamentoStatus.values();
	}
	
	public AgendamentoStatus getAgendamentoStatus(){
		return this.agendamentoStatus;
	}
	
	public void setAgendamentoStatus(AgendamentoStatus status){
		this.agendamentoStatus = status;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}
