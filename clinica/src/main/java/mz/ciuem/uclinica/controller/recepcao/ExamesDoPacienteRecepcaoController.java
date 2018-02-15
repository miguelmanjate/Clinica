package mz.ciuem.uclinica.controller.recepcao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ciuem.uclinica.entity.parametro.ExamesDoPaciente;
import mz.ciuem.uclinica.service.parametro.ExamesDoPacienteService;

@Controller

public class ExamesDoPacienteRecepcaoController {
	
	
	@Autowired
	private ExamesDoPacienteService examesPacienteService;
	@RequestMapping(value = "/main_recepcao", method = RequestMethod.GET)
	public ModelAndView recepcao(ExamesDoPaciente exames_do_paciente) {
		
		ModelAndView model = new ModelAndView("/recepcao/main_recepcao");

		return model;
	}
	
	
	@RequestMapping(value = "/registar_pac_externo", method = RequestMethod.GET)
	public ModelAndView registar_pac_externo(ExamesDoPaciente exames_do_paciente) {
		
		ModelAndView model = new ModelAndView("/recepcao/registar_pac_externo");

		return model;
	}
	
	
}
