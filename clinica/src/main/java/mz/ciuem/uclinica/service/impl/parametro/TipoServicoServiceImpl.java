package mz.ciuem.uclinica.service.impl.parametro;

import org.springframework.stereotype.Service;

import mz.ciuem.uclinica.entity.parametro.ServicoDaUnidade;
import mz.ciuem.uclinica.service.impl.GenericServiceImpl;
import mz.ciuem.uclinica.service.parametro.TipoServicoService;

@Service("tiposervicoService")
public class TipoServicoServiceImpl extends GenericServiceImpl<ServicoDaUnidade> implements TipoServicoService{

}
