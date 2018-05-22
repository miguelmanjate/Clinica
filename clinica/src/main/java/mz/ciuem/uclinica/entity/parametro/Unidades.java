package mz.ciuem.uclinica.entity.parametro;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import mz.ciuem.uclinica.entity.GenericEntity;

@Entity
@Table(name ="clinica_param_unidades")
@Access(AccessType.FIELD)
public class Unidades extends GenericEntity{
	
	@NotNull(message = "Campo Obrigatorio ")
	@NotBlank(message = "Campo Obrigatorio ")
    @Column(name="designacao")
	private String designacao;
	
	@ManyToOne(cascade = CascadeType.REFRESH )
	@JoinColumn(name  = "orgao_unidade")
	private Orgao orgao;
	
//	@OneToMany(mappedBy= "unidade", fetch = FetchType.EAGER)
//	private List<Funcionario> funcionarios;
//	
//	
//	public List<Funcionario> getFuncionarios() {
//		return funcionarios;
//	}
//
//	public void setFuncionarios(List<Funcionario> funcionarios) {
//		this.funcionarios = funcionarios;
//	}

	@Column(name="nacionalidade")
	private String nacionalidade;
	
	@Column(name="endereco")
	private String endereco;

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
}
