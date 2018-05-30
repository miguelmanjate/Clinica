package mz.ciuem.uclinica.controller.parametro;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ciuem.uclinica.entity.estudante.Ano;
import mz.ciuem.uclinica.entity.estudante.CursoJson;
import mz.ciuem.uclinica.entity.estudante.Estudante;
import mz.ciuem.uclinica.entity.estudante.Semestre;
import mz.ciuem.uclinica.entity.paciente.EstadoCivil;
import mz.ciuem.uclinica.entity.paciente.Genero;
import mz.ciuem.uclinica.entity.paciente.Raca;
import mz.ciuem.uclinica.entity.paciente.TipoDePaciente;
import mz.ciuem.uclinica.service.parametro.CursoService;
import mz.ciuem.uclinica.service.parametro.EstudanteService;
import mz.ciuem.uclinica.service.parametro.FaculdadeService;

@Controller
@RequestMapping("/estudante/uem")
public class EstudanteController {
	
	@Autowired
	private EstudanteService estudanteService;
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private FaculdadeService faculdadeService;
	
	@GetMapping(value ={"", "/", "add"})
	private ModelAndView estudante(Estudante estudante){
		
		ModelAndView model = new ModelAndView("estudante/add-estudante");
		
		return preencher(model);
	}
	
	private ModelAndView preencher(ModelAndView model){
		
		model.addObject("estadoCivil", EstadoCivil.values());
		model.addObject("genero", Genero.values());
		model.addObject("anos", Ano.values());
		model.addObject("semestres", Semestre.values());
		model.addObject("faculdades", faculdadeService.getAll());
		
		return model;
	}
	
	@PostMapping(value ={"/add"})
	public ModelAndView addestudantes(@Valid Estudante estudante , BindingResult bindingResult,
			RedirectAttributes redirectAttributes){
		
		if(bindingResult.hasErrors()){
			
			return estudante(estudante);
		}
		
		estudanteService.saveOrUpdate(estudante);
		
		String redirect = "redirect:/estudante/uem/"+estudante.getId();
		
		ModelAndView model = new ModelAndView(redirect);
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		
		return model;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = { "/{id}", "/{id}/"})
	public ModelAndView detalhes(@PathVariable Long id) {
		
		Estudante estudante = estudanteService.find(id);
		
		ModelAndView model = new ModelAndView("/estudante/detalhes-estudante", "estudante", estudante);
		
		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public ModelAndView listar() {

		List<Estudante> estudantes = estudanteService.getAll();

		ModelAndView modelAndView = new ModelAndView("/estudante/list-estudante");
		modelAndView.addObject("estudantes", estudantes);
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/update")
	public ModelAndView editar(@PathVariable Long id) {

		Estudante estudante = estudanteService.find(id);
		
		ModelAndView model = new ModelAndView("/estudante/update-estudante", "estudante", estudante);


		return preencher(model);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/update")
	public ModelAndView editar(@Valid Estudante estudante, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
         System.err.println("Tem error");
			return editar(estudante.getId());
		}

		estudanteService.saveOrUpdate(estudante);
		Estudante e = estudanteService.find(estudante.getId());
		
		String redirect = "redirect:/estudante/uem/"+e.getId();
		
		ModelAndView model = new ModelAndView(redirect);
		
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		
		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/configurar")
	public ModelAndView configurarEstudante(@PathVariable Long id) {

		Estudante estudante = estudanteService.find(id);
		
		ModelAndView model = new ModelAndView("/estudante/estudante-paciente", "estudante", estudante);
		model.addObject("raca", Raca.values());
		
		return model;
	}
	
	@PostMapping(value = { "add_estudante_paciente" })
	public ModelAndView configurarEstudante(@Valid Estudante estudante, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		estudante.setTipoDePaciente(TipoDePaciente.PACIENTE_ESTUDANTE);
		estudante.setNid(estudante.getId());
		if (bindingResult.hasErrors()) {
			
			return configurarEstudante(estudante.getId());

		}
		
		estudanteService.saveOrUpdate(estudante);
		
		String redirect = "redirect:/estudante/uem/list";
		
		ModelAndView modelandview = new ModelAndView(redirect);
		redirectAttributes.addFlashAttribute("messageVisible", "true");

		return modelandview;
		
	}
	
	@RequestMapping(value = {"/cursos", "/{id}/update/cursos"})
	
	public @ResponseBody List<CursoJson> getCursoPorFaculdade(@RequestParam String faculdade){
		List<CursoJson> cursos = cursoService.getTodosCursosDaFaculdade(faculdade);
		
		return cursos;
	}

}
