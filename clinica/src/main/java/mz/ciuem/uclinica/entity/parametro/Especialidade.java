package mz.ciuem.uclinica.entity.parametro;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mz.ciuem.uclinica.entity.GenericEntity;
import mz.ciuem.uclinica.entity.consulta.Consulta;
import mz.ciuem.uclinica.entity.consulta.Medico;
import mz.ciuem.uclinica.entity.exame.Exame;
import mz.ciuem.uclinica.entity.exame.Laboratorio;
//import mz.ciuem.uclinica.entity.exame.Exame;
@Entity
@Table(name = "especialidade_especialidade")
@Access(AccessType.FIELD)
public class Especialidade extends GenericEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "especialidade_codigo")
	private String codigo;
	
	@Column(name = "especialidade_designacao")
	private String descricao;

	@Column(name = "especialidade_sigla")
	private String sigla;
	
	@Column(name = "especialidade_tipo")
	@Enumerated(EnumType.STRING)
	private ServicoTipo servicoTipo;
	
	@ManyToOne
	@JoinColumn(name = "especialidade_sector_id")
	private Sector sector;
	
	@OneToMany(mappedBy = "especialidade")
	private List<Consulta> consultas;
	
	@OneToMany(mappedBy = "especialidade")
	private List<Exame> exames;
	
	@OneToMany(mappedBy = "especialidade")
	private List<Medico> medicos;
	
	@OneToMany(mappedBy = "especialidade")
	private List<Laboratorio> laboratorios;
	
	@OneToMany(mappedBy = "especialidade")
	private List<Servico> servicos;
	
	public List<Laboratorio> getLaboratorios() {
		return laboratorios;
	}

	public void setLaboratorios(List<Laboratorio> laboratorios) {
		this.laboratorios = laboratorios;
	}

	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public ServicoTipo getServicoTipo() {
		return servicoTipo;
	}

	public void setServicoTipo(ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
	
	
	
}
