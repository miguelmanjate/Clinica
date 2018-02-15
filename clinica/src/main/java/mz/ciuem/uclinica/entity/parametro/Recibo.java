package mz.ciuem.uclinica.entity.parametro;

import java.sql.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mz.ciuem.uclinica.entity.GenericEntity;

@Entity
@Table(name = "recibo")
@Access(AccessType.FIELD)
public class Recibo extends GenericEntity {
	
	@Column(name ="numero_de_recibo")
	private Long numeroDeRecibo;
	
	@Column(name ="data_de_pagamento")
	private Date dataDePagamento;
	
	@Column(name ="valor_pago")
	private double valorPago;
	
	@ManyToOne
	@JoinColumn(name  = "formadepagamento")
	private FormaDePagamento formaDePagamento;
	
	@ManyToOne
	private Factura factura;

	public Long getNumeroDeRecibo() {
		return numeroDeRecibo;
	}

	public void setNumeroDeRecibo(Long numeroDeRecibo) {
		this.numeroDeRecibo = numeroDeRecibo;
	}

	public Date getDataDePagamento() {
		return dataDePagamento;
	}

	public void setDataDePagamento(Date dataDePagamento) {
		this.dataDePagamento = dataDePagamento;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
	

}
