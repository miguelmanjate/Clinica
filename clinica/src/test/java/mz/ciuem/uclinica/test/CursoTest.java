package mz.ciuem.uclinica.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mz.ciuem.uclinica.entity.estudante.Curso;
import mz.ciuem.uclinica.service.parametro.CursoService;

public class CursoTest extends GenericTest {

	@Autowired
	private CursoService cursoService;

	@Test
	public void registarCursoTest() {
		
		Curso c = new Curso();
		c.setDescricao("Fisica");
		
		cursoService.saveOrUpdate(c);
		
	}

}
