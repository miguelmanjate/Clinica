package mz.ciuem.uclinica.service.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.uclinica.dao.consulta.ConsultaDao;
import mz.ciuem.uclinica.entity.consulta.Consulta;
import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.service.impl.GenericServiceImpl;

@Service("consultaService")
public class ConsultaServiceImpl extends GenericServiceImpl<Consulta> implements ConsultaService {
	
	@Autowired
	private ConsultaDao consultaDao;

	@Override
	public List<Consulta> getConsultasDoPaciente(Paciente paciente) {
		return consultaDao.getConsultasDoPaciente(paciente);
	}

}
