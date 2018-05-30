package mz.ciuem.uclinica.service.parametro;

import java.util.List;

import mz.ciuem.uclinica.entity.estudante.Curso;
import mz.ciuem.uclinica.service.GenericService;

public interface CursoService extends GenericService<Curso>{
	
	List getTodosCursosDaFaculdade(String faculdade);
	
	Curso getCursoPorDescricao(String curso);

}
