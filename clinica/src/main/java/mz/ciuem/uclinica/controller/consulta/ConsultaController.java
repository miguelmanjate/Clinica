package mz.ciuem.uclinica.controller.consulta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ciuem.uclinica.entity.consulta.CausaAdmissao;
import mz.ciuem.uclinica.entity.consulta.Consulta;
import mz.ciuem.uclinica.entity.consulta.ConsultaForm;
import mz.ciuem.uclinica.entity.consulta.Especialidade;
import mz.ciuem.uclinica.entity.consulta.EstadoDaConsulta;
import mz.ciuem.uclinica.entity.consulta.Medico;
import mz.ciuem.uclinica.entity.consulta.TipoConsulta;
import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.entity.parametro.Factura;
import mz.ciuem.uclinica.entity.parametro.Servico;
import mz.ciuem.uclinica.entity.parametro.ServicoTipo;
import mz.ciuem.uclinica.paciente.service.PacienteService;
import mz.ciuem.uclinica.service.consulta.ConsultaService;
import mz.ciuem.uclinica.service.consulta.MedicoService;
import mz.ciuem.uclinica.service.parametro.FacturaService;
import mz.ciuem.uclinica.service.parametro.ServicoService;

@Controller
@RequestMapping(value = { "/consulta" })
public class ConsultaController {

	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private MedicoService medicoService;
	@Autowired
	private ConsultaService consultaService;

	@Autowired
	private ServicoService servicoService;
	
	@Autowired 
	private FacturaService facturaService;
	
	private Paciente paciente;
	private Consulta consulta;
	

	@GetMapping("/agendar/{id}")
	public ModelAndView agendarConculta(@ModelAttribute ConsultaForm consultaForm, @PathVariable Long id) {

		paciente = pacienteService.find(id);
		ModelAndView model = new ModelAndView("/consultas/agendar-consulta");
		model.addObject("paciente", paciente);
		// consulta.setServicos(servicoService.getAll());
		// servicoList.setServicos((servicoService.getAll()));
		List<Servico> servicos = servicoService.getAll();
		
		model.addObject("servicos", selecionarServicos(servicos));
		preencherFormulario(model);
		model.addObject("consultas", consultaService.getConsultasDoPaciente(paciente));

		return model;
	}

	private List<Servico> selecionarServicos(List<Servico> servicos) {
		
		List<Servico> servicosSelecionados = new ArrayList<>();
		for(Servico s : servicos){
//			     if(s.getServicoTipo().equals(ServicoTipo.CONSULTA_MEDICA))
			    	 servicosSelecionados.add(s);	 
		}
         return servicosSelecionados;
	}

	private void preencherFormulario(ModelAndView model) {
		model.addObject("tipoConsulta", TipoConsulta.values());
		model.addObject("especialidade", Especialidade.values());
		model.addObject("causaAdmissao", CausaAdmissao.values());
		model.addObject("estados", EstadoDaConsulta.values());
		List<Medico> medicos = medicoService.getAll();
		model.addObject("medicos", medicos);
	}

	@PostMapping("/agendar")
	public ModelAndView registarConsulta(@Valid ConsultaForm consultaForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {

		}
		double precoTotal = 0;
		Consulta consulta = consultaForm.getConsulta();

		consulta.setPaciente(paciente);

		consulta.setServicos(consultaForm.getServicos());

		List<Servico> servicosSelecionados = new ArrayList<>();

		for (Servico servico : consultaForm.getServicos()) {
			
			servicosSelecionados.add(servicoService.find(servico.getId()));
//			precoTotal = precoTotal + servicoService.find(servico.getId()).getPreco();
		
		}

		consulta.setServicos(servicosSelecionados);
		consulta.setEstado(EstadoDaConsulta.AGENDADA);
		consulta.setPreco(precoTotal);
		consultaService.create(consulta);

		ModelAndView model = new ModelAndView("redirect:/consulta/" + paciente.getId() + "/list");

		return model;
	}

	@GetMapping("{id}/list")
	public ModelAndView listaConsultasDoPacinte(@PathVariable Long id) {

		Paciente paciente = pacienteService.find(id);
		List<Consulta> consultas = consultaService.getConsultasDoPaciente(paciente);

		ModelAndView model = new ModelAndView("/consultas/paciente-consultas", "consultas", consultas);
		model.addObject("paciente", paciente);

		return model;

	}
	
