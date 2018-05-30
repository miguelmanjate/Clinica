package mz.ciuem.uclinica.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mz.ciuem.uclinica.entity.estudante.CursoJson;
import mz.ciuem.uclinica.entity.estudante.Faculdade;
import mz.ciuem.uclinica.service.parametro.CursoService;
import mz.ciuem.uclinica.service.parametro.FaculdadeService;

public class CursoTest extends GenericTest {

	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private FaculdadeService faculdadeService;

	@Test
	public void registarCursoTest() {
		
//		Curso c = cursoService.getCursoPorDescricao("Enge_Iinformática");
//		
//		System.err.println(c.getDescricao());
		
		Faculdade f = faculdadeService.getFaculdadePorDescricao("Faculdade_de_Letras");
		
		System.err.println(f.getDescricao());
		
		List<CursoJson> cursos = cursoService.getTodosCursosDaFaculdade("Faculdade_de_Engenharia");
		
		for(CursoJson c :  cursos){
			
			System.err.println(c.getDescricao());
			
		}
		
	}

}
