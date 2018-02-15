package mz.ciuem.uclinica.entity.parametro;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

import mz.ciuem.uclinica.entity.GenericEntity;

@Entity
@Table(name = "clinica_param_tipo_risco")
@Access(AccessType.FIELD)
public class TipoRisco extends GenericEntity {
	private String designcao;

	public String getDesigncao() {
		return designcao;
	}

	public void setDesigncao(String designcao) {
		this.designcao = designcao;
	}
	

}
