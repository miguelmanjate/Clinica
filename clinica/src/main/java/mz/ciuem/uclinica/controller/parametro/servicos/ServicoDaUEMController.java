package mz.ciuem.uclinica.controller.parametro.servicos;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ciuem.uclinica.entity.parametro.ServicoTipo;
import mz.ciuem.uclinica.entity.parametro.RubricaDaUnidade;
import mz.ciuem.uclinica.entity.parametro.ServicoDaUnidade;
import mz.ciuem.uclinica.service.parametro.TipoServicoService;

@Controller
@RequestMapping("/parametro/servico-uem")
public class ServicoDaUEMController {
	@Autowired
	private TipoServicoService tiposervicoService;

	@RequestMapping(value = { "/add", "/add/" }, method = RequestMethod.GET)
	public ModelAndView TipoServicos(ServicoDaUnidade tiposervico) {
		List<ServicoDaUnidade> tipoServicos = tiposervicoService.getAll();

		ModelAndView model = new ModelAndView("/parametros/servico/servico-uem");
		model.addObject("tipoServicos", tipoServicos);
		model.addObject("rubricaDaUnidade", RubricaDaUnidade.values());
		model.addObject("servicoTipo", ServicoTipo.values());
		return model;
	}

	@PostMapping(value = { "/add", "/add/" })
	public ModelAndView registarTipoServico(@Valid ServicoDaUnidade servicoDaUnidade, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return TipoServicos(servicoDaUnidade);

		}
		tiposervicoService.create(servicoDaUnidade);

		ModelAndView model = new ModelAndView("redirect:/parametro/servico-uem/add");
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		return model;
	}

	@GetMapping(value = { "/servicos" })

	public ModelAndView listar() {

		List<ServicoDaUnidade> tipoServicos = tiposervicoService.getAll();
		ModelAndView model = new ModelAndView("/parametros/servico/servico-uem-list");
		model.addObject("servicosDaUnidade", tipoServicos);

		return model;
	}

	@GetMapping(value = "/{id}/update")
	public ModelAndView editar(@PathVariable Long id) {
		return listar();
	}
	
	
	@GetMapping(value = "/{id}")
	public ModelAndView detalhes(@PathVariable Long id) {
		return listar();
	}
}
