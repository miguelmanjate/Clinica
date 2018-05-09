package mz.ciuem.uclinica.entity.estudante;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import mz.ciuem.uclinica.entity.GenericEntity;

@Entity
@Table(name="cursos_da_uem")
@Access(AccessType.FIELD)
public class Curso extends GenericEntity {
	
	@NotNull(message = "Apelido Campo Obrigatorio")
	@NotBlank(message = "Apelido Campo Obrigatorio")
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
