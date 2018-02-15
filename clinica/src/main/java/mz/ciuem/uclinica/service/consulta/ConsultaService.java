package mz.ciuem.uclinica.service.consulta;

import java.util.List;

import mz.ciuem.uclinica.entity.consulta.Consulta;
import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.service.GenericService;

public interface ConsultaService extends GenericService<Consulta>{

	List<Consulta> getConsultasDoPaciente(Paciente paciente);

}
