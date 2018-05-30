package mz.ciuem.uclinica.service.impl.parametro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.uclinica.dao.parametro.EspecialidadeDao;
import mz.ciuem.uclinica.entity.parametro.Especialidade;
import mz.ciuem.uclinica.service.impl.GenericServiceImpl;
import mz.ciuem.uclinica.service.parametro.EspecialidadeService;
@Service("especialidadeService")
public class EspecialidadeServiceImpl extends GenericServiceImpl<Especialidade> implements EspecialidadeService {

	@Autowired
	private EspecialidadeDao especialidadeDao;
	
	@Override
	public Especialidade getEspecialidadePorDesignacao(String descricao) {
		
		return especialidadeDao.getEspecialidadePorDesignacao(descricao);
		
	}

}
