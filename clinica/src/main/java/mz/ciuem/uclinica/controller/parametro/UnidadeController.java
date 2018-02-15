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

import mz.ciuem.uclinica.entity.parametro.Orgao;
import mz.ciuem.uclinica.entity.parametro.Servico;
import mz.ciuem.uclinica.entity.parametro.ServicoDaUnidade;
import mz.ciuem.uclinica.entity.parametro.Unidades;
import mz.ciuem.uclinica.service.parametro.OrgaoService;
import mz.ciuem.uclinica.service.parametro.UnidadesService;

@Controller
@RequestMapping("/parametro/unidade")
public class UnidadeController {

	@Autowired
	private UnidadesService unidadesService;
	@Autowired
	private OrgaoService orgaoService;

	@GetMapping(value = { "", "/", "add" })
	private ModelAndView unidade(Unidades unidades) {

		ModelAndView model = new ModelAndView("parametros/add-unidades");
		List<Orgao> orgoes = orgaoService.getAll();
		model.addObject("orgoes", orgoes);
		model.addObject("unidadess", unidadesService.getAll());
		
		return model;
	}

	@PostMapping(value = {"/add"})
	private ModelAndView addUnidades(@Valid Unidades unidades, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return unidade(unidades);
		}
		unidadesService.saveOrUpdate(unidades);
		
		String redirect = "redirect:/parametro/unidade/"+unidades.getId();
		ModelAndView modelAndView = new ModelAndView(redirect);
		redirectAttributes.addFlashAttribute("messageVisible", "true");
	
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = { "/{id}", "/{id}/"})
	public ModelAndView detalhes(@PathVariable Long id) {
		
		Unidades unidades = unidadesService.find(id);
		
		ModelAndView model = new ModelAndView("parametros/detalhes-unidades", "unidades", unidades);
		
		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public ModelAndView listar() {

		List<Unidades> unidades = unidadesService.getAll();

		ModelAndView modelAndView = new ModelAndView("/parametros/unidades");
		modelAndView.addObject("unidades", unidades);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/update")
	public ModelAndView editar(@PathVariable Long id) {

		Unidades unidades = unidadesService.find(id);
		List<Orgao> orgoes = orgaoService.getAll();
		ModelAndView model = new ModelAndView("/parametros/update-unidades", "unidades", unidades);
		model.addObject("orgoes", orgoes);

		return model;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/update")
	public ModelAndView editar(@Valid Unidades unidades, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
         System.err.println("Tem error");
			return editar(unidades.getId());
		}

		unidadesService.saveOrUpdate(unidades);
	
		ModelAndView model = new ModelAndView("redirect:/parametro/unidade/list");
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		return model;
	}

}
