package mz.ciuem.uclinica.controller.parametro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mz.ciuem.uclinica.entity.parametro.ServicoFuncionario;
import mz.ciuem.uclinica.service.parametro.ServicoFuncionarioService;

@Controller
@RequestMapping("/parametro")
public class ServicoFuncionarioController {
	@Autowired
	private ServicoFuncionarioService servicofuncionarioService;
	
	
	@RequestMapping(value = "/funcionario", method = RequestMethod.GET)
	public ModelAndView servico_por_unidade(ServicoFuncionario servicoFuncionario){
		
		ModelAndView model = new ModelAndView("/parametros/servicos_por_unidade");
		return model;
	}
	
}
