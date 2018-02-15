package mz.ciuem.uclinica.paciente.service;

import java.util.List;

import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.recepcao.modelview.ParametrosDePesquisaPaciente;
import mz.ciuem.uclinica.service.GenericService;

public interface PacienteService extends GenericService<Paciente> {
	
	List<Paciente> procurarPacientePor(ParametrosDePesquisaPaciente parametroDePesquisa,String parametro);

}
