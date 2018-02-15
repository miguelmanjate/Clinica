package mz.ciuem.uclinica.controller.parametro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ciuem.uclinica.entity.parametro.Especialidade;
import mz.ciuem.uclinica.service.parametro.EspecialidadeService;

@Controller
@RequestMapping("/parametro")
public class EspecialidadeController {
	
	
	@Autowired
	private EspecialidadeService especialidadeService;
	@RequestMapping(value = "/especialidade", method = RequestMethod.GET)
	public ModelAndView especialidades(Especialidade especialidade) {
		
		ModelAndView model = new ModelAndView("/parametros/csuem_especialidade");

		return model;
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "especialidade")
	public ModelAndView gravarServico(Especialidade especialidade, RedirectAttributes redirectAttributes){
			
		especialidadeService.saveOrUpdate(especialidade);
		
		ModelAndView model = new ModelAndView("redirect:/parametro/especialidade");
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		return model;
	}

}
