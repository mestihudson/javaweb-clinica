package br.com.fiap.clinica.modelo;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "material")
public class Material implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String descricao;

	@Column(name = "valor_remuneracao")
	private double valorRemuneracao;

	private Boolean ativo;

	@ManyToOne
	@JoinColumn(name = "id_material_tipo")
	private MaterialTipo materialTipo;

	@ManyToMany(mappedBy="materiais")
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

	public double getValorRemuneracao() {
		return valorRemuneracao;
	}

	public void setValorRemuneracao(double valorRemuneracao) {
		this.valorRemuneracao = valorRemuneracao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public MaterialTipo getMaterialTipo() {
		return materialTipo;
	}

	public void setMaterialTipo(MaterialTipo materialTipo) {
		this.materialTipo = materialTipo;
	}

	public Collection<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(Collection<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}
}
