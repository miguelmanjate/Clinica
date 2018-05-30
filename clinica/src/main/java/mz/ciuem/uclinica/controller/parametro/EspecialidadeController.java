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

import mz.ciuem.uclinica.entity.parametro.Especialidade;
import mz.ciuem.uclinica.entity.parametro.ServicoTipo;
import mz.ciuem.uclinica.service.parametro.EspecialidadeService;
import mz.ciuem.uclinica.service.parametro.SectorService;

@Controller
@RequestMapping("/parametro/especialidades")
public class EspecialidadeController {
	
	
	@Autowired
	private EspecialidadeService especialidadeService;
	
	@Autowired
	private SectorService sectorService;
	
	@GetMapping(value = { "", "/", "add" })
	public ModelAndView novaEspecialidade(Especialidade especialidade) {
		
		ModelAndView model = new ModelAndView("/parametros/especialidades/add-especialidade");

		return preencher(model);
	}
	
	private ModelAndView preencher(ModelAndView model){
		
		model.addObject("especialidades", especialidadeService.getAll());
		model.addObject("servicoTipos", ServicoTipo.values());
		model.addObject("sectores", sectorService.getAll());
		return model;
	}
	@PostMapping(value = {"/add"})
	public ModelAndView addEspecialidade(@Valid Especialidade especialidade, BindingResult bindingResult,
			RedirectAttributes redirectAttributes){
		
		if(bindingResult.hasErrors()){
			return novaEspecialidade(especialidade);
		}
		especialidadeService.saveOrUpdate(especialidade);
		
		String redirect = "redirect: "+especialidade.getId();
		ModelAndView modelAndView = new ModelAndView(redirect);
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = { "/{id}", "/{id}/"})
	public ModelAndView detalhes(@PathVariable Long id) {
		
		Especialidade especialidade = especialidadeService.find(id);
		
		ModelAndView model = new ModelAndView("parametros/especialidades/detalhes-especialidade", "especialidade", especialidade);
		
		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public ModelAndView listar() {

		List<Especialidade> especialidades = especialidadeService.getAll();

		ModelAndView modelAndView = new ModelAndView("/parametros/especialidades/especialidades");
		modelAndView.addObject("especialidades", especialidades);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/update")
	public ModelAndView editar(@PathVariable Long id) {

		Especialidade especialidade = especialidadeService.find(id);
		
		ModelAndView model = new ModelAndView("/parametros/especialidades/update-especialidade", "especialidade", especialidade);
		
		preencher(model);
		
		return model;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/update")
	public ModelAndView editar(@Valid Especialidade especialidade, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
        
			return editar(especialidade.getId());
		}

		especialidadeService.saveOrUpdate(especialidade);
		String redirect = "redirect: "+especialidade.getId();
		ModelAndView model = new ModelAndView(redirect);
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		return model;
	}

}
