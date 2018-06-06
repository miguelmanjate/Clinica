package mz.ciuem.uclinica.controller.consulta;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ciuem.uclinica.entity.consulta.CausaAdmissao;
import mz.ciuem.uclinica.entity.consulta.Consulta;
import mz.ciuem.uclinica.entity.consulta.ConsultaForm;
import mz.ciuem.uclinica.entity.consulta.EstadoDaConsulta;
import mz.ciuem.uclinica.entity.consulta.ItemConsultaServico;
import mz.ciuem.uclinica.entity.consulta.MedicoJson;
import mz.ciuem.uclinica.entity.consulta.ServicoJason;
import mz.ciuem.uclinica.entity.consulta.TipoConsulta;
import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.entity.parametro.Servico;
import mz.ciuem.uclinica.paciente.service.PacienteService;
import mz.ciuem.uclinica.service.consulta.ConsultaService;
import mz.ciuem.uclinica.service.consulta.ItemConsultaService;
import mz.ciuem.uclinica.service.consulta.ItemConsultaServicoServiceImpl;
import mz.ciuem.uclinica.service.consulta.MedicoService;
import mz.ciuem.uclinica.service.parametro.EspecialidadeService;
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

	@Autowired
	private EspecialidadeService especialidadeService;

	@Autowired
	private ItemConsultaService itemConsultaServicoService;

	private Paciente paciente;
	private Consulta consulta;

	@GetMapping("/agendar/{id}")
	public ModelAndView agendarConculta(@ModelAttribute ConsultaForm consultaForm, @PathVariable Long id) {

		paciente = pacienteService.find(id);
		ModelAndView model = new ModelAndView("/consultas/agendar-consulta");
		model.addObject("paciente", paciente);

		preencherFormulario(model);
		model.addObject("consultas", consultaService.getConsultasDoPaciente(paciente));

		return model;
	}

	private List<Servico> selecionarServicos(List<Servico> servicos) {

		List<Servico> servicosSelecionados = new ArrayList<>();
		for (Servico s : servicos) {
			// if(s.getServicoTipo().equals(ServicoTipo.CONSULTA_MEDICA))
			servicosSelecionados.add(s);
		}
		return servicosSelecionados;
	}

	private void preencherFormulario(ModelAndView model) {
		model.addObject("tipoConsulta", TipoConsulta.values());
		model.addObject("especialidade", especialidadeService.getAll());
		model.addObject("causaAdmissao", CausaAdmissao.values());
		model.addObject("estados", EstadoDaConsulta.values());

	}

	@PostMapping("/agendar")
	public ModelAndView registarConsulta(@Valid ConsultaForm consultaForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {

		}
		double precoTotal = 0;
		Consulta consulta = consultaForm.getConsulta();

		consulta.setPaciente(paciente);

		List<Servico> servicosSelecionados = new ArrayList<>();

		for (Servico servico : consultaForm.getServicos()) {

			servicosSelecionados.add(servicoService.find(servico.getId()));
		}

		consulta.setEstado(EstadoDaConsulta.AGENDADA);
		consultaService.create(consulta);
				
		try {
			itemConsultaServicoService.criarItemConsultaListaServicos(consulta, servicosSelecionados);

		} catch (RuntimeException e) {
			System.err.println(e);
			redirectAttributes.addFlashAttribute("msgSemTaxas", "true");
			ModelAndView model = new ModelAndView("redirect:/consulta/agendar/" + paciente.getId());

			return model;
		}

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
		// List<Consulta> consultas =
		// consultaService.getConsultasDoPaciente(paciente);

		ModelAndView model = new ModelAndView("/consultas/paciente-uma-consulta", "consulta", consulta);
		model.addObject("paciente", paciente);

		return model;

	}

	@GetMapping("{id}/update")
	public ModelAndView editar(@ModelAttribute ConsultaForm consultaForm, @PathVariable Long id) {

		Consulta consulta = consultaService.find(id);
		consultaForm.setConsulta(consulta);
		paciente = consultaForm.getConsulta().getPaciente();
		consultaForm.setItems(consulta.getItemConsultaServicos());

		ModelAndView model = new ModelAndView("/consultas/update-consulta", "consultaForm", consultaForm);
		model.addObject("paciente", consultaForm.getConsulta().getPaciente());
		preencherFormulario(model);

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

		List<Servico> servicosSelecionados = new ArrayList<>();

		for (Servico servico : consultaForm.getServicos()) {

			servicosSelecionados.add(servicoService.find(servico.getId()));

		}

		consultaService.saveOrUpdate(consulta);
		try {
			System.err.println("try");

			itemConsultaServicoService.actualizarItemConsultaListaServicos(consulta, servicosSelecionados);
		} catch (RuntimeException e) {

			redirectAtributes.addFlashAttribute("msgSemTaxas", "true");
			ModelAndView model = new ModelAndView("redirect:/consulta/agendar/" + paciente.getId());

			return model;
		}
		ModelAndView model = new ModelAndView("redirect:/consulta/" + consulta.getId() + "/detalhes");

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
	public ModelAndView detalhesConsulta(@PathVariable Long id) {

		Consulta consulta = consultaService.find(id);
		Paciente paciente = consulta.getPaciente();

		ModelAndView model = new ModelAndView("/consultas/detalhes-de-consulta", "consulta", consulta);
		model.addObject("paciente", paciente);

		return model;
	}

	@GetMapping(value = { "/listAgendadas", "/listAgendadas/" })
	public ModelAndView listarConsultasAgendadas() {

		List<Consulta> consultas = consultaService.getAll();
		List<Consulta> consultasAgendadas = new ArrayList<>();

		for (Consulta consulta : consultas) {

			if (consulta.getEstado().equals(EstadoDaConsulta.AGENDADA))
				consultasAgendadas.add(consulta);
		}
		ModelAndView model = new ModelAndView("/consultas/list-consultas-agendadas", "consultas", consultasAgendadas);

		return model;

	}

	@RequestMapping(value = { "/agendar/{id}/medicos", "/{id}/update/medicos" })
	public @ResponseBody List<MedicoJson> getMedicosPorEspecialidade(@RequestParam String especialidade) {

		List<MedicoJson> medicos = medicoService.getTodosMedicosDaEspecialidade(especialidade);

		return medicos;
	}

	@RequestMapping(value = { "/agendar/{id}/servicos", "{id}/update/servicos" })
	public @ResponseBody List<ServicoJason> getServicosPorEspecialidade(@RequestParam String especialidade) {

		List<ServicoJason> servicos = servicoService.getTodosServicosDaEspecialidadeToJson(especialidade);

		return servicos;
	}

}
