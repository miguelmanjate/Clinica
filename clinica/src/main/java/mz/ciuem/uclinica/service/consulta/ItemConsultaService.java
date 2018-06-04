package mz.ciuem.uclinica.service.consulta;

import java.util.List;

import mz.ciuem.uclinica.entity.consulta.Consulta;
import mz.ciuem.uclinica.entity.consulta.ItemConsultaServico;
import mz.ciuem.uclinica.entity.parametro.Servico;
import mz.ciuem.uclinica.service.GenericService;

public interface ItemConsultaService extends GenericService<ItemConsultaServico>{
	

	void criarItemConsultaServico(Consulta consulta, Servico servico);
	
	void criarItemConsultaListaServicos(Consulta consulta,List<Servico> servico);
	
	void actualizarItemConsultaListaServicos(Consulta consulta,List<Servico> servico);

	List<ItemConsultaServico> buscarItensPorIdDaFactura(Long id);

}
