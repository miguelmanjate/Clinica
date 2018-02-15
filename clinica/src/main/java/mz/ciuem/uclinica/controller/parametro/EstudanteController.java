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
import mz.ciuem.uclinica.entity.parametro.Estudante;
import mz.ciuem.uclinica.entity.parametro.Funcionario;
import mz.ciuem.uclinica.entity.parametro.Servico;
import mz.ciuem.uclinica.service.parametro.EstudanteService;

@Controller
@RequestMapping("/parametro/estudante")
public class EstudanteController {
	
	@Autowired
	private EstudanteService estudanteService;
	
	@GetMapping(value ={"", "/", "add"})
	private ModelAndView estudante(Estudante estudante){
		
		ModelAndView model = new ModelAndView("parametros/add-estudante");
		model.addObject("estado_civil", EstadoCivil.values());
		model.addObject("genero", Genero.values());
		
		return model;
	}
	
	
	@PostMapping(value ={"/add"})
	public ModelAndView addestudantes(@Valid Estudante estudante , BindingResult bindingResult,
			RedirectAttributes redirectAttributes){
		
		if(bindingResult.hasErrors()){
			
			return estudante(estudante);
		}
		
		estudanteService.saveOrUpdate(estudante);
		
		String redirect = "redirect:/parametro/estudante/"+estudante.getId();
		
		ModelAndView model = new ModelAndView(redirect);
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		
		return model;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = { "/{id}", "/{id}/"})
	public ModelAndView detalhes(@PathVariable Long id) {
		
		Estudante estudante = estudanteService.find(id);
		
		ModelAndView model = new ModelAndView("parametros/detalhes-estudante", "estudante", estudante);
		
		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public ModelAndView listar() {

		List<Estudante> estudantes = estudanteService.getAll();

		ModelAndView modelAndView = new ModelAndView("parametros/estudantes");
		modelAndView.addObject("estudantes", estudantes);
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/update")
	public ModelAndView editar(@PathVariable Long id) {

		Estudante estudante = estudanteService.find(id);
		
		ModelAndView model = new ModelAndView("/parametros/update-estudante", "estudante", estudante);
		model.addObject("estado_civil", EstadoCivil.values());
		model.addObject("genero", Genero.values());

		return model;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/update")
	public ModelAndView editar(@Valid Estudante estudante, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
         System.err.println("Tem error");
			return editar(estudante.getId());
		}

		estudanteService.saveOrUpdate(estudante);
		Estudante e = estudanteService.find(estudante.getId());
		
		String redirect = "redirect:/parametro/estudante/"+e.getId();
		
		ModelAndView model = new ModelAndView(redirect);
		
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		
		return model;
	}
	

}
