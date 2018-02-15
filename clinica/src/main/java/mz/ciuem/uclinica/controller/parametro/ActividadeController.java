package mz.ciuem.uclinica.controller.parametro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ciuem.uclinica.entity.parametro.Actividade;
import mz.ciuem.uclinica.service.parametro.ActividadeService;

@Controller
@RequestMapping("/parametro")
public class ActividadeController {
	@Autowired
	private ActividadeService actividadeService;
	@RequestMapping(value = "/actividade", method = RequestMethod.GET)
	public ModelAndView actividades(Actividade actividade){
		
		ModelAndView model = new ModelAndView("/parametros/actividades_por_grupo");

		return model;
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "actividades")
	public ModelAndView gravarCategoria(Actividade actividade, RedirectAttributes redirectAttributes){
		
		
		actividadeService.saveOrUpdate(actividade);
		
		ModelAndView model = new ModelAndView("redirect:actividade");
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		return model;
	}
	
	@RequestMapping(value = "/tipo_actividade", method = RequestMethod.GET)
	public ModelAndView tipo_actividades(Actividade actividade){
		
		ModelAndView model = new ModelAndView("/parametros/tipo_actividade");
		return model;
	}
	
}
