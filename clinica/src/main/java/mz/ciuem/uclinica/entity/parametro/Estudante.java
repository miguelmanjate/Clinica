package mz.ciuem.uclinica.entity.parametro;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

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
    
	@Enumerated(EnumType.STRING)
	private Genero genero;
	
	@Enumerated(EnumType.STRING)
	private EstadoCivil estado_civil;
	
	private String nome_pai;
	private String nome_mae;
	private String telefone1;
	private String telefone2;
	private String naturalidade;
	private String endereco;
	private String conta_bancaria;
	private String numero_bi;
	private String nivel_academico;
	
	
	
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
	public void setEstado_civil(EstadoCivil estado_civil) {
		this.estado_civil = estado_civil;
	}
	public String getNome_pai() {
		return nome_pai;
	}
	public void setNome_pai(String nome_pai) {
		this.nome_pai = nome_pai;
	}
	public String getNome_mae() {
		return nome_mae;
	}
	public void setNome_mae(String nome_mae) {
		this.nome_mae = nome_mae;
	}
	public String getTelefone1() {
		return telefone1;
	}
	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}
	public String getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	public String getNaturalidade() {
		return naturalidade;
	}
	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getConta_bancaria() {
		return conta_bancaria;
	}
	public void setConta_bancaria(String conta_bancaria) {
		this.conta_bancaria = conta_bancaria;
	}
	public String getNumero_bi() {
		return numero_bi;
	}
	public EstadoCivil getEstado_civil() {
		return estado_civil;
	}
	public void setNumero_bi(String numero_bi) {
		this.numero_bi = numero_bi;
	}
	public String getNivel_academico() {
		return nivel_academico;
	}
	public void setNivel_academico(String nivel_academico) {
		this.nivel_academico = nivel_academico;
	}
	

}
