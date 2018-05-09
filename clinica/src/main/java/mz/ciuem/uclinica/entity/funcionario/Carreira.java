package mz.ciuem.uclinica.entity.funcionario;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import mz.ciuem.uclinica.entity.GenericEntity;

@Entity
@Table(name = "carreira")
@Access(AccessType.FIELD)
public class Carreira  extends GenericEntity{
	
	@NotNull( message = "Campo Obrigatorio")
	@NotBlank( message = "Campo Obrigatorio")
	@Column(name = "designacao")
	private String designacao;
	
//	@OneToMany( mappedBy = "carreira", fetch = FetchType.EAGER)
//	private List<Funcionario> funcionarios;

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

//	public List<Funcionario> getFuncionarios() {
//		return funcionarios;
//	}
//
//	public void setFuncionarios(List<Funcionario> funcionarios) {
//		this.funcionarios = funcionarios;
//	}
	
	
}
