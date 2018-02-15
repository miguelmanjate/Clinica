package mz.ciuem.uclinica.controller.parametro.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mz.ciuem.uclinica.entity.parametro.ServicoDoSector;
import mz.ciuem.uclinica.service.parametro.ServicoDoSectorService;

@Controller
@RequestMapping("/parametro")
public class ServicoDoSectorController {
	@Autowired
	private ServicoDoSectorService servicodosectorService;
	
	
	@RequestMapping(value = "/servicos-sector", method = RequestMethod.GET)
	public ModelAndView servico_sector(ServicoDoSector servicodosector){	
		ModelAndView model = new ModelAndView("/parametros/servicos_sector");

		return model;
	}
	
	@RequestMapping(value = "/servico_sector_funcionario", method = RequestMethod.GET)
	public ModelAndView servico_sector_funcionario(ServicoDoSector servicodosector){
		
		ModelAndView model = new ModelAndView("/parametros/servicos_sector_funcionario");

		return model;
	}
}
