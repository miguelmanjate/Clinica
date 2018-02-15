package mz.ciuem.uclinica.controller.consulta;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mz.ciuem.uclinica.recepcao.modelview.ParametrosDePesquisaPaciente;
import mz.ciuem.uclinica.recepcao.modelview.PesquisarPacienteForm;

@Controller
@RequestMapping("/recepcao/contabilidade")
public class pesquisarPacienteContabilidadeControle {
	
	@GetMapping("")
	public ModelAndView iniciarPagamento(PesquisarPacienteForm pesquisarPacienteForm){
		
		ModelAndView model = new ModelAndView("/recepcao/procurar_paciente_contabilidade");
		model.addObject("tipoParametro", ParametrosDePesquisaPaciente.values());
		return model;
		
	}

}
