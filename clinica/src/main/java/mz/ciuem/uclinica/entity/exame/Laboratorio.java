package mz.ciuem.uclinica.entity.exame;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mz.ciuem.uclinica.entity.GenericEntity;
import mz.ciuem.uclinica.entity.consulta.Consulta;
import mz.ciuem.uclinica.entity.consulta.EstadoDaConsulta;
import mz.ciuem.uclinica.entity.parametro.Especialidade;

@Entity
@Table(name = "laboratorio_laboratorio")
@Access(AccessType.FIELD)
public class Laboratorio extends GenericEntity{
	
	@Column(name = "sigla")
	private String sigla;
	
	@Column(name = "descricao")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "laboratorio_especialidade_id")
	private Especialidade especialidade ;
	
	@OneToMany(mappedBy = "laboratorio", fetch = FetchType.EAGER)
	private List<Exame> exames;
	
	public List<Exame> getExames() {
		List<Exame> selecionarExames = new ArrayList<>();
		
		for(Exame e : this.exames){
			if(e.getEstado().toString().equals(EstadoDaConsulta.PAGA.toString())){
				selecionarExames.add(e);
			}
		}
		return selecionarExames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}
	

}
