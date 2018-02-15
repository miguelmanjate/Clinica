package mz.ciuem.uclinica.service.impl.parametro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.uclinica.dao.parametro.ServicoDao;
import mz.ciuem.uclinica.entity.parametro.Servico;
import mz.ciuem.uclinica.service.impl.GenericServiceImpl;
import mz.ciuem.uclinica.service.parametro.ServicoService;

@Service("servicoService")
public class ServicoServiceImpl extends GenericServiceImpl<Servico> implements ServicoService{

	
	@Autowired
	private ServicoDao servicoDao;
	
	@Override
	public List<Servico> getServicosComSuasTaxas() {
		
		return servicoDao. getServicosComSuasTaxas() ;
	}

	
	
}
