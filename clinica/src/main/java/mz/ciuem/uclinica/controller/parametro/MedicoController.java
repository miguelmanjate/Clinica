package mz.ciuem.uclinica.controller.parametro;

import java.text.ParseException;
import java.util.ArrayList;
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

import mz.ciuem.uclinica.entity.consulta.Consulta;
import mz.ciuem.uclinica.entity.consulta.EstadoDaConsulta;
import mz.ciuem.uclinica.entity.consulta.Medico;
import mz.ciuem.uclinica.entity.paciente.EstadoCivil;
import mz.ciuem.uclinica.entity.paciente.Genero;
import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.service.consulta.ConsultaService;
import mz.ciuem.uclinica.service.consulta.MedicoService;
import mz.ciuem.uclinica.service.parametro.EspecialidadeService;
import mz.ciuem.uclinica.service.parametro.SectorService;

@Controller
@RequestMapping("/parametro/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoService medicoService;
	
	@Autowired
	private EspecialidadeService especialidadeService;
	
	@Autowired
	private SectorService sectorService;
	
	@Autowired
	private ConsultaService consultaService;

	private List<Medico> medicos;
	
	@GetMapping(value = {"","/"})
	
	public ModelAndView addMedico(Medico medico){
		
		ModelAndView modelAndView = new ModelAndView("/parametros/medico/add-medico");
		inicialiarFormulario(modelAndView);

		
		return modelAndView;
	}

	private void inicialiarFormulario(ModelAndView modelAndView) {
		modelAndView.addObject("especialidades", especialidadeService.getAll());
		modelAndView.addObject("sectores", sectorService.getAll());
		modelAndView.addObject("genero", Genero.values());
		modelAndView.addObject("estadoCivil", EstadoCivil.values());
	}
	
	@PostMapping(value = {"/add","/add/"})
	public ModelAndView gravarMedico(@Valid Medico medico, BindingResult bindingResult, RedirectAttributes redirectAttributes){
		
		if (bindingResult.hasErrors()) {
			
			return addMedico(medico);
			
		}
		medicoService.saveOrUpdate(medico);
		ModelAndView model = new ModelAndView("redirect:/parametro/medicos/"+medico.getId());
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = { "/list", "/list/" })
	public ModelAndView listar() {

		medicos = medicoService.getAll();
		
	    return exibirPacientes();
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/list/consulta", "/list/consulta/" })
	public ModelAndView listarMedicoConsultas(){
	
		medicos = medicoService.getAll();
		
		List<Medico> selecionarMedicos = new ArrayList<>();
		
		for (Medico medico : medicos){
			if(medico.getConsultas().size() != 0){
				selecionarMedicos.add(medico);
			}
				
		}
		
		ModelAndView model = new ModelAndView("/parametros/medico/list-medicos-consultas");
		model.addObject("medicos", selecionarMedicos);
		
		return model;
	}
	
	private ModelAndView exibirPacientes() {

		ModelAndView modelAndView = new ModelAndView("/parametros/medico/list-medico");
		modelAndView.addObject("medicos", medicos);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = { "/{id}", "/{id}/" })
	public ModelAndView detalhes(@PathVariable Long id) {

		Medico medico = medicoService.find(id);
		ModelAndView model = new ModelAndView("/parametros/medico/detalhes-medico", "medico", medico);

		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/update")
	public ModelAndView editar(@PathVariable Long id) throws ParseException {

		Medico medico = medicoService.find(id);

		
		ModelAndView model = new ModelAndView("/parametros/medico/update-medico", "medico", medico);
		inicialiarFormulario(model);

		return model;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/update")
	public ModelAndView editar(@Valid Medico medico, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
 
		if (bindingResult.hasErrors()) {

			addMedico(new Medico());

		}
		
		medicoService.update(medico);
		ModelAndView model = new ModelAndView("redirect:/parametro/medicos/"+medico.getId());
		redirectAttributes.addFlashAttribute("messageVisible", "true");

		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = {"medico/{id}/consultas", "medico/{id}/consultas/"})
	public ModelAndView verConsultasMedico(@PathVariable Long id){
		
		Medico medico = medicoService.find(id);
		
		ModelAndView model = new ModelAndView("/parametros/medico/medico-consultas-agend", "medico", medico);
		model.addObject("consultas", medico.getConsultas());
		
		return model;
	}
	@RequestMapping(method = RequestMethod.GET, value = {"consulta/{id}/servicos"})
	public ModelAndView verConsultaServicos(@PathVariable Long id){
		
		Consulta consulta = consultaService.find(id);
		Paciente paciente = consulta.getPaciente();
		Medico medico = consulta.getMedico();
		
		ModelAndView model = new ModelAndView("/parametros/medico/medico-consulta-servicos");
		model.addObject("consulta", consulta);
		model.addObject("paciente", paciente);
		model.addObject("medico", medico);
		
		return model;
		
	}
	@RequestMapping(method = RequestMethod.GET, value = {"consulta/{id}/concluida"})
	public ModelAndView concluirAtendimentoConsulta(@PathVariable Long id){
		
		Consulta consulta = consultaService.find(id);
		consulta.setEstado(EstadoDaConsulta.ATENDIDA);
		
		consultaService.update(consulta);
		Medico medico = consulta.getMedico();
		
		ModelAndView model = new ModelAndView("redirect:/parametro/medicos/medico/"+medico.getId()+"/consultas/");
		
		return model;
	}
	
}
