package mz.ciuem.uclinica.dao.consulta;

import java.util.List;

import mz.ciuem.uclinica.entity.consulta.ItemConsultaServico;
import mz.ciuem.uclinica.service.GenericService;

public interface ItemConsultaServicoDao extends GenericService<ItemConsultaServico>{

	List<ItemConsultaServico> buscarItensPorIdDaFactura(Long id);
	
	
	

}
