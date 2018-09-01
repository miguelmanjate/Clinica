package mz.ciuem.uclinica.service.exame;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.uclinica.dao.exame.ExameDao;
import mz.ciuem.uclinica.entity.exame.Exame;
import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.service.impl.GenericServiceImpl;

@Service("exameService")
public class ExameServiceImpl extends GenericServiceImpl<Exame> implements ExameService {

	@Autowired
	private ExameDao exameDao;
	
	@Override
	public List<Exame> getExamesDoPaciente(Paciente paciente) {
		
		return exameDao.getExamesDoPaciente(paciente);
	}

}
