package mz.ciuem.uclinica.service.impl.parametro;

import org.springframework.stereotype.Service;

import mz.ciuem.uclinica.entity.parametro.Factura;
import mz.ciuem.uclinica.service.impl.GenericServiceImpl;
import mz.ciuem.uclinica.service.parametro.FacturaService;

@Service("facturaService")
public class FacturaServiceImpl extends GenericServiceImpl<Factura> implements FacturaService{

}
