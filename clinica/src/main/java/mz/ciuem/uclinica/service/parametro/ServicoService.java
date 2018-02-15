package mz.ciuem.uclinica.service.parametro;

import java.util.List;

import mz.ciuem.uclinica.entity.parametro.Servico;
import mz.ciuem.uclinica.service.GenericService;

public interface ServicoService extends GenericService<Servico>{

	List<Servico> getServicosComSuasTaxas();

}
