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

import mz.ciuem.uclinica.entity.paciente.EstadoCivil;
import mz.ciuem.uclinica.entity.paciente.Genero;
import mz.ciuem.uclinica.entity.parametro.Funcionario;
import mz.ciuem.uclinica.entity.parametro.Servico;
import mz.ciuem.uclinica.entity.parametro.ServicoDaUnidade;
import mz.ciuem.uclinica.service.parametro.FuncionarioService;
import mz.ciuem.uclinica.service.parametro.SectorService;

@Controller
@RequestMapping("/parametro/funcionario/uem")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private SectorService sectorService;

	@GetMapping(value = { "", "/", "add" })
	private ModelAndView funcionario(Funcionario funcionario) {

		ModelAndView model = new ModelAndView("/parametros/add-funcionario");
		model.addObject("estado_civil", EstadoCivil.values());
		model.addObject("genero", Genero.values());
		model.addObject("sectores", sectorService.getAll());

		return model;

	}

	@PostMapping(value = { "add" })
	public ModelAndView addFuncionario(@Valid Funcionario funcionario, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {

			return funcionario(funcionario);

		}

		funcionarioService.saveOrUpdate(funcionario);
		
		String redirect = "redirect:/parametro/funcionario/uem/"+funcionario.getId();
		
		ModelAndView modelandview = new ModelAndView(redirect);
		redirectAttributes.addFlashAttribute("messageVisible", "true");

		return modelandview;
	}


	@RequestMapping(method = RequestMethod.GET, value = { "/{id}", "/{id}/" })
	public ModelAndView detalhes(@PathVariable Long id) {

		Funcionario funcionario = funcionarioService.find(id);

		ModelAndView model = new ModelAndView("/parametros/detalhes-funcionario", "funcionario", funcionario);

		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public ModelAndView listar() {

		List<Funcionario> funcionarios = funcionarioService.getAll();

		ModelAndView modelAndView = new ModelAndView("/parametros/funcionarios");
		modelAndView.addObject("funcionarios", funcionarios);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/update")
	public ModelAndView editar(@PathVariable Long id) {

		Funcionario funcionario = funcionarioService.find(id);
		
		ModelAndView model = new ModelAndView("/parametros/update-funcionario", "funcionario", funcionario);
		model.addObject("estado_civil", EstadoCivil.values());
		model.addObject("genero", Genero.values());
		model.addObject("sectores", sectorService.getAll());

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
		
		String redirect = "redirect:/parametro/funcionario/uem/"+f.getId();
		
		ModelAndView model = new ModelAndView(redirect);
		
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		
		return model;
	}
	
	
	
}
