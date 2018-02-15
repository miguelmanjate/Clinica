package mz.ciuem.uclinica.controller.recepcao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mz.ciuem.uclinica.recepcao.modelview.ParametrosDePesquisaPaciente;
import mz.ciuem.uclinica.recepcao.modelview.PesquisarPacienteForm;

@Controller
public class ServicosDoUtenteRecepcaoController {

	/*@Autowired
	private PesquisarPacienteForm p;*/
	@RequestMapping(value = "/main_recepcao/utente", method = RequestMethod.GET)
	public ModelAndView recepcaoUtente(PesquisarPacienteForm pesquisarPacienteForm) {
		
		ModelAndView model = new ModelAndView("/recepcao/main_recepcao_utente");
		model.addObject("tipoParametro",ParametrosDePesquisaPaciente.values());

		return model;
	}
}
