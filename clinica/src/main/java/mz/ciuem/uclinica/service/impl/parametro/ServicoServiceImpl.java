package mz.ciuem.uclinica.service.impl.parametro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.uclinica.dao.parametro.ServicoDao;
import mz.ciuem.uclinica.entity.consulta.ServicoJason;
import mz.ciuem.uclinica.entity.parametro.Especialidade;
import mz.ciuem.uclinica.entity.parametro.Servico;
import mz.ciuem.uclinica.service.impl.GenericServiceImpl;
import mz.ciuem.uclinica.service.parametro.EspecialidadeService;
import mz.ciuem.uclinica.service.parametro.ServicoService;

@Service("servicoService")
public class ServicoServiceImpl extends GenericServiceImpl<Servico> implements ServicoService {

	@Autowired
	private EspecialidadeService especialidadeService;

	@Autowired
	private ServicoDao servicoDao;

	@Override
	public List<Servico> getServicosComSuasTaxas() {

		return servicoDao.getServicosComSuasTaxas();
	}

	@Override
	public List getTodosServicosDaEspecialidade(String especialidade) {

		Especialidade espec = especialidadeService.getEspecialidadePorDesignacao(especialidade);
		return servicoDao.getTodosServicosDaEspecialidade(espec);
	}

	@Override
	public List getTodosServicosDaEspecialidadeToJson(String especialidade) {
		
		List<Servico> servicos = getTodosServicosDaEspecialidade(especialidade);
		List<ServicoJason> servicosToJson  = new ArrayList<>();
		
		for (Servico servico : servicos) {
			
			
			ServicoJason servicoToJson = new ServicoJason();
			servicoToJson.setId(servico.getId());
			servicoToJson.setDescricao(servico.getDescricao());
			
			servicosToJson.add(servicoToJson);
		}
				return servicosToJson;
	}

}
