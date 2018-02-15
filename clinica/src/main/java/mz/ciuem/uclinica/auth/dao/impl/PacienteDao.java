package mz.ciuem.uclinica.auth.dao.impl;

import java.util.List;

import mz.ciuem.uclinica.dao.GenericDao;
import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.recepcao.modelview.ParametrosDePesquisaPaciente;

public interface PacienteDao extends GenericDao<Paciente>{

	List<Paciente> procurarPacientePorNome(String parametro);

	List<Paciente> procurarPacientePorCodigo(String parametro);

	List<Paciente> procurarPacientePorNID(String parametro);

}
