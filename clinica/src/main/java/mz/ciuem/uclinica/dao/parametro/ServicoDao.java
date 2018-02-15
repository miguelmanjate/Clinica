package mz.ciuem.uclinica.dao.parametro;

import java.util.List;

import mz.ciuem.uclinica.dao.GenericDao;
import mz.ciuem.uclinica.entity.parametro.Servico;

public interface ServicoDao extends GenericDao<Servico>{

	List<Servico> getServicosComSuasTaxas();

}
