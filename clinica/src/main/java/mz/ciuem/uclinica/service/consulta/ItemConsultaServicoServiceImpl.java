package mz.ciuem.uclinica.service.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.uclinica.dao.consulta.ItemConsultaServicoDao;
import mz.ciuem.uclinica.entity.consulta.Consulta;
import mz.ciuem.uclinica.entity.consulta.ItemConsultaServico;
import mz.ciuem.uclinica.entity.parametro.Servico;
import mz.ciuem.uclinica.service.impl.GenericServiceImpl;

@Service("itemConsultaService")
public class ItemConsultaServicoServiceImpl extends GenericServiceImpl<ItemConsultaServico>
		implements ItemConsultaService {

	@Autowired
	private ItemConsultaServicoDao itemConsultaServicoDao;

	@Override
	public void criarItemConsultaServico(Consulta consulta, Servico servico) {

		ItemConsultaServico item = new ItemConsultaServico(consulta, servico);
		if(item.getPreco() != 0){
			itemConsultaServicoDao.create(item);
		}
	}

	@Override
	public void criarItemConsultaListaServicos(Consulta consulta, List<Servico> servicos) {
    
		for (Servico servico : servicos) {

			ItemConsultaServico item = new ItemConsultaServico(consulta, servico);
			
			
			if(item.getPreco() != 0){
				itemConsultaServicoDao.create(item);
				
			}
			

		}

	}

	@Override
	public void actualizarItemConsultaListaServicos(Consulta consulta, List<Servico> servicos) {

		List<ItemConsultaServico> items = buscarItensPorIdDaFactura(consulta.getId());    
	           
		for(int i = 0 ; i < items.size(); i ++){
			
				itemConsultaServicoDao.delete(items.get(i));
		}
		criarItemConsultaListaServicos(consulta, servicos);
	}

	@Override
	public List<ItemConsultaServico> buscarItensPorIdDaFactura(Long id) {
		return itemConsultaServicoDao.buscarItensPorIdDaFactura(id);
	}

}
