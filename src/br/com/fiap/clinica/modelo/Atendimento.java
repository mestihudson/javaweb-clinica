package br.com.fiap.clinica.modelo;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "atendimento")
public class Atendimento implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	private Boolean ativo;

	@ManyToOne
	@JoinColumn(name = "id_agendamento")
	private Agendamento agendamento;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "atendimento")
	private Collection<AtendimentoMaterial> atendimentoMateriais;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public Collection<AtendimentoMaterial> getAtendimentoMateriais() {
		return atendimentoMateriais;
	}

	public void setAtendimentoMateriais(
			Collection<AtendimentoMaterial> atendimentoMateriais) {
		this.atendimentoMateriais = atendimentoMateriais;
	}
}
