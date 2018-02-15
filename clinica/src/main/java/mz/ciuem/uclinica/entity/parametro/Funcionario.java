package mz.ciuem.uclinica.entity.parametro;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import mz.ciuem.uclinica.entity.GenericEntity;
import mz.ciuem.uclinica.entity.paciente.EstadoCivil;
import mz.ciuem.uclinica.entity.paciente.Genero;

@Entity
@Table(name="funcionario_funcionario")
@Access(AccessType.FIELD)
public class Funcionario extends GenericEntity{
	private String nid;
	
	@NotNull(message = "Apelido Campo Obrigatorio")
	@NotBlank(message = "Apelido Campo Obrigatorio")
	private String apelido;
	
	@NotNull(message = "Nome Campo Obrigatorio")
	@NotBlank(message = "Nome Campo Obrigatorio")
	private String nome;
	
	@NotNull(message = "Campo Obrigatorio")
	@NotBlank(message = "Campo Obrigatorio")
	private String numero_bi;
	
	@NotNull(message = "Campo Obrigatorio")
	@NotBlank(message = "Campo Obrigatorio")
	private String naturalidade;
	
	private String email;
	private String nome_pai;
	private String nome_mae;
	
	@Enumerated(EnumType.STRING)
	private Genero genero;
	
	@Enumerated(EnumType.STRING)
	private EstadoCivil estado_civil;
	private String telefone1;
	private String telefone2;
	
	@ManyToOne
	private Sector sector;
	private String profissao;
	
	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
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
	public String getNumero_bi() {
		return numero_bi;
	}
	public void setNumero_bi(String numero_bi) {
		this.numero_bi = numero_bi;
	}
	public String getNaturalidade() {
		return naturalidade;
	}
	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
    	
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public EstadoCivil getEstado_civil() {
		return estado_civil;
	}
	public void setEstado_civil(EstadoCivil estado_civil) {
		this.estado_civil = estado_civil;
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
	
}
