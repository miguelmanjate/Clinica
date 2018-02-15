package mz.ciuem.uclinica.controller.parametro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ciuem.uclinica.entity.parametro.Grupo;
import mz.ciuem.uclinica.service.parametro.GrupoService;

@Controller
@RequestMapping("/parametro")
public class GrupoController {
	
	
	@Autowired
	private GrupoService grupoService;
	@RequestMapping(value = "/grupo", method = RequestMethod.GET)
	public ModelAndView grupos(Grupo grupo) {
		
		ModelAndView model = new ModelAndView("/parametros/grupo");
		return model;
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "grupo")
	public ModelAndView gravarGrupo(Grupo grupo, RedirectAttributes redirectAttributes){
		
		
		grupoService.saveOrUpdate(grupo);
		
		ModelAndView model = new ModelAndView("redirect:grupo");
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		return model;
	}
}
