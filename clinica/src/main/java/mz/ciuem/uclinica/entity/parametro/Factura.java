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
import mz.ciuem.uclinica.entity.paciente.Paciente;

@Entity
@Table(name="clinica_factura")
@Access(AccessType.FIELD)
public class Factura extends GenericEntity {
	
	@Column(name="numero_factura")
	private Long numeroDaFactura;
	
	@Column(name="data_inicio")
	private Date dataInicio;
	
	@Column(name="data_fim")
	private Date dataFim;
	
	private double total;
	
	@ManyToOne
	@JoinColumn(name ="unidades")
	private Unidades unidades;
	
	@ManyToOne
	@JoinColumn(name ="paciente")
	private Paciente paciente;
	
	@ManyToOne
	private Iva iva;

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
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Unidades getUnidade() {
		return unidades;
	}

	public void setUnidade(Unidades unidade) {
		this.unidades = unidade;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Iva getIva() {
		return iva;
	}

	public void setIva(Iva iva) {
		this.iva = iva;
	}
	

}
