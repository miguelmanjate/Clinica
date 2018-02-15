package mz.ciuem.uclinica.controller.parametro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ciuem.uclinica.entity.parametro.ExamesDoPaciente;
import mz.ciuem.uclinica.service.parametro.ExamesDoPacienteService;

@Controller
@RequestMapping("/parametro")
public class ExamesDoPacienteController {
	
	
	@Autowired
	private ExamesDoPacienteService examesDoPacienteService;
	@RequestMapping(value = "/exame", method = RequestMethod.GET)
	public ModelAndView exames(ExamesDoPaciente exames_do_paciente) {
		
		ModelAndView model = new ModelAndView("/parametros/exame");

		return model;
	}
	
	@RequestMapping(value = "/pcmso_exame", method = RequestMethod.GET)
	public ModelAndView pcmso_exames(ExamesDoPaciente exames_do_paciente) {
		
		ModelAndView model = new ModelAndView("/parametros/pcmso_exames");

		return model;
	}
	
	@RequestMapping(value = "/exame_por_risco", method = RequestMethod.GET)
	public ModelAndView exame_por_risco(ExamesDoPaciente exames_do_paciente) {
		
		ModelAndView model = new ModelAndView("/parametros/exame_por_risco");
		return model;
	}
	
	
	@RequestMapping(method = RequestMethod.POST , value = "exame")
	public ModelAndView gravarExame(ExamesDoPaciente exames_do_paciente, RedirectAttributes redirectAttributes){
		
		
		examesDoPacienteService.saveOrUpdate(exames_do_paciente);
		
		ModelAndView model = new ModelAndView("redirect:exame");
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		return model;
	}
}
