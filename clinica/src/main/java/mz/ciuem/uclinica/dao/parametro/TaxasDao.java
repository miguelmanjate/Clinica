package mz.ciuem.uclinica.dao.parametro;

import java.util.List;

import mz.ciuem.uclinica.dao.GenericDao;
import mz.ciuem.uclinica.entity.consulta.Taxa;

public interface TaxasDao extends GenericDao<Taxa>{
	
	List<Taxa> buscarTaxasPorIdServico(Long id);
	
	Taxa getTaxaPorTipoCliente(String tipoDeClinte);

}
