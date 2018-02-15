package mz.ciuem.uclinica.entity.parametro;

import java.sql.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

import mz.ciuem.uclinica.entity.GenericEntity;
@Entity
@Table(name = "clinica_param_servico_do_sector")
@Access(AccessType.FIELD)
public class ServicoDoSector extends GenericEntity{
	private String preco;
	private boolean estado_activo;
	private Date data;
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public boolean isEstado_activo() {
		return estado_activo;
	}
	public void setEstado_activo(boolean estado_activo) {
		this.estado_activo = estado_activo;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
}
