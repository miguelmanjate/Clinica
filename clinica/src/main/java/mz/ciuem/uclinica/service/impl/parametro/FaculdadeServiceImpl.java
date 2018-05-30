package mz.ciuem.uclinica.service.impl.parametro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.uclinica.dao.parametro.FaculdadeDao;
import mz.ciuem.uclinica.entity.estudante.Faculdade;
import mz.ciuem.uclinica.service.impl.GenericServiceImpl;
import mz.ciuem.uclinica.service.parametro.FaculdadeService;
@Service("faculdadeService")
public class FaculdadeServiceImpl extends GenericServiceImpl<Faculdade> implements FaculdadeService{

	@Autowired
	private FaculdadeDao faculdadeDao;
	
	@Override
	public Faculdade getFaculdadePorDescricao(String descricao) {
	
		return faculdadeDao.getFaculdadePorDescricao(descricao);
	}
	
	

}
