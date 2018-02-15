package mz.ciuem.uclinica.controller.parametro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ciuem.uclinica.entity.parametro.Actividade;
import mz.ciuem.uclinica.entity.parametro.ActividadeDoSector;
import mz.ciuem.uclinica.service.parametro.ActividadeDoSectorService;
import mz.ciuem.uclinica.service.parametro.ActividadeService;

@Controller
@RequestMapping("/parametro")
public class ActividadesDoSectorController {
	@Autowired
	private ActividadeDoSectorService actividadedosectorService;
	@RequestMapping(value = "/actividade_sector", method = RequestMethod.GET)
	public ModelAndView actividadesdosector(ActividadeDoSector actividadedosector){
		
		ModelAndView model = new ModelAndView("/parametros/actividades_sector");

		return model;
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "actividades_sector")
	public ModelAndView gravarCategoria(ActividadeDoSector actividadedosector, RedirectAttributes redirectAttributes){
		
		actividadedosectorService.saveOrUpdate(actividadedosector);
		
		ModelAndView model = new ModelAndView("redirect:actividadedosector");
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		return model;
	}
}
