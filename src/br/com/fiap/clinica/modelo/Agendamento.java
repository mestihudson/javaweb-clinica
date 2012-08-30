package br.com.fiap.clinica.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name =  "agendamento")
public class Agendamento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "data_consulta")
	private Date dataConsulta;

	@Column(name = "numero_convenio")
	private Long numeroConvenio;

	@ManyToOne
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "id_convenio")
	private Convenio convenio;

	@ManyToOne
	@JoinColumn(name = "id_medico")
	private Medico medico;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "agendamento", fetch=FetchType.EAGER)
	private Collection<Atendimento> atendimentos;

	@Enumerated
	@Column(name="id_agendamento_status")
	private AgendamentoStatus agendamentoStatus;

	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public Long getNumeroConvenio() {
		return numeroConvenio;
	}

	public void setNumeroConvenio(Long numeroConvenio) {
		this.numeroConvenio = numeroConvenio;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public AgendamentoStatus getAgendamentoStatus() {
		return agendamentoStatus;
	}

	public void setAgendamentoStatus(AgendamentoStatus agendamentoStatus) {
		this.agendamentoStatus = agendamentoStatus;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Collection<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(Collection<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

}
