package mz.ciuem.uclinica.entity.parametro;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import mz.ciuem.uclinica.entity.GenericEntity;

@Entity
@Table(name="clinica_param_orgao")
@Access(AccessType.FIELD)
public class Orgao extends GenericEntity{
	
	@NotNull(message = "Campo Obrigatorio ")
	@NotBlank(message = "Campo Obrigatorio ")
	private String designacao;

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}
	
	@OneToMany(mappedBy= "orgao", fetch = FetchType.EAGER)
	List<Unidades> unidades;

	public List<Unidades> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<Unidades> unidades) {
		this.unidades = unidades;
	}
	
	

}
