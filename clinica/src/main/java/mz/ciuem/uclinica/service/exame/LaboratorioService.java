package mz.ciuem.uclinica.service.exame;

import java.util.List;

import mz.ciuem.uclinica.entity.exame.Laboratorio;
import mz.ciuem.uclinica.service.GenericService;

public interface LaboratorioService extends GenericService<Laboratorio>{
	
	List getTodosLaboratoriosDaEspecialidade(String especialidade);
	
	Laboratorio getLaboratorioPorDescricao(String descricao);

}
