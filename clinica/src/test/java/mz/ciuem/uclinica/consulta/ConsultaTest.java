package mz.ciuem.uclinica.consulta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mz.ciuem.uclinica.entity.consulta.CausaAdmissao;
import mz.ciuem.uclinica.entity.consulta.Consulta;
import mz.ciuem.uclinica.entity.consulta.Especialidade;
import mz.ciuem.uclinica.entity.consulta.EstadoDaConsulta;
import mz.ciuem.uclinica.entity.consulta.TipoConsulta;
import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.entity.parametro.Servico;
import mz.ciuem.uclinica.paciente.service.PacienteService;
import mz.ciuem.uclinica.service.consulta.ConsultaService;
import mz.ciuem.uclinica.service.consulta.MedicoService;
import mz.ciuem.uclinica.service.parametro.ServicoService;
import mz.ciuem.uclinica.test.GenericTest;

public class ConsultaTest extends GenericTest{
	
	@Autowired
	private ConsultaService consultaService;
	
	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private MedicoService medicoService;
	
	@Autowired
	private ServicoService servicoService;
	
//	@Test
//	public void numeroDeConsultaDeUmPaciente(){
//		
//	  Paciente paciente =	pacienteService.find(6l);
//	  
//	  List<Consulta> consultas = new ArrayList<>();
//	  
//	  for(Consulta c : consultaService.getConsultasDoPaciente(paciente)){
//		  consultas.add(c);
//	  }
//	  System.err.println("Numero de Consultas "+consultas.size());
//	  Assert.assertEquals(4, consultas.size());
//	}
//	
//	@Test
//	public void numeroDeServicosDeUmaConsulta(){
//		
//		Paciente paciente =	pacienteService.find(3l);
//		
//		Consulta consulta = new Consulta();	
//		
//		consulta.setPaciente(paciente);
//		consulta.setTipoConsulta(TipoConsulta.NORMAL);
//		consulta.setCausaAdmissao(CausaAdmissao.ACIDENTE_DE_VIACAO);
//		
//		
//		consulta.setMedico(medicoService.find(3l));
//		consulta.setDataDaConsulta(new Date());
//		consulta.setHoraDaConsulta(new Date());
//		
//		List<Servico> servicos = new ArrayList<>();
//		/*Ids de Servicos de Tipo CONSULTA
//		 * 3, 4, 5, 11, 12, 13
//		 * */
//		
//		servicos.add(servicoService.find(3l));
//		servicos.add(servicoService.find(2l));
//		servicos.add(servicoService.find(12l));
//		
//		consulta.setServicos(servicos);
//		consulta.setEstado(EstadoDaConsulta.AGENDADA);
//		double preco = 0;
//		for(Servico s : servicos){
//			preco = preco + s.getPreco();
//		}
//		consulta.setPreco(preco);
//		consultaService.create(consulta);
//		
//		Assert.assertEquals( 3, consulta.getServicos().size());
//	}
//	
//	@Test
//	public void precoDeUmaConsulta(){
//		
//		Paciente paciente =	pacienteService.find(19l);
//		
//		Consulta consulta = new Consulta();	
//		
//		consulta.setPaciente(paciente);
//		consulta.setTipoConsulta(TipoConsulta.URGENTE);
//		consulta.setCausaAdmissao(CausaAdmissao.DOENCA);
//	
//		
//		consulta.setMedico(medicoService.find(2l));
//		consulta.setDataDaConsulta(new Date());
//		consulta.setHoraDaConsulta(new Date());
//		
//		List<Servico> servicos = new ArrayList<>();
//		/*Ids de Servicos de Tipo CONSULTA
//		 * 3, 4, 5, 11, 12, 13
//		 * */
//		
//		servicos.add(servicoService.find(4l));
//		servicos.add(servicoService.find(11l));
//		servicos.add(servicoService.find(13l));
//		
//		consulta.setServicos(servicos);
//		consulta.setEstado(EstadoDaConsulta.AGENDADA);
//		double preco = 0;
//		for(Servico s : servicos){
//			preco = preco + s.getPreco();
//		}
//		consulta.setPreco(preco);
//		consultaService.create(consulta);
//		
//		Assert.assertEquals( 2350, consulta.getPreco(),0);
//	}

}
