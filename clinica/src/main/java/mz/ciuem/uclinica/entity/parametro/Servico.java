package mz.ciuem.uclinica.entity.parametro;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import mz.ciuem.uclinica.entity.GenericEntity;
import mz.ciuem.uclinica.entity.consulta.Consulta;
import mz.ciuem.uclinica.entity.consulta.Especialidade;
import mz.ciuem.uclinica.entity.consulta.Taxa;

@Entity
@Table(name = "param_servico")
@Access(AccessType.FIELD)
public class Servico extends GenericEntity {

//	@NotNull(message = "Mandatorio informar a Designacao")
//	@NotBlank(message = "Mandatorio informar a Designacao")
	@Column(name = "servico_designacao")
	private String designacao;

	// @NotNull(message = "Mandatorio informar o codigo do servico")
	// @NotBlank(message = "Mandatorio informar o codigo do servico")
	private String codigo;

	@ManyToOne
	@JoinColumn(name = "tiposervico")
	private ServicoDaUnidade servicoDaUnidade;

	@OneToMany(mappedBy = "servico", fetch = FetchType.LAZY)
	private List<Taxa> taxas;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sector_id")
	private Sector sector;

	@Column(name = "servico_especialidade")
	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;
	
	@ManyToMany
	private List<Consulta> consultas;

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public List<Taxa> getTaxas() {
		return taxas;
	}

	public void setTaxas(List<Taxa> taxas) {
		this.taxas = taxas;
	}

	public ServicoDaUnidade getServicoDaUnidade() {
		return servicoDaUnidade;
	}

	public void setServicoDaUnidade(ServicoDaUnidade servicoDaUnidade) {
		this.servicoDaUnidade = servicoDaUnidade;
	}

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
	
	

}
