package mz.ciuem.uclinica.service.consulta;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.uclinica.entity.consulta.Medico;
import mz.ciuem.uclinica.entity.consulta.MedicoJson;
import mz.ciuem.uclinica.entity.parametro.Especialidade;
import mz.ciuem.uclinica.service.impl.GenericServiceImpl;
import mz.ciuem.uclinica.service.impl.parametro.MedicoDao;
import mz.ciuem.uclinica.service.parametro.EspecialidadeService;

@Service("medicoService")
public class MedicoServiceImpl extends GenericServiceImpl<Medico> implements MedicoService {

	@Autowired
	private EspecialidadeService especialidadeService;
	
	@Autowired
	private MedicoDao medicoDao;
	
	@Override
	public List getTodosMedicosDaEspecialidade(String especialidade) {
		
		Especialidade especialidade1 = especialidadeService.getEspecialidadePorDesignacao(especialidade);
		
		List<Medico> medicos = especialidade1.getMedicos();
		List<MedicoJson> MedicoJsons = new ArrayList<>();
		
		for(Medico medico : medicos){
			
			MedicoJson medicoJson = new MedicoJson();
			medicoJson.setId(medico.getId());
			medicoJson.setNome(medico.getNome());
			medicoJson.setApelido(medico.getApelido());
			
			MedicoJsons.add(medicoJson);
		}
		
		return MedicoJsons;
	}

	@Override
	public Medico getMedicoPorNome(String medico) {
		
		return medicoDao.getMedicoPorNome(medico);
	}
	
	

}
