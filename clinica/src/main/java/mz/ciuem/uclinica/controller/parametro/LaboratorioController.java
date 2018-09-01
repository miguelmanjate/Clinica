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
import mz.ciuem.uclinica.entity.exame.Exame;
import mz.ciuem.uclinica.entity.exame.Laboratorio;
import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.entity.parametro.Especialidade;
import mz.ciuem.uclinica.entity.parametro.ServicoTipo;
import mz.ciuem.uclinica.service.exame.ExameService;
import mz.ciuem.uclinica.service.exame.LaboratorioService;
import mz.ciuem.uclinica.service.parametro.EspecialidadeService;

@Controller
@RequestMapping("/parametro/laboratorio")
public class LaboratorioController {
	
	@Autowired
	private LaboratorioService laboratorioService;
	
	@Autowired
	private EspecialidadeService especialidadeService;
	
	@Autowired
	private ExameService exameService;
	
	private List<Laboratorio> laboratorios ;
	
	@GetMapping(value = {"","/"})
	
	public ModelAndView addLaboratorio(Laboratorio laboratorio){
		
		ModelAndView modelAndView = new ModelAndView("/parametros/laboratorio/add-laboratorio");
		inicialiarFormulario(modelAndView);

		
		return modelAndView;
	}
	
	private void inicialiarFormulario(ModelAndView modelAndView) {
		List<Especialidade> especialidades = especialidadeService.getAll();
		List<Especialidade> especialidadesSelecionadas = new ArrayList<>();
		
				for(Especialidade especialidade : especialidades){
					
					if(especialidade.getServicoTipo().equals(ServicoTipo.EXAME_MEDICO)){
						especialidadesSelecionadas.add(especialidade);
					}
					
				}
		
		modelAndView.addObject("especialidades", especialidadesSelecionadas);
		modelAndView.addObject("laboratorios", laboratorioService.getAll());
	}
	
	@PostMapping(value = {"/add","/add/"})
	public ModelAndView gravarLaboratorio(@Valid Laboratorio laboratorio, BindingResult bindingResult, RedirectAttributes redirectAttributes){
		
		if (bindingResult.hasErrors()) {
			
			return addLaboratorio(laboratorio);
			
		}
		laboratorioService.saveOrUpdate(laboratorio);

		redirectAttributes.addFlashAttribute("messageVisible", "true");
		return addLaboratorio(laboratorio);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = { "/list/exame", "/list/exame/" })
	public ModelAndView listarLaboratorioExames(){
	
		laboratorios = laboratorioService.getAll();
		
		List<Laboratorio> selecionarLaboratorios = new ArrayList<>();
		
		for (Laboratorio laboratorio : laboratorios){
			if(laboratorio.getExames().size() != 0){
				selecionarLaboratorios.add(laboratorio);
			}
				
		}
		
		ModelAndView model = new ModelAndView("/parametros/laboratorio/list-laboratorios-exames");
		model.addObject("laboratorios", selecionarLaboratorios);
		
		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = { "/{id}", "/{id}/" })
	public ModelAndView detalhes(@PathVariable Long id) {

		Laboratorio laboratorio = laboratorioService.find(id);
		ModelAndView model = new ModelAndView("/parametros/laboratorio/detalhes-laboratorio", "laboratorio", laboratorio);

		return model;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/update")
	public ModelAndView editar(@PathVariable Long id) throws ParseException {

        Laboratorio laboratorio =laboratorioService.find(id);
		
		ModelAndView model = new ModelAndView("/parametros/laboratorio/update-laboratorio", "laboratorio", laboratorio);
		inicialiarFormulario(model);

		return model;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/update")
	public ModelAndView editar(@Valid Laboratorio laboratorio, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
 
		if (bindingResult.hasErrors()) {

			addLaboratorio(new Laboratorio());

		}
		
	laboratorioService.update(laboratorio);
	
		ModelAndView model = new ModelAndView("redirect:/parametro/laboratorio/"+laboratorio.getId());
		redirectAttributes.addFlashAttribute("messageVisible", "true");

		return model;
	}
	@RequestMapping(method = RequestMethod.GET, value = {"laboratorio/{id}/exames", "laboratorio/{id}/exames/"})
	public ModelAndView verExamesLaboratorio(@PathVariable Long id){
		
		Laboratorio laboratorio = laboratorioService.find(id);
		
		ModelAndView model = new ModelAndView("/parametros/laboratorio/laboratorio-exames-agend", "laboratorio", laboratorio);
		model.addObject("exames", laboratorio.getExames());
		
		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = {"exame/{id}/servicos"})
	public ModelAndView verExameServicos(@PathVariable Long id){
		
		Exame exame = exameService.find(id);
		Paciente paciente = exame.getPaciente();
		Laboratorio laboratorio = exame.getLaboratorio();
		
		ModelAndView model = new ModelAndView("/parametros/laboratorio/laboratorio-exame-servicos");
		model.addObject("exame", exame);
		model.addObject("paciente", paciente);
		model.addObject("laboratorio", laboratorio);
		
		return model;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = {"exame/{id}/concluida"})
	public ModelAndView concluirAtendimentoExame(@PathVariable Long id){
		
		Exame exame = exameService.find(id);
		exame.setEstado(EstadoDaConsulta.ATENDIDA);
		
		exameService.update(exame);
		Laboratorio laboratorio = exame.getLaboratorio();
		
		ModelAndView model = new ModelAndView("redirect:/parametro/laboratorio/laboratorio/"+laboratorio.getId()+"/exames/");
		
		return model;
	}
}
