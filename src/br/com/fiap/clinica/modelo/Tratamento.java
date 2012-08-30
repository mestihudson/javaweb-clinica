package br.com.fiap.clinica.modelo;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.Entity;

@Entity
@Table(name = "tratamento")
public class Tratamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String descricao;

	@Column(name = "valor_remuneracao")
	private Double valorRemuneracao;

	private Boolean ativo;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tratamento", fetch=FetchType.EAGER)
	private Collection<EspecialidadeTratamento> especialidadeTratamentos;
	
	@ManyToMany(mappedBy="tratamentos")
	private Collection<Atendimento> atendimentos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValorRemuneracao() {
		return valorRemuneracao;
	}

	public void setValorRemuneracao(Double valorRemuneracao) {
		this.valorRemuneracao = valorRemuneracao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Collection<EspecialidadeTratamento> getEspecialidadeTratamentos() {
		return especialidadeTratamentos;
	}

	public void setEspecialidadeTratamentos(
			Collection<EspecialidadeTratamento> especialidadeTratamentos) {
		this.especialidadeTratamentos = especialidadeTratamentos;
	}

	public Collection<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(Collection<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}
	
}
