package mz.ciuem.uclinica.paciente.service;

import java.util.List;

import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.recepcao.modelview.ParametrosDePesquisaPaciente;

public interface ProcurarPaciente {
	
	List<Paciente> procurar(ParametrosDePesquisaPaciente parametroDePesquisa);

}
