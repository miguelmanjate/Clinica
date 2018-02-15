package mz.ciuem.uclinica.controller.paciente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.entity.parametro.Servico;
import mz.ciuem.uclinica.paciente.service.PacienteService;
import mz.ciuem.uclinica.service.parametro.ServicoService;

@Controller
@RequestMapping("/paciente/contabilista")
public class PacienteCtrlContabilista {

	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private ServicoService servicoService;

	@GetMapping(value = "/{id}/ver")
	public ModelAndView servicosSolicitados(@PathVariable Long id) {

		Paciente paciente = pacienteService.find(id);
		List<Servico> servicos = servicoService.getAll();

		ModelAndView model = new ModelAndView("/paciente/paciente-servico-solicitado");
		model.addObject("paciente", paciente);
		model.addObject("servicos", servicos);
				
		return model;
	}

}
