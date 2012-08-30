package br.com.fiap.clinica.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "atendimento")
public class Atendimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private Boolean ativo;

	@ManyToOne
	@JoinColumn(name = "id_agendamento")
	private Agendamento agendamento;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "atendimento")
	private Collection<AtendimentoMaterial> atendimentoMateriais;
	
	@ManyToMany
	@JoinTable(
		name="atendimento_tratamento",
		joinColumns={@JoinColumn(name="id_atendimento", referencedColumnName="id")},
		inverseJoinColumns={@JoinColumn(name="id_tratamento", referencedColumnName="id")})
	private Collection<Tratamento> tratamentos = new ArrayList<Tratamento>();

	@ManyToMany
	@JoinTable(
		name="atendimento_material",
		joinColumns={@JoinColumn(name="id_atendimento", referencedColumnName="id")},
		inverseJoinColumns={@JoinColumn(name="id_material", referencedColumnName="id")})
	private Collection<Material> materiais = new ArrayList<Material>();
	
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

	public Collection<Tratamento> getTratamentos() {
		return tratamentos;
	}

	public void setTratamentos(Collection<Tratamento> tratamentos) {
		this.tratamentos = tratamentos;
	}

	public Collection<Material> getMateriais() {
		return materiais;
	}

	public void setMateriais(Collection<Material> materiais) {
		this.materiais = materiais;
	}
}
