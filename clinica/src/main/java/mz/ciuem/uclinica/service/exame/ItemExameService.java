package mz.ciuem.uclinica.service.exame;

import java.util.List;

import mz.ciuem.uclinica.entity.exame.Exame;
import mz.ciuem.uclinica.entity.exame.ItemExameServico;
import mz.ciuem.uclinica.entity.parametro.Servico;
import mz.ciuem.uclinica.service.GenericService;

public interface ItemExameService extends GenericService<ItemExameServico>{
	
	void criarItemExameServico(Exame exame, Servico servico);
	
	void criarItemExameListaServicos(Exame exame, List<Servico> servico);
	
	void actualizarItemExameListaServicos(Exame exame, List<Servico> servico);
	
	List<ItemExameServico> buscarItensPorIdDaFactura(Long id);

}
