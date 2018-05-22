package mz.ciuem.uclinica.entity.parametro;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mz.ciuem.uclinica.entity.GenericEntity;

@Entity
@Table(name ="clinica_param_sectors")
@Access(AccessType.FIELD)
public class Sector extends GenericEntity{
	
	private String designacao;
	
	@ManyToOne
	private Unidades Unidades;
	
//	@OneToMany(mappedBy = "sector")
//	private List<Servico> servicos;

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public Unidades getUnidades() {
		return Unidades;
	}

	public void setUnidades(Unidades unidades) {
		Unidades = unidades;
	}
	
	

}
