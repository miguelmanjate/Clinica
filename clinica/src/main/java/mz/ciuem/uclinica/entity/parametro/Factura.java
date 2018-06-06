package mz.ciuem.uclinica.entity.parametro;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import mz.ciuem.uclinica.entity.GenericEntity;
import mz.ciuem.uclinica.entity.consulta.Consulta;
import mz.ciuem.uclinica.entity.consulta.ItemConsultaServico;
import mz.ciuem.uclinica.entity.paciente.Paciente;

@Entity
@Table(name="factura_factura")
@Access(AccessType.FIELD)
public class Factura extends GenericEntity {
	
	@Column(name="numero_factura")
	private Long numeroDaFactura;
	
	@Column(name="data_inicio ")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataInicio;
	
	@Column(name="data_fim")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFim;
	
	private double total;
	
	@Transient
	private String dataString;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="consulta_id")
	private Consulta consulta;
	
	@Column(name = "forma_de_pagamento")
	@Enumerated(EnumType.STRING)
	private FormaPagamento formaPagamento;
	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getDataString() {
		return dataString;
	}

	public void setDataString(String dataString) {
		this.dataString = dataString;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Long getNumeroDaFactura() {
		return numeroDaFactura;
	}

	public void setNumeroDaFactura(Long numeroDaFactura) {
		this.numeroDaFactura = numeroDaFactura;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public double getTotal() {
		
		this.total = 0;
		
		for(ItemConsultaServico item : this.consulta.getItemConsultaServicos()){
			
			if(item != null){
				this.total = this.total + item.getPreco();
			}
		}
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}


}