	@GetMapping("{id}/select")
	public ModelAndView selectConsultaDoPacinte(@PathVariable Long id) {

		Consulta consulta = consultaService.find(id);
		Paciente paciente = consulta.getPaciente();
		//List<Consulta> consultas = consultaService.getConsultasDoPaciente(paciente);

		ModelAndView model = new ModelAndView("/consultas/paciente-uma-consulta", "consulta", consulta);
		model.addObject("paciente", paciente);

		return model;

	}

	@GetMapping("{id}/update")
	public ModelAndView editar(@ModelAttribute ConsultaForm consultaForm, @PathVariable Long id) {

		consultaForm.setConsulta(consultaService.find(id)); 
		paciente = consultaForm.getConsulta().getPaciente();
		List<Servico> servicos = servicoService.getAll(); 
		consultaForm.setServicos(selecionarServicos(servicos));
		
		ModelAndView model = new ModelAndView("/consultas/update-consulta", "consultaForm", consultaForm);
		model.addObject("paciente", consultaForm.getConsulta().getPaciente());
		preencherFormulario(model);
		model.addObject("servicos", selecionarServicos(servicos));
		
		
		return model;

	}

	@PostMapping("/update")

	public ModelAndView aditar(@Valid ConsultaForm consultaForm, BindingResult bindingResult,
			RedirectAttributes redirectAtributes) {
		
		if (bindingResult.hasErrors()) {
			
		}
		double precoTotal = 0;
        Consulta consulta = consultaForm.getConsulta();
        consulta.setPaciente(paciente);
        
        consulta.setServicos(consultaForm.getServicos());
        
        List<Servico> servicosSelecionados = new ArrayList<>();
        
        for(Servico servico : consultaForm.getServicos()){
        	
        	servicosSelecionados.add(servicoService.find(servico.getId()));
//        	precoTotal = precoTotal + servicoService.find(servico.getId()).getPreco();
        }
		
        consulta.setPreco(precoTotal);
        
		consultaService.saveOrUpdate(consulta);
		ModelAndView model = new ModelAndView("redirect:/consulta/" + paciente.getId() + "/list");

		return model;
	}

	@GetMapping(value = { "/list", "/list/" })
	public ModelAndView listarConsultas() {

		List<Consulta> consultas = consultaService.getAll();
		ModelAndView model = new ModelAndView("/consultas/list-consultas");
		model.addObject("consultas", consultas);

		return model;

	}

	@GetMapping("{id}/cancelar")
	public ModelAndView cancelarConsulta(Consulta consulta, @PathVariable Long id) {

		consulta = consultaService.find(id);
		consulta.setEstado(EstadoDaConsulta.CANCELA);

		Paciente paciente = pacienteService.find(consulta.getPaciente().getId());
		// consultaService.saveOrUpdate(consulta);

		List<Consulta> consultas = consultaService.getConsultasDoPaciente(paciente);

		ModelAndView model = new ModelAndView("/consultas/paciente-consultas", "consultas", consultas);
		model.addObject("paciente", paciente);

		return model;
	}
	
	@GetMapping("{id}/cancelar/select")
	public ModelAndView cancelarConsultaSelecionada(Consulta consulta, @PathVariable Long id) {

		consulta = consultaService.find(id);
		consulta.setEstado(EstadoDaConsulta.CANCELA);

		Paciente paciente = pacienteService.find(consulta.getPaciente().getId());

		ModelAndView model = new ModelAndView("/consultas/paciente-uma-consulta", "consulta", consulta);
		model.addObject("paciente", paciente);

		return model;
	}
	
	@GetMapping("{id}/detalhes")
	public ModelAndView detalhesConsulta(@PathVariable Long id){
		
		Consulta consulta = consultaService.find(id);
		Paciente paciente = consulta.getPaciente();
	
		ModelAndView model = new ModelAndView("/consultas/detalhes-de-consulta", "consulta", consulta);
		model.addObject("paciente", paciente);
		
		return model;
	}
	
	@GetMapping(value = { "/listAgendadas", "/listAgendadas/" })
	public ModelAndView listarConsultasAgendadas(){
		
		List<Consulta> consultas = consultaService.getAll();
		List<Consulta> consultasAgendadas = new ArrayList<>();
		
		for(Consulta consulta : consultas){
			
			if(consulta.getEstado().equals(EstadoDaConsulta.AGENDADA))
				consultasAgendadas.add(consulta);
		}
		ModelAndView model = new ModelAndView("/consultas/list-consultas-agendadas", "consultas", consultasAgendadas);
		
		return model;
		
	}


}
