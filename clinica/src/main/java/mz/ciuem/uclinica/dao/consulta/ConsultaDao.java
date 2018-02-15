package mz.ciuem.uclinica.dao.consulta;

import java.util.List;

import mz.ciuem.uclinica.dao.GenericDao;
import mz.ciuem.uclinica.entity.consulta.Consulta;
import mz.ciuem.uclinica.entity.paciente.Paciente;

public interface ConsultaDao extends GenericDao<Consulta>{

	List<Consulta> getConsultasDoPaciente(Paciente paciente);

}
