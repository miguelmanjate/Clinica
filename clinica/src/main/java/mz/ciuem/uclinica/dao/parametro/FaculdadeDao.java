package mz.ciuem.uclinica.dao.parametro;

import mz.ciuem.uclinica.dao.GenericDao;
import mz.ciuem.uclinica.entity.estudante.Faculdade;

public interface FaculdadeDao extends GenericDao<Faculdade>{
	
	Faculdade getFaculdadePorDescricao(String descricao);

}
