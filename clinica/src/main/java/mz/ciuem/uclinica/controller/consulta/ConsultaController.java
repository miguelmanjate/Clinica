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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ciuem.uclinica.entity.consulta.CausaAdmissao;
import mz.ciuem.uclinica.entity.consulta.Consulta;
import mz.ciuem.uclinica.entity.consulta.ConsultaForm;
import mz.ciuem.uclinica.entity.consulta.Especialidade;
import mz.ciuem.uclinica.entity.consulta.Medico;
import mz.ciuem.uclinica.entity.consulta.TipoConsulta;
import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.entity.parametro.Servico;
import mz.ciuem.uclinica.paciente.service.PacienteService;
import mz.ciuem.uclinica.service.consulta.ConsultaService;
import mz.ciuem.uclinica.service.consulta.MedicoService;
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
	private Paciente paciente;
	private Consulta consulta;

	@GetMapping("/agendar/{id}")
	public ModelAndView agendarConculta(@ModelAttribute ConsultaForm consultaForm, @PathVariable Long id) {

		paciente = pacienteService.find(id);
		ModelAndView model = new ModelAndView("/consultas/agendar-consulta");
		model.addObject("paciente", paciente);
		// consulta.setServicos(servicoService.getAll());
		// servicoList.setServicos((servicoService.getAll()));
		model.addObject("servicos", servicoService.getAll());
		preencherFormulario(model);
		model.addObject("consultas",consultaService.getConsultasDoPaciente(paciente));

		return model;
	}

	private void preencherFormulario(ModelAndView model) {
		model.addObject("tipoConsulta", TipoConsulta.values());
		model.addObject("especialidade", Especialidade.values());
		model.addObject("causaAdmissao", CausaAdmissao.values());
		List<Medico> medicos = medicoService.getAll();
		model.addObject("medicos", medicos);
	}

	@PostMapping("/agendar")
	public ModelAndView registarConsulta(@Valid ConsultaForm consultaForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {

		}

		Consulta consulta = consultaForm.getConsulta();

		consulta.setPaciente(paciente);

		consulta.setServicos(consultaForm.getServicos());

		List<Servico> servicosSelecionados = new ArrayList<>();

		for (Servico servico : consultaForm.getServicos()) {
			servicosSelecionados.add(servicoService.find(servico.getId()));

		}

		consulta.setServicos(servicosSelecionados);
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

	@GetMapping("{id}/update")

	public ModelAndView editar(Consulta consulta, @PathVariable Long id) {

		consulta = consultaService.find(id);

		ModelAndView model = new ModelAndView("/consultas/update-consulta", "consulta", consulta);
		model.addObject("paciente", consulta.getPaciente());
		preencherFormulario(model);
		return model;

	}

	@PostMapping("/update")

	public ModelAndView aditar(@Valid Consulta consulta, BindingResult bindingResult,
			RedirectAttributes redirectAtributes) {

		consulta.setPaciente(paciente);
		if (bindingResult.hasErrors()) {
		}

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

}
