package mz.ciuem.uclinica.controller.parametro;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mz.ciuem.uclinica.entity.consulta.Consulta;
import mz.ciuem.uclinica.entity.consulta.EstadoDaConsulta;
import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.entity.parametro.Factura;
import mz.ciuem.uclinica.service.consulta.ConsultaService;
import mz.ciuem.uclinica.service.parametro.FacturaService;

@Controller
@RequestMapping(value = {"/factura"})
public class FacturaController {
	
	@Autowired 
	private FacturaService facturaService;
	
	@Autowired
	private ConsultaService consultaService;
	
	@GetMapping("{id}/faturar")
	public ModelAndView consultasFatura(Factura factura, @PathVariable Long id){
		
		Consulta consulta = consultaService.find(id);
		Paciente paciente = consulta.getPaciente();
	
		ModelAndView model = new ModelAndView("/parametros/fatura/consulta-fatura", "consulta", consulta);
		model.addObject("paciente", paciente);

		return model;
		
	}
	
	@GetMapping("{id}/pagar")
	public ModelAndView efectuarPagamentoConsulta(@PathVariable Long id){
		
		Consulta consulta = consultaService.find(id);
		
		Factura factura = new Factura();
		consulta.setEstado(EstadoDaConsulta.PAGA);
		Paciente paciente = consulta.getPaciente();
		
		consultaService.update(consulta);
		factura.setConsulta(consulta);
		factura.setTotal(consulta.getPreco());
		
		factura.setDataInicio(new Date());
		
		facturaService.create(factura);
		
		factura.setNumeroDaFactura(factura.getId());
		facturaService.update(factura);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		factura.setDataString(df.format(factura.getDataInicio()));
		
		ModelAndView model = new ModelAndView("/parametros/fatura/fatura-recibo", "consulta", consulta);
		model.addObject("paciente", paciente);
		model.addObject("factura", factura);
		
		return model;
	}
	
	@GetMapping("/list/recibos")
	public ModelAndView listarRecibos(){
		
		List<Factura> facturas = facturaService.getAll();

		ModelAndView model = new ModelAndView("/parametros/fatura/list-factura", "facturas", facturas );
		return model;
	}
	
	@GetMapping("{id}/detalhes/recibo")
	public ModelAndView detalhesRecibo(@PathVariable Long id){
		
		Factura factura = facturaService.find(id);
		Consulta consulta = factura.getConsulta();
		Paciente paciente = consulta.getPaciente();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		factura.setDataString(df.format(factura.getDataInicio()));
		
		ModelAndView model = new ModelAndView("/parametros/fatura/fatura-recibo", "consulta", consulta);
		model.addObject("paciente", paciente);
		model.addObject("factura", factura);
		
		return model;
	}
	

}
