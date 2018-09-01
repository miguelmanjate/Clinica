package mz.ciuem.uclinica.controller.parametro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mz.ciuem.uclinica.entity.consulta.Consulta;
import mz.ciuem.uclinica.entity.consulta.EstadoDaConsulta;
import mz.ciuem.uclinica.entity.exame.Exame;
import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.entity.parametro.Factura;
import mz.ciuem.uclinica.entity.parametro.FormaPagamento;
import mz.ciuem.uclinica.service.consulta.ConsultaService;
import mz.ciuem.uclinica.service.exame.ExameService;
import mz.ciuem.uclinica.service.parametro.FacturaService;

@Controller
@RequestMapping(value = {"/factura"})
public class FacturaController {
	
	@Autowired 
	private FacturaService facturaService;
	
	@Autowired
	private ConsultaService consultaService;
	
	@Autowired
	private ExameService exameService;
	
	@GetMapping("{id}/faturar")
	public ModelAndView consultasFatura(@ModelAttribute Factura factura, @PathVariable Long id ){
		
		Consulta consulta = consultaService.find(id);
		factura.setConsulta(consulta);
		Paciente paciente = consulta.getPaciente();
	
		ModelAndView model = new ModelAndView("/parametros/fatura/consulta-fatura", "consulta", consulta);
		model.addObject("paciente", paciente);
		model.addObject("formaPagamento", FormaPagamento.values());

		return model;
		
	}
	
	@GetMapping("{id}/pagar")
	public ModelAndView efectuarPagamentoConsulta(@PathVariable Long id, @Valid Factura factura){
		
		Consulta consulta = consultaService.find(id);
				
		consulta.setEstado(EstadoDaConsulta.PAGA);
		Paciente paciente = consulta.getPaciente();
		Factura f = new Factura();
		
		f.setFormaPagamento(factura.getFormaPagamento());
		
		consultaService.update(consulta);
		f.setConsulta(consulta);
		f.setTotal(consulta.getPreco());
		
		f.setDataInicio(new Date());
		
		facturaService.create(f);
		
		f.setNumeroDaFactura(f.getId());
		facturaService.update(f);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		f.setDataString(df.format(f.getDataInicio()));
		
		ModelAndView model = new ModelAndView("/parametros/fatura/fatura-recibo", "consulta", consulta);
		model.addObject("paciente", paciente);
		model.addObject("factura", f);
		
		return model;
	}
	
	@GetMapping("/list/recibos")
	public ModelAndView listarRecibos(){
		
		List<Factura> facturas = facturaService.getAll();
		List<Factura> facturasSeleceonadas = new ArrayList<>();
		
		for(Factura factura : facturas){
			if(factura.getConsulta() != null){
				facturasSeleceonadas.add(factura);
			}
		}

		ModelAndView model = new ModelAndView("/parametros/fatura/list-factura", "facturas", facturasSeleceonadas );
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
	
	@GetMapping("/exame/{id}/faturar")
	public ModelAndView examesFactura(@ModelAttribute Factura factura, @PathVariable Long id){
		
		Exame exame = exameService.find(id);
		
		factura.setExame(exame);
		
		Paciente paciente = exame.getPaciente();
		ModelAndView model = new ModelAndView("/parametros/fatura/exame-fatura", "exame", exame);
		model.addObject("paciente", paciente);
		model.addObject("formaPagamento", FormaPagamento.values());

		return model;
	}
	
	@GetMapping("/exame/{id}/pagar")
	public ModelAndView efectuarPagamentoExame(@PathVariable Long id, @Valid Factura factura){
		
		Exame exame = exameService.find(id);
		
		exame.setEstado(EstadoDaConsulta.PAGA);
		Paciente paciente = exame.getPaciente();
		
		Factura f = new Factura();
		
		f.setFormaPagamento(factura.getFormaPagamento());
		
		exameService.update(exame);
		f.setExame(exame);
		
		f.setTotalExame(exame.getPreco());
		f.setDataInicio(new Date());
		
		facturaService.create(f);
		
		f.setNumeroDaFactura(f.getId());
		facturaService.update(f);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		f.setDataString(df.format(f.getDataInicio()));
		
		ModelAndView model = new ModelAndView("/parametros/fatura/faturaExame-recibo", "exame", exame);
		model.addObject("paciente", paciente);
		model.addObject("factura", f);
		
		return model;
		
	}
	
	@GetMapping("/list/recibos/exame")
	public ModelAndView listarRecibosExame(){
		
		List<Factura> facturas = facturaService.getAll();
		List<Factura> facturasSeleceonadas = new ArrayList<>();
		
		for(Factura factura : facturas){
			if(factura.getExame() != null){
				facturasSeleceonadas.add(factura);
			}
		}

		ModelAndView model = new ModelAndView("/parametros/fatura/list-factura-exame", "facturas", facturasSeleceonadas );
		return model;
	}
	
	@GetMapping("{id}/detalhes/recibo/exame")
	public ModelAndView detalhesReciboExame(@PathVariable Long id){
		
		Factura factura = facturaService.find(id);
		Exame exame = factura.getExame();
		Paciente paciente = exame.getPaciente();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		factura.setDataString(df.format(factura.getDataInicio()));
		
		ModelAndView model = new ModelAndView("/parametros/fatura/faturaExame-recibo", "exame", exame);
		model.addObject("paciente", paciente);
		model.addObject("factura", factura);
		
		return model;
	}
	
}
