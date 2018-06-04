package mz.ciuem.uclinica.entity.consulta;

import javax.persistence.Access;
import javax.persistence.Transient;
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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import mz.ciuem.uclinica.controller.parametro.servicos.TipoCliente;
import mz.ciuem.uclinica.entity.GenericEntity;
import mz.ciuem.uclinica.entity.parametro.Servico;

@Entity
@Table(name = "servico_taxa")
@Access(AccessType.FIELD)
public class Taxa extends GenericEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = " Campo Obrigatorio")
	@Column(name = "servico_taxa_normal")
	private Double taxaNormal;

	@NotNull(message = "Campo Obrigatorio")
	@Column(name = "servico_taxa_urgente")
	private Double taxaUrgente;

	@NotNull(message = " Campo Obrigatorio")
	@Column(name = "servico_taxa_controle")
	private Double taxaControle;

	@NotNull(message = " Campo Obrigatorio")
	@Column(name = "servico_taxa_tipo_cliente")
	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;

	@ManyToOne()
	@JoinColumn(name = "servico_taxa_id")
	private Servico servico;

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

	public Double getTaxaControle() {
		return taxaControle;
	}

	public void setTaxaControle(Double taxaControle) {
		this.taxaControle = taxaControle;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	
	@Transient
	public Double getTaxaPorTipoConsulta(String tipoConsulta) {
		TipoConsulta tipo = TipoConsulta.valueOf(tipoConsulta);

		switch (tipo) {
		case NORMAL:
			
			return this.getTaxaNormal();
			
		case URGENTE:
			
			return this.getTaxaUrgente();
			
		case CONTROLE:
			
			return this.getTaxaControle();
		
		default :
			return 0D;
		}
	}

}
