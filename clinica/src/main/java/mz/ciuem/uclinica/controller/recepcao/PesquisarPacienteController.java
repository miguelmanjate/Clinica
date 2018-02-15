package mz.ciuem.uclinica.controller.recepcao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mz.ciuem.uclinica.recepcao.modelview.ParametrosDePesquisaPaciente;
import mz.ciuem.uclinica.recepcao.modelview.PesquisarPacienteForm;

@Controller
@RequestMapping("/recepcao")
public class PesquisarPacienteController {
	
	@GetMapping("")
	public ModelAndView agendarConsulta( PesquisarPacienteForm pesquisarPacienteForm){
		
		ModelAndView model = new ModelAndView("/recepcao/procurar-paciente");
		model.addObject("tipoParametro",ParametrosDePesquisaPaciente.values());
		return model;
	}

}
