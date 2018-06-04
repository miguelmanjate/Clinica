package mz.ciuem.uclinica.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.entity.parametro.Unidades;
import mz.ciuem.uclinica.paciente.service.PacienteService;
import mz.ciuem.uclinica.service.parametro.CursoService;
import mz.ciuem.uclinica.service.parametro.EspecialidadeService;
import mz.ciuem.uclinica.service.parametro.FaculdadeService;
import mz.ciuem.uclinica.service.parametro.FuncionarioService;
import mz.ciuem.uclinica.service.parametro.ServicoService;
import mz.ciuem.uclinica.service.parametro.UnidadesService;

public class CursoTest extends GenericTest {

	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private FaculdadeService faculdadeService;
	
	@Autowired
	private ServicoService servicoService;
	
	@Autowired
	private EspecialidadeService especialidadeService;
	
	@Autowired
	private UnidadesService unidadesService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private PacienteService pacienteService;

//	@Test
//	public void registarCursoTest() {
//		
////		Curso c = cursoService.getCursoPorDescricao("Enge_Iinformática");
////		
////		System.err.println(c.getDescricao());
//		
//		Faculdade f = faculdadeService.getFaculdadePorDescricao("Faculdade_de_Letras");
//		
//		System.err.println(f.getDescricao());
//		
//		List<CursoJson> cursos = cursoService.getTodosCursosDaFaculdade("Faculdade_de_Engenharia");
//		
//		for(CursoJson c :  cursos){
//			
//			System.err.println(c.getDescricao());
//			
//		}
//		
//	}

	@Test
	public void listarServicoPorEspecialidade(){
		
         Paciente f = pacienteService.find(12l);
         Unidades u = unidadesService.find(12l);
       //  f.setUnidade(u);
         System.err.println(f.getNome());
         //funcionarioService.saveOrUpdate(f);
	}
}
