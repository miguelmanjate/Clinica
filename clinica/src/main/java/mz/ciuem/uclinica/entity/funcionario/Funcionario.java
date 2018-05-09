package mz.ciuem.uclinica.entity.funcionario;

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
import mz.ciuem.uclinica.entity.parametro.Unidades;

@Entity
@Table(name = "funcionario_uem")
@Access(AccessType.FIELD)
public class Funcionario  extends GenericEntity{
	
	@NotNull(message = "Mandatorio informar o nome")
	@NotBlank(message = "Mandatorio informar o nome")
	@Column(name = "apelido")
	private String apelido;
	
	@NotNull(message = "Mandatorio informar o nome")
	@NotBlank(message = "Mandatorio informar o nome")
	@Column(name = "nome")
	private String nome;
	
	
	@Temporal(TemporalType.DATE )
	@DateTimeFormat (pattern="dd/MM/yyyy")
	@Column(name = "data_de_nascimento")
	private Date dataDeNascimento;
	
	@Column(name = "genero")
	@Enumerated(EnumType.STRING)
	private Genero genero;
	
	@Column(name  = "nome_pai")
	private String nomeDoPai;
	
	
	@Column(name = "nome_mae")
	private String nomeDaMae;
	
	@Column(name = "nivel_academico")
	@Enumerated(EnumType.STRING)
	private NivelAcademico nivelAcademico;
	
	@Column(name = "estado_civil")
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;
	
	private String endereco;
	
	private String funcao;
		
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "unidade_funcionario")
	private Unidades unidade;
	
	private String numeroBi;
	
	private String telefone;
	
	public String getNumeroBi() {
		return numeroBi;
	}

	public void setNumeroBi(String numeroBi) {
		this.numeroBi = numeroBi;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

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

	public NivelAcademico getNivelAcademico() {
		return nivelAcademico;
	}

	public void setNivelAcademico(NivelAcademico nivelAcademico) {
		this.nivelAcademico = nivelAcademico;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}


	public Unidades getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidades unidade) {
		this.unidade = unidade;
	}
	
	

}
