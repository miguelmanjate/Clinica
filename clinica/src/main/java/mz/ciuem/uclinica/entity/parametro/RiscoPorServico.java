package mz.ciuem.uclinica.entity.parametro;

import mz.ciuem.uclinica.entity.GenericEntity;

public class RiscoPorServico extends GenericEntity{
	private String periodicidade;

	public String getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(String periodicidade) {
		this.periodicidade = periodicidade;
	}
	
}
