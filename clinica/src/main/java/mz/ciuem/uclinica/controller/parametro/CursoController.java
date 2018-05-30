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

import mz.ciuem.uclinica.entity.estudante.Curso;
import mz.ciuem.uclinica.entity.estudante.Faculdade;
import mz.ciuem.uclinica.service.parametro.CursoService;
import mz.ciuem.uclinica.service.parametro.FaculdadeService;

@Controller
@RequestMapping("/curso")
public class CursoController {

	@Autowired
	private FaculdadeService faculdadeService;
	
	@Autowired
	private CursoService cursoService;

	@GetMapping(value = { "", "/", "add" })
	private ModelAndView curso(Curso curso) {

		ModelAndView model = new ModelAndView("/estudante/uem/add-curso");
		model.addObject("faculdades", faculdadeService.getAll());
		
		return model;
	}

	@PostMapping(value = { "add" })
	public ModelAndView addCurso(@Valid Curso curso, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return curso(curso);
		}
		cursoService.saveOrUpdate(curso);

		System.err.println("successul  " + curso.getDescricao());
		return null;
	}
}
