package mz.ciuem.uclinica.controller.parametro;

import java.text.ParseException;

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

import mz.ciuem.uclinica.entity.consulta.Especialidade;
import mz.ciuem.uclinica.entity.consulta.Medico;
import mz.ciuem.uclinica.entity.paciente.EstadoCivil;
import mz.ciuem.uclinica.entity.paciente.Genero;
import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.entity.paciente.Raca;
import mz.ciuem.uclinica.entity.paciente.TipoDocumento;
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
		modelAndView.addObject("medicos", medicoService.getAll());
		
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/update")
	public ModelAndView editar(@PathVariable Long id) throws ParseException {

		Medico medico = medicoService.find(id);

		
		ModelAndView model = new ModelAndView("/parametros/medico/update-medico", "medico", medico);
		model.addObject("especialidade", Especialidade.values());
		model.addObject("medicos", medicoService.getAll());

		return model;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/update")
	public ModelAndView editar(@Valid Medico medico, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
 
		if (bindingResult.hasErrors()) {

			System.out.println("Ocorreu um erro durante o registo de pacientes!");

		}
	
		medicoService.saveOrUpdate(medico);
		ModelAndView model = new ModelAndView("redirect:/parametro/medico");
		redirectAttributes.addFlashAttribute("messageVisible", "true");

		return model;
	}

}
