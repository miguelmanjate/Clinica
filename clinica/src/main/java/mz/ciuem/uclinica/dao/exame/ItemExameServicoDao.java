package mz.ciuem.uclinica.dao.exame;

import java.util.List;

import mz.ciuem.uclinica.entity.exame.ItemExameServico;
import mz.ciuem.uclinica.service.GenericService;

public interface ItemExameServicoDao extends GenericService<ItemExameServico>{
	
	List<ItemExameServico> buscarItensPorIdDaFactura(Long id);

}
