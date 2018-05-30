package mz.ciuem.uclinica.service.impl.parametro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.uclinica.dao.parametro.CursoDao;
import mz.ciuem.uclinica.entity.estudante.Curso;
import mz.ciuem.uclinica.entity.estudante.CursoJson;
import mz.ciuem.uclinica.entity.estudante.Faculdade;
import mz.ciuem.uclinica.service.impl.GenericServiceImpl;
import mz.ciuem.uclinica.service.parametro.CursoService;
import mz.ciuem.uclinica.service.parametro.FaculdadeService;

@Service("CursoService")
public class CursoServiceImpl extends GenericServiceImpl<Curso> implements CursoService {
	
	@Autowired
	private FaculdadeService faculdadeService;
	
	@Autowired
	private CursoDao cursoDao;

	@Override
	public List getTodosCursosDaFaculdade(String faculdade) {
		
	Faculdade faculdade1 = faculdadeService.getFaculdadePorDescricao(faculdade);
	
	List<Curso> cursos = faculdade1.getCursos();
	
	List<CursoJson> cursoToJson = new ArrayList<>();
	
	for (Curso curso : cursos){
		
		CursoJson cursoJson = new CursoJson();
		cursoJson.setId(curso.getId());
		cursoJson.setDescricao(curso.getDescricao());
		
		cursoToJson.add(cursoJson);
	}
		
		return cursoToJson;
	}

	@Override
	public Curso getCursoPorDescricao(String curso) {
	
		return cursoDao.getCursoPorDescricao(curso);
	}
	
	

}
