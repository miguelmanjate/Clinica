package mz.ciuem.uclinica.paciente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.uclinica.auth.dao.impl.PacienteDao;
import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.recepcao.modelview.ParametrosDePesquisaPaciente;
import mz.ciuem.uclinica.service.impl.GenericServiceImpl;


@Service("pacienteService")
public class PacienteServiceImpl extends GenericServiceImpl<Paciente> implements PacienteService {

	@Autowired
	private PacienteDao pacienteDao;

	@Override
	public List<Paciente> procurarPacientePor(ParametrosDePesquisaPaciente parametroDePesquisa,String parametro) {

		switch (parametroDePesquisa) {
		case NOME:
			return pacienteDao.procurarPacientePorNome(parametro);

		case CODIGO:
			return pacienteDao.procurarPacientePorCodigo(parametro);

		case NID:
			return pacienteDao.procurarPacientePorNID(parametro);

		}

		return null;
	}

}
