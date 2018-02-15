package mz.ciuem.uclinica.controller.parametro.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ciuem.uclinica.entity.parametro.ServicoDoSector;
import mz.ciuem.uclinica.service.parametro.ServicoDoSectorService;

@Controller
@RequestMapping("/parametro")
public class ServicosDoSectorController {
	
	
	@Autowired
	private ServicoDoSectorService servicoDoSectorService;
	@RequestMapping(value = "/csuem_servico_do_sector", method = RequestMethod.GET)
	public ModelAndView servicos_do_sector(ServicoDoSector servico_do_sector) {
		
		ModelAndView model = new ModelAndView("/parametros/csuem_servicos_sector");
		return model;
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "csuem_servico_do_sector")
	public ModelAndView gravarServico(ServicoDoSector servico_do_sector, RedirectAttributes redirectAttributes){
		
		
		servicoDoSectorService.saveOrUpdate(servico_do_sector);
		
		ModelAndView model = new ModelAndView("redirect:csuem_servico_do_sector");
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		return model;
	}
}
