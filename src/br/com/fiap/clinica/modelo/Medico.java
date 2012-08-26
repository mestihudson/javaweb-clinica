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
@Table(name="medico")
public class Medico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	private String sobrenome;
	
	private Boolean ativo;
	
	@ManyToOne
	@JoinColumn(name = "id_especialidade")
	private Especialidade especialidade;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="medico")
	private Collection<Agendamento> agendamentos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Collection<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(Collection<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}
	
	public Especialidade getEspecialidade(){
		return especialidade;
	}
	
	public void setEspecialidade(Especialidade especialidade){
		this.especialidade = especialidade;
	}
	
}
