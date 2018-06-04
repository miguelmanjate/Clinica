package mz.ciuem.uclinica.service.consulta;

import java.util.List;

import mz.ciuem.uclinica.entity.consulta.Medico;
import mz.ciuem.uclinica.service.GenericService;

public interface MedicoService extends GenericService<Medico>{

	List getTodosMedicosDaEspecialidade(String especialidade);

	Medico getMedicoPorNome(String medico);
	
}
