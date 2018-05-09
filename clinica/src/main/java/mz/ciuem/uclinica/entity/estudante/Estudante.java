package mz.ciuem.uclinica.entity.estudante;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import mz.ciuem.uclinica.entity.GenericEntity;
import mz.ciuem.uclinica.entity.paciente.EstadoCivil;
import mz.ciuem.uclinica.entity.paciente.Genero;

@Entity
@Table(name="estudante_uem")
@Access(AccessType.FIELD)
public class Estudante extends GenericEntity {
	
	@NotNull(message = "Apelido Campo Obrigatorio")
	@NotBlank(message = "Apelido Campo Obrigatorio")
	private String apelido;
	
	@NotNull(message = "Apelido Campo Obrigatorio")
	@NotBlank(message = "Apelido Campo Obrigatorio")
	private String nome;
	
	@Temporal(TemporalType.DATE )
	@DateTimeFormat (pattern="dd/MM/yyyy")
	@Column(name = "data_de_nascimento")
	private Date dataDeNascimento;
    
	@Enumerated(EnumType.STRING)
	private Genero genero;
	
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;
	
	private String nomeDoPai;
	private String nomeDaMae;
	private String telefone;
	private String endereco;
	private String numeroBi;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "curso_estudante")
	private Curso curso;
	
	@Enumerated(EnumType.STRING)
	private Ano ano;
	
	@Enumerated(EnumType.STRING)
	private Semestre semestre;
	

	public String getNomeDoPai() {
		return nomeDoPai;
	}

	public void setNomeDoPai(String nomeDoPai) {
		this.nomeDoPai = nomeDoPai;
	}

	public String getNomeDaMae() {
		return nomeDaMae;
	}

	public void setNomeDaMae(String nomeDaMae) {
		this.nomeDaMae = nomeDaMae;
	}

	public String getNumeroBi() {
		return numeroBi;
	}

	public void setNumeroBi(String numeroBi) {
		this.numeroBi = numeroBi;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Ano getAno() {
		return ano;
	}

	public void setAno(Ano ano) {
		this.ano = ano;
	}

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
	

}
