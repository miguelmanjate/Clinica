package mz.ciuem.uclinica.service.exame;

import java.util.List;

import mz.ciuem.uclinica.entity.exame.Exame;
import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.service.GenericService;

public interface ExameService extends GenericService<Exame> {
	
	List<Exame> getExamesDoPaciente(Paciente paciente);

}
