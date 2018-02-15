package mz.ciuem.uclinica.entity.parametro;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import mz.ciuem.uclinica.entity.GenericEntity;

@Entity
@Table(name = "clinica_iva")
@Access(AccessType.FIELD)
public class Iva extends GenericEntity {
	
	@Column( name ="tipo_iva")
	private String tipoDeIva;
	
	@Column( name ="valor_iva")
	private double valorDeIva;

	public String getTipoDeIva() {
		return tipoDeIva;
	}

	public void setTipoDeIva(String tipoDeIva) {
		this.tipoDeIva = tipoDeIva;
	}

	public double getValorDeIva() {
		return valorDeIva;
	}

	public void setValorDeIva(double valorDeIva) {
		this.valorDeIva = valorDeIva;
	}
	
	

}
