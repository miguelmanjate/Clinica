package mz.ciuem.uclinica.entity.exame;

import java.util.List;

import mz.ciuem.uclinica.entity.parametro.Servico;

public class ExameForm {
	
	private List<Servico> servicos;
	
	private Exame exame;
	
	private List<ItemExameServico> items;

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public List<ItemExameServico> getItems() {
		return items;
	}

	public void setItems(List<ItemExameServico> items) {
		this.items = items;
	}
	

}
