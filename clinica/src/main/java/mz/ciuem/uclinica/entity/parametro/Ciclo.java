package mz.ciuem.uclinica.entity.parametro;

import java.sql.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import mz.ciuem.uclinica.entity.GenericEntity;
@Entity
@Table(name = "clinica_param_ciclo")
@Access(AccessType.FIELD)
public class Ciclo extends GenericEntity{
	@Column(name="param_ciclo_data_inicio")
	private Date data_inicio;
	private Date data_fim;
	private String designacao;
	private boolean activo;
	
	public Date getData_inicio() {
		return data_inicio;
	}
	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}
	public Date getData_fim() {
		return data_fim;
	}
	public void setData_fim(Date data_fim) {
		this.data_fim = data_fim;
	}
	public String getDesignacao() {
		return designacao;
	}
	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}
