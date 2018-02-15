package mz.ciuem.uclinica.entity.parametro;

import java.sql.Date;

import mz.ciuem.uclinica.entity.GenericEntity;

public class ServicoFuncionario extends GenericEntity{
	private Date data;
	private boolean estado_activo;
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public boolean isEstado_activo() {
		return estado_activo;
	}
	public void setEstado_activo(boolean estado_activo) {
		this.estado_activo = estado_activo;
	}
	
}
