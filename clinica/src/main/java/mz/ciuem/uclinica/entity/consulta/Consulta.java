package mz.ciuem.uclinica.entity.consulta;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import mz.ciuem.uclinica.entity.GenericEntity;
import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.entity.parametro.Especialidade;
import mz.ciuem.uclinica.entity.parametro.Servico;

@Entity
@Table(name = "consulta_consulta")
public class Consulta extends GenericEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	@Column(name = "tipo_consulta")
	@Enumerated(EnumType.STRING)
	private TipoConsulta tipoConsulta;
	
	@Column(name = "causa_admissao_consulta")
	@Enumerated(EnumType.STRING)
	private CausaAdmissao causaAdmissao;

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
	@JoinColumn(name = "medico_id")
	private Medico medico;
    
	@OneToMany(mappedBy = "consulta", fetch = FetchType.EAGER)
	private List<ItemConsultaServico> itemConsultaServicos;
	

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public TipoConsulta getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(TipoConsulta tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public CausaAdmissao getCausaAdmissao() {
		return causaAdmissao;
	}

	public void setCausaAdmissao(CausaAdmissao causaAdmissao) {
		this.causaAdmissao = causaAdmissao;
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
		for (ItemConsultaServico itemConsultaServico : itemConsultaServicos) {
			if (itemConsultaServico != null) {

				this.preco = this.preco + itemConsultaServico.getPreco();
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

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public List<ItemConsultaServico> getItemConsultaServicos() {
		return itemConsultaServicos;
	}

	public void setItemConsultaServicos(List<ItemConsultaServico> itemConsultaServicos) {
		this.itemConsultaServicos = itemConsultaServicos;
	}

	
}
