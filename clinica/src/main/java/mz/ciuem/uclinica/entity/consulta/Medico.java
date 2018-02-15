package mz.ciuem.uclinica.entity.consulta;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mz.ciuem.uclinica.entity.GenericEntity;


@Entity
@Table(name = "medico")
@Access(AccessType.FIELD)
public class Medico extends GenericEntity {

	@Column(name = "medico_nome")
	private String nome;
	@Column(name = "medico_apelido")
	private String apelido;

	@Column(name = "medico_especialidade")
	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;
	
	@OneToMany(mappedBy = "medico")
	private List<Consulta> consultas; 

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
	
	

}
