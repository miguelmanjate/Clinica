package mz.ciuem.uclinica.entity.exame;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import mz.ciuem.uclinica.entity.GenericEntity;
import mz.ciuem.uclinica.entity.consulta.EstadoDaConsulta;
import mz.ciuem.uclinica.entity.consulta.ItemConsultaServico;
import mz.ciuem.uclinica.entity.consulta.TipoConsulta;
import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.entity.parametro.Especialidade;
import mz.ciuem.uclinica.entity.parametro.Factura;

@Entity
@Table(name = "exame_exame")
public class Exame extends GenericEntity{
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	
	@Column(name = "tipo_exame")
	@Enumerated(EnumType.STRING)
	private TipoConsulta tipoExame;
	
	@ManyToOne()
	@JoinColumn(name = "consulta_especialidade_id")
	private Especialidade especialidade ;
	
	@Column(name = "data_consulta")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dataDaConsulta;
	
	@Column(name = "consulta_hora")
	@Temporal(TemporalType.TIME)
	private Date horaDaConsulta;
	
	private double preco;
	
	@Column(name = "estado_consulta")
	@Enumerated(EnumType.STRING)
	private EstadoDaConsulta estado;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "laboratorio_id")
	private Laboratorio laboratorio;
	
	@OneToMany(mappedBy = "exame")
	private List<Factura> facturas;
    
	@OneToMany(mappedBy = "exame")
	private List<ItemExameServico> itemExameServicos;

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public TipoConsulta getTipoExame() {
		return tipoExame;
	}

	public void setTipoExame(TipoConsulta tipoExame) {
		this.tipoExame = tipoExame;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Date getDataDaConsulta() {
		return dataDaConsulta;
	}

	public void setDataDaConsulta(Date dataDaConsulta) {
		this.dataDaConsulta = dataDaConsulta;
	}

	public Date getHoraDaConsulta() {
		return horaDaConsulta;
	}

	public void setHoraDaConsulta(Date horaDaConsulta) {
		this.horaDaConsulta = horaDaConsulta;
	}

	public double getPreco() {
		this.preco = 0;
		for (ItemExameServico itemExameServico : itemExameServicos) {
			if (itemExameServico != null) {

				this.preco = this.preco + itemExameServico.getPreco();
			}
		}
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public EstadoDaConsulta getEstado() {
		return estado;
	}

	public void setEstado(EstadoDaConsulta estado) {
		this.estado = estado;
	}

	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public List<ItemExameServico> getItemExameServicos() {
		return itemExameServicos;
	}

	public void setItemExameServicos(List<ItemExameServico> itemExameServicos) {
		this.itemExameServicos = itemExameServicos;
	}
	
}
