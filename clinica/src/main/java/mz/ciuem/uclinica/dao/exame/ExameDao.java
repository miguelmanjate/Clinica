package mz.ciuem.uclinica.dao.exame;

import java.util.List;

import mz.ciuem.uclinica.dao.GenericDao;
import mz.ciuem.uclinica.entity.exame.Exame;
import mz.ciuem.uclinica.entity.paciente.Paciente;

public interface ExameDao extends GenericDao<Exame>{
	
	List<Exame> getExamesDoPaciente(Paciente paciente);

}
