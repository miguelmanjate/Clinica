package mz.ciuem.uclinica.entity.consulta;

import java.util.List;

import mz.ciuem.uclinica.entity.parametro.Servico;

public class ConsultaForm {

	private List<Servico> servicos;

	private Consulta consulta;
	
	private List<ItemConsultaServico> items ;
	
	public List<ItemConsultaServico> getItems() {
		return items;
	}

	public void setItems(List<ItemConsultaServico> items) {
		this.items = items;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

}
