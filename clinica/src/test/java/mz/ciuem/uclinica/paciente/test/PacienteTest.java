package mz.ciuem.uclinica.paciente.test;

import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mz.ciuem.uclinica.entity.estudante.Ano;
import mz.ciuem.uclinica.entity.estudante.Estudante;
import mz.ciuem.uclinica.entity.estudante.Semestre;
import mz.ciuem.uclinica.entity.funcionario.Funcionario;
import mz.ciuem.uclinica.entity.paciente.EstadoCivil;
import mz.ciuem.uclinica.entity.paciente.Genero;
import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.entity.paciente.Raca;
import mz.ciuem.uclinica.entity.paciente.TipoDePaciente;
import mz.ciuem.uclinica.paciente.service.PacienteService;
import mz.ciuem.uclinica.service.parametro.EstudanteService;
import mz.ciuem.uclinica.service.parametro.FuncionarioService;
import mz.ciuem.uclinica.service.parametro.UnidadesService;
import mz.ciuem.uclinica.test.GenericTest;

public class PacienteTest extends GenericTest {

	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private EstudanteService estudanteService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	@Autowired
	private UnidadesService unidadesService ;

	@Test
	public void registarPacienteGeRalTest() {

		Paciente paciente = new Paciente();
		paciente.setNome("Moises");
		paciente.setApelido("Arao");
		paciente.setEmail("moisesa@hotmail.com");
		paciente.setGenero(Genero.MASCULINO);
		paciente.setProfissao("Libertador");
		paciente.setEndereco("Guibita");
		paciente.setNaturalidade("Kanana");
		paciente.setRaca(Raca.NEGRA);
		paciente.setEstadoCivil(EstadoCivil.CASADO);
		
		Calendar c = Calendar.getInstance();
    	c.set(1978, Calendar.AUGUST, 27);
    	
    	paciente.setDataDeNascimento(c.getTime());
    	
    	paciente.setTelefone("+283729220");
    	paciente.setTipoDePaciente(TipoDePaciente.PACIENTE_GERAL);

		pacienteService.create(paciente);

		Assert.assertEquals(TipoDePaciente.PACIENTE_GERAL, paciente.getTipoDePaciente());

	}

//	@Test
//	public void actualizarPacienteTest() {
//
//		Paciente paciente = new Paciente();
//		paciente.setNome("Joao");
//		paciente.setApelido("Matola");
//		paciente.setEmail("Muchave@gmail.com");
//
//		pacienteService.create(paciente);
//		paciente.setNome("Andrade");
//		pacienteService.update(paciente);
//		Paciente pacienteDB = pacienteService.find(paciente.getId());
//		Assert.assertEquals(pacienteDB.getNome(), "Andrade");
//	}
    @Test
	public void tipoDePacienteEstudante() {
    	
    	Estudante estudante = new Estudante();
    	
    	estudante.setApelido("Tamires");
    	estudante.setNome("Tania");
//    	estudante.setAno(Ano.TERCEIRO_ANO);
//    	estudante.setSemestre(Semestre.SEGUNDO);
    	estudante.setEmail("taniat@outlook.pt");
    	estudante.setEndereco("Matola c");
    	estudante.setGenero(Genero.FEMININO);
    	estudante.setEstadoCivil(EstadoCivil.SOLTEIRO);
    	estudante.setTipoDePaciente(TipoDePaciente.PACIENTE_ESTUDANTE);
    	
    	Calendar c = Calendar.getInstance();
    	c.set(1993, Calendar.FEBRUARY, 16);
    	
    	estudante.setDataDeNascimento(c.getTime());
    	
    	estudanteService.create(estudante);
    	
    	Assert.assertEquals(TipoDePaciente.PACIENTE_ESTUDANTE, estudante.getTipoDePaciente());

	}
    
    @Test
    public void tipoDePacienteFuncionario(){
    	
    	Funcionario funcionario = new Funcionario();
    	
    	funcionario.setApelido("Milo");
    	funcionario.setNome("Neusa");
    	funcionario.setEmail("neusam@outlook.com");
    	funcionario.setEndereco("Baixa");
    	funcionario.setGenero(Genero.FEMININO);
    	funcionario.setEstadoCivil(EstadoCivil.CASADO);
    	funcionario.setNumeroBi("29237382K");
    	funcionario.setFuncao("Gestora troca Aki ");
    	funcionario.setUnidade(unidadesService.find(2l));
    	
    	Calendar c = Calendar.getInstance();
    	c.set(1989, Calendar.JANUARY, 02);
    	
    	funcionario.setDataDeNascimento(c.getTime());
    	funcionario.setTipoDePaciente(TipoDePaciente.PACIENTE_FUNCIONARIO);
    	
    	funcionarioService.create(funcionario);
    	
    	Assert.assertEquals(TipoDePaciente.PACIENTE_FUNCIONARIO, funcionario.getTipoDePaciente());
    	
    }

}
