package mz.ciuem.uclinica.entity.consulta;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mz.ciuem.uclinica.controller.parametro.servicos.TipoCliente;
import mz.ciuem.uclinica.entity.GenericEntity;
import mz.ciuem.uclinica.entity.parametro.Servico;

@Entity
@Table(name = "servico_taxa")
@Access(AccessType.FIELD)
public class Taxa extends GenericEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Column(name = "servico_taxa_normal")
	private Double taxaNormal;
	
	
	@Column(name = "servico_taxa_urgente")
	private Double taxaUrgente;
	
	@Column(name  = "servico_taxa_retorno")
	private Double taxaRetorno;
	
	@Column(name = "servico_taxa_segunda_via")
	private Double taxaSegundaVia;
	
	@Column(name = "servico_taxa_tipo_cliente")
	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;
	
//	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//	@JoinColumn(name  = "servico_taxa_id")
//	private Servico servico;
	
	public Double getTaxaNormal() {
		return taxaNormal;
	}

	public void setTaxaNormal(Double taxaNormal) {
		this.taxaNormal = taxaNormal;
	}

	public Double getTaxaUrgente() {
		return taxaUrgente;
	}

	public void setTaxaUrgente(Double taxaUrgente) {
		this.taxaUrgente = taxaUrgente;
	}

	public Double getTaxaRetorno() {
		return taxaRetorno;
	}

	public void setTaxaRetorno(Double taxaRetorno) {
		this.taxaRetorno = taxaRetorno;
	}

	public Double getTaxaSegundaVia() {
		return taxaSegundaVia;
	}

	public void setTaxaSegundaVia(Double taxaSegundaVia) {
		this.taxaSegundaVia = taxaSegundaVia;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
	

}
