package mz.ciuem.uclinica.entity.parametro;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import mz.ciuem.uclinica.entity.GenericEntity;
import mz.ciuem.uclinica.entity.consulta.Medico;

@Entity
@Table(name ="sectors_clinica")
@Access(AccessType.FIELD)
public class Sector extends GenericEntity{
	
	@NotBlank
	@Column(name= "sector_designacao", unique = true)
	private String descricao;
	
	@Column(name = "sector_sigla")
	private String sigla;
	
	@Column(name = "sector_codigo", unique = true)
	private String codigo;
	
	@OneToMany(mappedBy = "sector")
	private List<Medico> medicos;
	
	@OneToMany(mappedBy = "sector", fetch = FetchType.EAGER)
	private List<Especialidade> especialidades;
	
	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getSigla() {
		return sigla;
	}


	public void setSigla(String sigla) {
		this.sigla = sigla;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public List<Medico> getMedicos() {
		return medicos;
	}


	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}
	

}
