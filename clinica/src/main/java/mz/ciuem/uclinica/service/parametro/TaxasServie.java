package mz.ciuem.uclinica.service.parametro;

import java.util.List;

import mz.ciuem.uclinica.entity.consulta.Taxa;
import mz.ciuem.uclinica.service.GenericService;

public interface TaxasServie extends GenericService<Taxa> {
	
	List<Taxa> buscarTaxasPorIdServico(Long id);
	
	Taxa getTaxaPorTipoCliente(String tipoCliente);

}
