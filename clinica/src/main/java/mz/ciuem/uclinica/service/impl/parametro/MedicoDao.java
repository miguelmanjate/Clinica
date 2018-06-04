package mz.ciuem.uclinica.service.impl.parametro;

import mz.ciuem.uclinica.dao.GenericDao;
import mz.ciuem.uclinica.entity.consulta.Medico;

public interface MedicoDao  extends GenericDao<Medico>{

	Medico getMedicoPorNome(String nome);
}
