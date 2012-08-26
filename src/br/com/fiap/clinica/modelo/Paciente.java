package br.com.fiap.clinica.modelo;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="paciente")
public class Paciente implements Serializable {

	private static final long serialVersionUID = 2691108470910450405L;

	@Id
	@GeneratedValue
	private Long id;
		
	private String nome;
	
	private String sobrenome;
	
	private String endereco;
	
	private String cpf;
	
	private Boolean ativo;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="paciente")
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	
}
