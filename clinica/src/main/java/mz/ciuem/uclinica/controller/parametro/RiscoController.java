package mz.ciuem.uclinica.controller.parametro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mz.ciuem.uclinica.entity.parametro.Risco;
import mz.ciuem.uclinica.service.parametro.RiscoService;

@Controller
@RequestMapping("/parametro")
public class RiscoController {
	@Autowired
	private RiscoService riscoService;
	@RequestMapping(value = "/pcmso_risco", method = RequestMethod.GET)
	public ModelAndView pcmso_risco(Risco risco){		
		ModelAndView model = new ModelAndView("/parametros/pcmso_riscos");

		return model;
	}
	
	@RequestMapping(value = "/risco", method = RequestMethod.GET)
	public ModelAndView risco(Risco risco){
		
		ModelAndView model = new ModelAndView("/parametros/riscos");

		return model;
	}
	
	@RequestMapping(value = "/risco_actividade", method = RequestMethod.GET)
	public ModelAndView risco_actividade(Risco risco){
		
		ModelAndView model = new ModelAndView("/parametros/risco_actividade");

		return model;
	}
}
