package mz.ciuem.uclinica.service.impl.parametro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.uclinica.dao.parametro.TaxasDao;
import mz.ciuem.uclinica.entity.consulta.Taxa;
import mz.ciuem.uclinica.service.impl.GenericServiceImpl;
import mz.ciuem.uclinica.service.parametro.TaxasServie;

@Service("taxasService")
public class TaxasServiceImpl extends GenericServiceImpl<Taxa> implements TaxasServie{

	@Autowired
	private TaxasDao taxasDao;
	
	@Override
	public List<Taxa> buscarTaxasPorIdServico(Long id) {
		
		return taxasDao.buscarTaxasPorIdServico(id);
	}

	@Override
	public Taxa getTaxaPorTipoCliente(String tipoCliente) {
		
		return taxasDao.getTaxaPorTipoCliente(tipoCliente);
	}

}
