package mz.ciuem.uclinica.controller.paciente;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ciuem.uclinica.entity.consulta.Especialidade;
import mz.ciuem.uclinica.entity.consulta.Medico;
import mz.ciuem.uclinica.service.consulta.MedicoService;

@Controller
@RequestMapping("/parametro/medico")
public class MedicoController {
	
	@Autowired
	private MedicoService medicoService;
	
	@GetMapping(value = {"","/"})
	
	public ModelAndView addMedico(Medico medico){
		
		ModelAndView modelAndView = new ModelAndView("/parametros/medico/add-medico");
		modelAndView.addObject("especialidade", Especialidade.values());
		
		return modelAndView;
	}
	
	@PostMapping(value = {"","/"})
	public ModelAndView gravarMedico(@Valid Medico medico, BindingResult bindingResult, RedirectAttributes redirectAttributes){
		
		if (bindingResult.hasErrors()) {
			
			return addMedico(medico);
			
		}
		medicoService.saveOrUpdate(medico);
		ModelAndView model = new ModelAndView("redirect:/parametro/medico");
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		return model;
	}

}
