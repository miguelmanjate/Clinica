package mz.ciuem.uclinica.dao.parametro;

import mz.ciuem.uclinica.dao.GenericDao;
import mz.ciuem.uclinica.entity.exame.Laboratorio;

public interface LaboratorioDao extends GenericDao<Laboratorio>{
	
	Laboratorio getLaboratorioPorDescr(String descricao);

}
