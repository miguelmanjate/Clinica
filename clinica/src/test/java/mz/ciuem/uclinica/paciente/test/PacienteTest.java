package mz.ciuem.uclinica.paciente.test;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.paciente.service.PacienteService;
import mz.ciuem.uclinica.test.GenericTest;

public class PacienteTest extends GenericTest {

	@Autowired
	private PacienteService pacienteService;

	@Test
	public void registarPacienteTest() {

		long numeroPacientes = pacienteService.count();
		Paciente paciente = new Paciente();
		paciente.setNome("Andrade");
		paciente.setApelido("Gorbatchove");

		pacienteService.create(paciente);

		Assert.assertEquals(numeroPacientes + 1, pacienteService.count());

	}

	@Test
	public void actualizarPacienteTest() {

		Paciente paciente = new Paciente();
		paciente.setNome("Joao");
		paciente.setApelido("Matola");
		paciente.setEmail("Muchave@gmail.com");

		pacienteService.create(paciente);
		paciente.setNome("Andrade");
		pacienteService.update(paciente);
		Paciente pacienteDB = pacienteService.find(paciente.getId());
		Assert.assertEquals(pacienteDB.getNome(), "Andrade");
	}

	@Test(expected = ConstraintViolationException.class)
	public void emailInvalidoTest() {

		Paciente pacienteComEmailInvalido = new Paciente();
		pacienteComEmailInvalido.setEmail("muchave");
		pacienteComEmailInvalido.setApelido("Muchave");
		pacienteComEmailInvalido.setNome("Eliazardo");

		pacienteService.create(pacienteComEmailInvalido);
	}
	


}
