package mz.ciuem.uclinica.entity.parametro;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import mz.ciuem.uclinica.entity.GenericEntity;
import mz.ciuem.uclinica.entity.consulta.Especialidade;
@Entity
@Table(name = "param_servico_unidade")
public class ServicoDaUnidade extends GenericEntity{
	
	@NotBlank
	@Column(name = "sevico_rubrica_codigo")
	private String codigo;
	
	@Column(name = "servico_designacao")
	@Enumerated(EnumType.STRING)
	private ServicoTipo servicoTipo;
	
	@Column(name = "servico_rubrica")
	@Enumerated(EnumType.STRING)
	private RubricaDaUnidade rubricaDaUnidade;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "servicoDaUnidade")
	private List<Servico> servicos;



	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	@Override
	public String toString() {
		return this.codigo;
	}

	public ServicoTipo getServicoTipo() {
		return servicoTipo;
	}

	public void setServicoTipo(ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}

	public RubricaDaUnidade getRubricaDaUnidade() {
		return rubricaDaUnidade;
	}

	public void setRubricaDaUnidade(RubricaDaUnidade rubricaDaUnidade) {
		this.rubricaDaUnidade = rubricaDaUnidade;
	}

	
	


	
	
	
	
	
	
	

}
