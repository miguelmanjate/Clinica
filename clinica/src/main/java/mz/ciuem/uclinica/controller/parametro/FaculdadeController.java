package mz.ciuem.uclinica.controller.parametro;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ciuem.uclinica.entity.estudante.Faculdade;
import mz.ciuem.uclinica.service.parametro.FaculdadeService;

@Controller
@RequestMapping("/faculdade")
public class FaculdadeController {

	@Autowired
	private FaculdadeService faculdadeService;

	@GetMapping(value = { "", "/", "add" })
	private ModelAndView faculdade(Faculdade faculdade) {

		ModelAndView model = new ModelAndView("/estudante/uem/add-faculdade");

		return model;
	}

	@PostMapping(value = { "add" })
	public ModelAndView addFaculdade(@Valid Faculdade faculdade, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return faculdade(faculdade);
		}
		faculdadeService.saveOrUpdate(faculdade);
		
		System.err.println("successul  "+faculdade.getDescricao());
		return null;
	}
}
