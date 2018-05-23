package mz.ciuem.uclinica.controller.paciente;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ciuem.uclinica.entity.paciente.EstadoCivil;
import mz.ciuem.uclinica.entity.paciente.Genero;
import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.entity.paciente.Raca;
import mz.ciuem.uclinica.entity.paciente.TipoDePaciente;
import mz.ciuem.uclinica.entity.paciente.TipoDocumento;
import mz.ciuem.uclinica.paciente.service.PacienteService;
import mz.ciuem.uclinica.recepcao.modelview.ParametrosDePesquisaPaciente;
import mz.ciuem.uclinica.recepcao.modelview.PesquisarPacienteForm;

@Controller
@RequestMapping("/paciente")
public class PacienteCtrl {

	@Autowired
	private PacienteService pacienteService;
	private List<Paciente> pacientes;

	@GetMapping(value = { "", "/", "add" })
	private ModelAndView addPaciente(Paciente paciente) {

		ModelAndView model = new ModelAndView("paciente/add-paciente");
		preencherFormulario(model);

		return model;
	}

	private void preencherFormulario(ModelAndView model) {

		model.addObject("genero", Genero.values());
		model.addObject("tipoDocumento", TipoDocumento.values());
		model.addObject("raca", Raca.values());
		model.addObject("estadoCivil", EstadoCivil.values());
		model.addObject("tipoDePacientes", TipoDePaciente.values());
	}

	@PostMapping(value = { "add" })
	public ModelAndView registarPaciente(@Valid Paciente paciente, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return addPaciente(paciente);

		}
		paciente.setTipoDePaciente(TipoDePaciente.PACIENTE_GERAL);

		pacienteService.saveOrUpdate(paciente);
		paciente.setNid(paciente.getId());
		pacienteService.update(paciente);

		String redirect = "redirect:" + paciente.getId();

		ModelAndView modelAndView = new ModelAndView(redirect);
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/{id}", "/{id}/" })
	public ModelAndView detalhes(@PathVariable Long id) {

		Paciente paciente = pacienteService.find(id);
		ModelAndView model = new ModelAndView("/paciente/detalhes-paciente", "paciente", paciente);

		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/list", "/list/" })
	public ModelAndView listar() {
		pesquisarPacientes();

		return exibirPacientes();
	}

	private void pesquisarPacientes() {

		List<Paciente> pacietesSelecionados = pacienteService.getAll();
		pacientes = new ArrayList<>();
		for (Paciente p : pacietesSelecionados) {
			if (p.getTipoDePaciente() != null) {
				pacientes.add(p);
			}
		}

	}

	private ModelAndView exibirPacientes() {

		ModelAndView modelAndView = new ModelAndView("/paciente/list-paciente");
		modelAndView.addObject("pacientes", pacientes);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/update")
	public ModelAndView editar(@PathVariable Long id) throws ParseException {

		Paciente paciente = pacienteService.find(id);

		ModelAndView model = new ModelAndView("/paciente/update-paciente", "paciente", paciente);
		model.addObject("genero", Genero.values());
		model.addObject("tipoDocumento", TipoDocumento.values());
		model.addObject("raca", Raca.values());
		model.addObject("estadoCivil", EstadoCivil.values());
		model.addObject("tipoDePaciente", TipoDePaciente.values());

		return model;

	}

	@RequestMapping(method = RequestMethod.POST, value = "/update")
	public ModelAndView editar(@Valid Paciente paciente, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {

			System.err.println("Ocorreu um erro durante o registo de pacientes!");

		}
		// paciente.setNid(paciente.getId());
		pacienteService.saveOrUpdate(paciente);
		ModelAndView model = new ModelAndView("redirect:/paciente/" + paciente.getId());
		redirectAttributes.addFlashAttribute("messageVisible", "true");

		return model;
	}

	@GetMapping("/procurar")
	public ModelAndView agendarConsulta(PesquisarPacienteForm pesquisarPacienteForm) {
		pesquisarPacientes();
		ModelAndView model = new ModelAndView("/paciente/procurar-paciente");
		model.addObject("pacientes", pacientes);
		model.addObject("tipoParametro", ParametrosDePesquisaPaciente.values());
		return model;
	}

	@PostMapping("/procurar")
	public ModelAndView procurarPaciente(PesquisarPacienteForm pesquisarPacienteForm) {
        
		List<Paciente> pacientes = new ArrayList<>();
		List<Paciente> pacietesSelecionados = pacienteService.procurarPacientePor(pesquisarPacienteForm.getTipo(),
				pesquisarPacienteForm.getParametroDePesquisa());
        for (Paciente p : pacietesSelecionados){
        	if (p.getTipoDePaciente() != null) {
				pacientes.add(p);
			}
        }
		ModelAndView model = new ModelAndView("/paciente/procurar-paciente");
		model.addObject("pacientes", pacientes);
		model.addObject("tipoParametro", ParametrosDePesquisaPaciente.values());
		return model;

	}

}
