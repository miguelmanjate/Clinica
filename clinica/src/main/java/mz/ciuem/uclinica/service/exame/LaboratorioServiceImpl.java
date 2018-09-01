package mz.ciuem.uclinica.service.exame;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.uclinica.dao.parametro.LaboratorioDao;
import mz.ciuem.uclinica.entity.exame.Laboratorio;
import mz.ciuem.uclinica.entity.exame.LaboratorioJson;
import mz.ciuem.uclinica.entity.parametro.Especialidade;
import mz.ciuem.uclinica.service.impl.GenericServiceImpl;
import mz.ciuem.uclinica.service.parametro.EspecialidadeService;

@Service("laboratorioService")
public class LaboratorioServiceImpl extends GenericServiceImpl<Laboratorio> implements LaboratorioService{

	@Autowired
	private EspecialidadeService especialidadeService;
	
	@Autowired
	private LaboratorioDao laboratorioDao;
	
	@Override
	public List getTodosLaboratoriosDaEspecialidade(String especialidade) {
		
		Especialidade especialidade1 = especialidadeService.getEspecialidadePorDesignacao(especialidade);
		
		List<Laboratorio> laboratorios = especialidade1.getLaboratorios();
		List<LaboratorioJson> laboratorioJsons = new ArrayList<>();
		
		for (Laboratorio laboratorio : laboratorios){
			
			LaboratorioJson laboratorioJson = new LaboratorioJson();
			laboratorioJson.setId(laboratorio.getId());
			laboratorioJson.setDescricao(laboratorio.getDescricao());
			
			laboratorioJsons.add(laboratorioJson);			
		}
		
		return laboratorioJsons;
	}

	@Override
	public Laboratorio getLaboratorioPorDescricao(String descricao) {
		
		return laboratorioDao.getLaboratorioPorDescr(descricao);
	}

}
