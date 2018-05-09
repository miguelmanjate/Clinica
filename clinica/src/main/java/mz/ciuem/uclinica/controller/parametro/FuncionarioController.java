package mz.ciuem.uclinica.controller.parametro;

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

import mz.ciuem.uclinica.entity.funcionario.Funcionario;
import mz.ciuem.uclinica.entity.funcionario.NivelAcademico;
import mz.ciuem.uclinica.entity.paciente.EstadoCivil;
import mz.ciuem.uclinica.entity.paciente.Genero;
import mz.ciuem.uclinica.service.parametro.FuncionarioService;
import mz.ciuem.uclinica.service.parametro.UnidadesService;

@Controller
@RequestMapping("/funcionario/uem")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private UnidadesService unidadesService;

	@GetMapping(value = { "", "/", "add" })
	private ModelAndView funcionario(Funcionario funcionario) {

		ModelAndView model = new ModelAndView("/funcionario/add-funcionario");
		model.addObject("estadoCivil", EstadoCivil.values());
		model.addObject("genero", Genero.values());
		model.addObject("nivelAcademico", NivelAcademico.values());
		model.addObject("unidades", unidadesService.getAll());

		return model;

	}

	@PostMapping(value = { "add" })
	public ModelAndView addFuncionario(@Valid Funcionario funcionario, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			
			return funcionario(funcionario);

		}

		funcionarioService.saveOrUpdate(funcionario);
		
		String redirect = "redirect:/funcionario/uem/"+funcionario.getId();
		
		ModelAndView modelandview = new ModelAndView(redirect);
		redirectAttributes.addFlashAttribute("messageVisible", "true");

		return modelandview;
		
	}


	@RequestMapping(method = RequestMethod.GET, value = { "/{id}", "/{id}/" })
	public ModelAndView detalhes(@PathVariable Long id) {

		Funcionario funcionario = funcionarioService.find(id);

		ModelAndView model = new ModelAndView("/funcionario/detalhes-funcionario", "funcionario", funcionario);

		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public ModelAndView listar() {

		List<Funcionario> funcionarios = funcionarioService.getAll();

		ModelAndView modelAndView = new ModelAndView("/funcionario/list-funcionario");
		modelAndView.addObject("funcionarios", funcionarios);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/update")
	public ModelAndView editar(@PathVariable Long id) {

		Funcionario funcionario = funcionarioService.find(id);
		
		ModelAndView model = new ModelAndView("/funcionario/update-funcionario", "funcionario", funcionario);
		model.addObject("estadoCivil", EstadoCivil.values());
		model.addObject("genero", Genero.values());
		model.addObject("nivelAcademico", NivelAcademico.values());
		model.addObject("unidades", unidadesService.getAll());

		return model;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/update")
	public ModelAndView editar(@Valid Funcionario funcionario, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
         System.err.println("Tem error");
			return editar(funcionario.getId());
		}

		funcionarioService.saveOrUpdate(funcionario);
		Funcionario f = funcionarioService.find(funcionario.getId());
		
		String redirect = "redirect:/funcionario/uem/"+f.getId();
		
		ModelAndView model = new ModelAndView(redirect);
		
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		
		return model;
	}
	
	
	
}
