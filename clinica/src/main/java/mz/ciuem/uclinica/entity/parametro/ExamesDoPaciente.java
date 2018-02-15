package mz.ciuem.uclinica.entity.parametro;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import mz.ciuem.uclinica.entity.GenericEntity;
@Entity
@Table(name = "clinica_param_exame_do_paciente")
public class ExamesDoPaciente extends GenericEntity{
	private String ordem;
	private Date data_realizacao;
	private Date data_marcacao;
	private boolean realizado;
	public String getOrdem() {
		return ordem;
	}
	public void setOrdem(String ordem) {
		this.ordem = ordem;
	}
	public Date getData_realizacao() {
		return data_realizacao;
	}
	public void setData_realizacao(Date data_realizacao) {
		this.data_realizacao = data_realizacao;
	}
	public Date getData_marcacao() {
		return data_marcacao;
	}
	public void setData_marcacao(Date data_marcacao) {
		this.data_marcacao = data_marcacao;
	}
	public boolean isRealizado() {
		return realizado;
	}
	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}
}
