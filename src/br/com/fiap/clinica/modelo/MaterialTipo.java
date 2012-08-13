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
@Table(name="material_tipo")
public class MaterialTipo implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	
	private String descricao;
	
	private Boolean ativo;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="materialTipo")
	private Collection<Material> materiais;

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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Collection<Material> getMateriais() {
		return materiais;
	}

	public void setMateriais(Collection<Material> materiais) {
		this.materiais = materiais;
	}
}
