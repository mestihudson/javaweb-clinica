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
@Table(name = "especialidade")
public class Especialidade implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_area")
	private Area area;

	private Boolean ativa;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "especialidade")
	private Collection<EspecialidadeTratamento> especialidadeTratamentos;

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

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Collection<EspecialidadeTratamento> getEspecialidadeTratamentos() {
		return especialidadeTratamentos;
	}

	public void setEspecialidadeTratamentos(
			Collection<EspecialidadeTratamento> especialidadeTratamentos) {
		this.especialidadeTratamentos = especialidadeTratamentos;
	}
}
