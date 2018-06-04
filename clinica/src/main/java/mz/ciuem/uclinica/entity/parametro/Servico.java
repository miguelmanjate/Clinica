package mz.ciuem.uclinica.entity.parametro;

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
import javax.persistence.Transient;

import mz.ciuem.uclinica.entity.GenericEntity;
import mz.ciuem.uclinica.entity.consulta.ItemConsultaServico;
import mz.ciuem.uclinica.entity.consulta.Taxa;

@Entity
@Table(name = "param_servico")
@Access(AccessType.FIELD)
public class Servico extends GenericEntity {

	// @NotNull(message = "Mandatorio informar a Designacao")
	// @NotBlank(message = "Mandatorio informar a Designacao")
	@Column(name = "servico_descricao")
	private String descricao;

	// @NotNull(message = "Mandatorio informar o codigo do servico")
	// @NotBlank(message = "Mandatorio informar o codigo do servico")
	private String codigo;

	// @ManyToOne
	// @JoinColumn(name = "tiposervico")
	// private ServicoDaUnidade servicoDaUnidade;

	@OneToMany(mappedBy = "servico")
	private List<Taxa> taxas;

	@ManyToOne
	@JoinColumn(name = "servico_especialidade_id")
	private Especialidade especialidade;

	@OneToMany(mappedBy = "servico", fetch = FetchType.EAGER)
	private List<ItemConsultaServico> itemConsultaServicos;

	public List<ItemConsultaServico> getItemConsultaServicos() {
		return itemConsultaServicos;
	}

	public void setItemConsultaServicos(List<ItemConsultaServico> itemConsultaServicos) {
		this.itemConsultaServicos = itemConsultaServicos;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<Taxa> getTaxas() {
		return taxas;
	}

	public void setTaxas(List<Taxa> taxas) {
		this.taxas = taxas;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	@Transient
	public Double getTaxa(String tipoServico, String tipoCliente) {
		Taxa t = null;
		for (Taxa taxa : this.taxas) {

			if (taxa.getTipoCliente().toString().equals(tipoCliente)) {
				t = taxa;
			}
		}
		if(t != null){
			return t.getTaxaPorTipoConsulta(tipoServico);
		}else {
			return 0D;
		}

	}

}
