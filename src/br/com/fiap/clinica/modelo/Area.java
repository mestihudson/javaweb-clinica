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
@Table(name="area")
public class Area implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	
	private String descricao;
	
	private Boolean ativa;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="area")
	private Collection<Especialidade> especialidades;

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

	public Boolean getAtiva() {
		return ativa;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}

	public Collection<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Collection<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}
}
