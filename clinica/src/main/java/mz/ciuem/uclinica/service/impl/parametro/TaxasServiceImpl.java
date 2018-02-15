package mz.ciuem.uclinica.service.impl.parametro;

import org.springframework.stereotype.Service;

import mz.ciuem.uclinica.entity.consulta.Taxa;
import mz.ciuem.uclinica.service.impl.GenericServiceImpl;
import mz.ciuem.uclinica.service.parametro.TaxasServie;

@Service("taxasService")
public class TaxasServiceImpl extends GenericServiceImpl<Taxa> implements TaxasServie{

}
