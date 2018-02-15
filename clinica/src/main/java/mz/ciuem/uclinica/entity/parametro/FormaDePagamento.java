package mz.ciuem.uclinica.entity.parametro;

import java.sql.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import mz.ciuem.uclinica.entity.GenericEntity;

@Entity
@Table(name="clinica_forma_de_pagamento")
@Access(AccessType.FIELD)
public class FormaDePagamento extends GenericEntity{
	
	private String designacao;
	
	@Column(name="codigo_transacao")
	private String codigoTransacao;
	
	@Column(name="data_trancacao")
	private Date dataTrancacao;
	
	@Column(name="nome_do_banco")
	private String nomeDoBanco;
	
	@Column(name="numero_conta")
	private String numConta;
	
	@Column(name="data_inicio")
	private Date dataInicio;
	
	@Column(name ="data_fim")
	private Date dataFim;

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public String getCodigoTransacao() {
		return codigoTransacao;
	}

	public void setCodigoTransacao(String codigoTransacao) {
		this.codigoTransacao = codigoTransacao;
	}

	public Date getDataTrancacao() {
		return dataTrancacao;
	}

	public void setDataTrancacao(Date dataTrancacao) {
		this.dataTrancacao = dataTrancacao;
	}

	public String getNomeDoBanco() {
		return nomeDoBanco;
	}

	public void setNomeDoBanco(String nomeDoBanco) {
		this.nomeDoBanco = nomeDoBanco;
	}

	public String getNumConta() {
		return numConta;
	}

	public void setNumConta(String numConta) {
		this.numConta = numConta;
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
	
	

}
