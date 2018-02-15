package mz.ciuem.uclinica.service.consulta;

import org.springframework.stereotype.Service;

import mz.ciuem.uclinica.entity.consulta.Medico;
import mz.ciuem.uclinica.service.impl.GenericServiceImpl;

@Service("medicoService")
public class MedicoServiceImpl extends GenericServiceImpl<Medico> implements MedicoService {

}
