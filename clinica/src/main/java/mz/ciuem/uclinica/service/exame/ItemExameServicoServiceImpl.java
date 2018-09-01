package mz.ciuem.uclinica.service.exame;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.uclinica.dao.exame.ItemExameServicoDao;
import mz.ciuem.uclinica.entity.exame.Exame;
import mz.ciuem.uclinica.entity.exame.ItemExameServico;
import mz.ciuem.uclinica.entity.parametro.Servico;
import mz.ciuem.uclinica.service.impl.GenericServiceImpl;

@Service("itemExameService")
public class ItemExameServicoServiceImpl extends GenericServiceImpl<ItemExameServico> implements ItemExameService {

	@Autowired
	private ItemExameServicoDao itemExameServicoDao;

	@Override
	public void criarItemExameServico(Exame exame, Servico servico) {

		ItemExameServico item = new ItemExameServico(exame, servico);

		if (item.getPreco() != 0) {
			itemExameServicoDao.create(item);
		}

	}

	@Override
	public void criarItemExameListaServicos(Exame exame, List<Servico> servico) {
		
		for(Servico s : servico){
			
			ItemExameServico item = new ItemExameServico(exame, s);
		    
			if(item.getPreco() != 0){
				itemExameServicoDao.create(item);
			}
		}

	}

	@Override
	public void actualizarItemExameListaServicos(Exame exame, List<Servico> servico) {
		
		List<ItemExameServico> items = itemExameServicoDao.buscarItensPorIdDaFactura(exame.getId());
		
		for(int i = 0 ; i < items.size() ; i++){
			
			itemExameServicoDao.delete(items.get(i));
		}
       criarItemExameListaServicos(exame, servico);
	}

	@Override
	public List<ItemExameServico> buscarItensPorIdDaFactura(Long id) {
		
		return itemExameServicoDao.buscarItensPorIdDaFactura(id);
	}

}
