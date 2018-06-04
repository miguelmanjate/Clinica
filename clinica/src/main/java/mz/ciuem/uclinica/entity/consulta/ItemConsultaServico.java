package mz.ciuem.uclinica.entity.consulta;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mz.ciuem.uclinica.entity.GenericEntity;
import mz.ciuem.uclinica.entity.parametro.Servico;

@Entity
@Table(name = "item_consulta_servico")
@Access(AccessType.FIELD)
public class ItemConsultaServico extends GenericEntity {

	@Column(name = "item_designacao_servico")
	private String descricao;

	@Column(name = "item_tipo_consulta")
	private String tipoConsulta;

	@Column(name = "item_preco")
	private double preco;
	
	@Column(name = "item_quantidade")
	private int quantidade = 1;
	
	@Column(name = "item_cliente")
	private String cliente;

	@ManyToOne()
	@JoinColumn(name = "consulta_id")
	private Consulta consulta;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "servico_id")
	private Servico servico;

	private ItemConsultaServico() {
	}

	public ItemConsultaServico(Consulta consulta, Servico servico) {

		this.consulta = consulta;
		this.servico = servico;
		this.descricao = servico.getDescricao();
		this.cliente = consulta.getPaciente().getNome()+" "+consulta.getPaciente().getApelido();
	
		this.preco =  this.servico.getTaxa(this.consulta.getTipoConsulta().toString(), this.consulta.getPaciente().getTipoDePaciente().toString());

	}
	
	public void setItemConsultaServico(Consulta consulta, Servico servico){
		
		this.consulta = consulta;
		this.servico = servico;
		this.descricao = servico.getDescricao();
		this.cliente = consulta.getPaciente().getNome()+" "+consulta.getPaciente().getApelido();

		this.preco =  this.servico.getTaxa(this.consulta.getTipoConsulta().toString(), this.consulta.getPaciente().getTipoDePaciente().toString());
		
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	

}
