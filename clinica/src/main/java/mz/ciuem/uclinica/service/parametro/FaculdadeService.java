package mz.ciuem.uclinica.service.parametro;

import mz.ciuem.uclinica.entity.estudante.Faculdade;
import mz.ciuem.uclinica.service.GenericService;

public interface FaculdadeService extends GenericService<Faculdade> {
	
	Faculdade getFaculdadePorDescricao(String descricao);

}
