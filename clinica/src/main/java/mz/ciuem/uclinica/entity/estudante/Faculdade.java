package mz.ciuem.uclinica.entity.estudante;

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
@Table(name="faculdade_da_uem")
@Access(AccessType.FIELD)
public class Faculdade extends GenericEntity{
	
	@NotNull(message = "descricao Campo Obrigatorio")
	@NotBlank(message = "descricao Campo Obrigatorio")
	private String descricao;
	
	@OneToMany(mappedBy = "faculdade")
	private List<Estudante> estudantes;
	
	@OneToMany(mappedBy = "faculdade", fetch = FetchType.EAGER)
	private List<Curso> cursos ;
	
	

	public List<Estudante> getEstudantes() {
		return estudantes;
	}

	public void setEstudantes(List<Estudante> estudantes) {
		this.estudantes = estudantes;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
	

}
