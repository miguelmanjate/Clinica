package mz.ciuem.uclinica.entity.parametro;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

import mz.ciuem.uclinica.entity.GenericEntity;
@Entity
@Table(name = "clinica_param_especialidade")
@Access(AccessType.FIELD)
public class Especialidade extends GenericEntity{
	private String designacao;

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}
	
}
