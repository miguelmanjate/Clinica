package mz.ciuem.uclinica.controller.exame;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ciuem.uclinica.entity.consulta.CausaAdmissao;
import mz.ciuem.uclinica.entity.consulta.Consulta;
import mz.ciuem.uclinica.entity.consulta.ConsultaForm;
import mz.ciuem.uclinica.entity.consulta.EstadoDaConsulta;
import mz.ciuem.uclinica.entity.consulta.MedicoJson;
import mz.ciuem.uclinica.entity.consulta.ServicoJason;
import mz.ciuem.uclinica.entity.consulta.TipoConsulta;
import mz.ciuem.uclinica.entity.exame.Exame;
import mz.ciuem.uclinica.entity.exame.ExameForm;
import mz.ciuem.uclinica.entity.exame.LaboratorioJson;
import mz.ciuem.uclinica.entity.paciente.Paciente;
import mz.ciuem.uclinica.entity.parametro.Especialidade;
import mz.ciuem.uclinica.entity.parametro.Servico;
import mz.ciuem.uclinica.entity.parametro.ServicoTipo;
import mz.ciuem.uclinica.paciente.service.PacienteService;
import mz.ciuem.uclinica.recepcao.modelview.ParametrosDePesquisaPaciente;
import mz.ciuem.uclinica.recepcao.modelview.PesquisarPacienteForm;
import mz.ciuem.uclinica.service.exame.ExameService;
import mz.ciuem.uclinica.service.exame.ItemExameService;
import mz.ciuem.uclinica.service.exame.LaboratorioService;
import mz.ciuem.uclinica.service.parametro.EspecialidadeService;
import mz.ciuem.uclinica.service.parametro.FacturaService;
import mz.ciuem.uclinica.service.parametro.ServicoService;

@Controller
@RequestMapping(value = { "/exame" })
public class ExameController {
	
	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private ExameService exameService;
	
	@Autowired
	private ServicoService servicoService;
	
	@Autowired
	private FacturaService facturaService;
	
	@Autowired
	private LaboratorioService laboratorioService;
	
	@Autowired
	private EspecialidadeService especialidadeService;
	
	@Autowired
	private ItemExameService itemExameServicoService;
	
	private Paciente paciente;
	private Exame exame;
	
	private List<Paciente> pacientes;
	
	@GetMapping("/agendar/{id}")
	public ModelAndView agendarExame(@ModelAttribute ExameForm exameForm, @PathVariable Long id){
		
		paciente = pacienteService.find(id);
		ModelAndView model = new ModelAndView("/exames/agendar-exame");
		model.addObject("paciente", paciente);
		
		preencherFormulario(model);
		model.addObject("exames", exameService.getExamesDoPaciente(paciente));
		
		return model;
	}
	
	private List<Servico> selecionarServicos(List<Servico> servicos) {

		List<Servico> servicosSelecionados = new ArrayList<>();
		for (Servico s : servicos) {
			servicosSelecionados.add(s);
		}
		return servicosSelecionados;
	}
	
	private void preencherFormulario(ModelAndView model){
		
		List<Especialidade> especialidades = especialidadeService.getAll();
		List<Especialidade> especialidadesSelecionadas = new ArrayList<>();
		
				for(Especialidade especialidade : especialidades){
					
					if(especialidade.getServicoTipo().equals(ServicoTipo.EXAME_MEDICO)){
						especialidadesSelecionadas.add(especialidade);
					}
					
				}
		
		model.addObject("tipoExame", TipoConsulta.values());
		model.addObject("especialidade", especialidadesSelecionadas);
		model.addObject("estados", EstadoDaConsulta.values());
		
	}
	
	@PostMapping("/agendar")
	public ModelAndView registarExame(@Valid ExameForm exameForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes){
		
		if (bindingResult.hasErrors()) {

		}
		
		double precoTotal = 0;
		Exame exame = exameForm.getExame();
		
		exame.setPaciente(paciente);
		
		List<Servico> servicosSelecionados = new ArrayList<>();
		
		for(Servico servico : exameForm.getServicos()){
			
			servicosSelecionados.add(servicoService.find(servico.getId()));
		}
		exame.setEstado(EstadoDaConsulta.AGENDADA);
		
		exameService.create(exame);
						
		try {
		    
			itemExameServicoService.criarItemExameListaServicos(exame, servicosSelecionados);
		} catch (Exception e) {
			
			redirectAttributes.addFlashAttribute("msgSemTaxas", "true");
			ModelAndView model = new ModelAndView("redirect:/exame/agendar/" + paciente.getId());
			
			return model;
		}
		ModelAndView model = new ModelAndView("redirect:/exame/" + paciente.getId() + "/list");

		return model;
	}
	
	@GetMapping("{id}/list")
	public ModelAndView listarExamesDoPaciente(@PathVariable Long id){
		
		Paciente paciente = pacienteService.find(id);
		List<Exame> exames = exameService.getExamesDoPaciente(paciente);
		
		ModelAndView model = new ModelAndView("/exames/paciente-exames", "exames", exames);
		model.addObject("paciente", paciente);

		return model;
	}
	
	@GetMapping("{id}/select")
	public ModelAndView selectExameDoPacinte(@PathVariable Long id) {
		
		Exame exame = exameService.find(id);
		
		Paciente paciente = exame.getPaciente();
		ModelAndView model = new ModelAndView("/exames/paciente-um-exame", "exame", exame);
		model.addObject("paciente", paciente);
		
		return model;
	}
	
	@GetMapping(value = { "/list", "/list/" })
	public ModelAndView listarExames(){
		
		List<Exame> exames = exameService.getAll();
		ModelAndView model = new ModelAndView("/exames/list-exames");
		
		model.addObject("exames", exames);
		
		return model;
	}
	
	@GetMapping("{id}/update")
	public ModelAndView editar(@ModelAttribute ExameForm exameForm, @PathVariable Long id){
		
		Exame exame = exameService.find(id);
		exameForm.setExame(exame);
		paciente = exameForm.getExame().getPaciente();
		exameForm.setItems(exame.getItemExameServicos());
		
		ModelAndView model = new ModelAndView("/exames/update-exame", "exameForm", exameForm);
		model.addObject("paciente", exameForm.getExame().getPaciente());
		preencherFormulario(model);
		
		return model;
		
	}
	
	@PostMapping("/update")
	public ModelAndView aditar(@Valid ExameForm exameForm, BindingResult bindingResult,
			RedirectAttributes redirectAtributes) {
		
		if (bindingResult.hasErrors()) {

		}
		double precoTotal = 0;
		Exame exame = exameForm.getExame();
		exame.setPaciente(paciente);
		
		List<Servico> servicosSelecionados = new ArrayList<>();
		
		for(Servico servico : exameForm.getServicos()){
			
			servicosSelecionados.add(servicoService.find(servico.getId()));
		}
		
		exameService.saveOrUpdate(exame);
		
		try {
			
			itemExameServicoService.actualizarItemExameListaServicos(exame, servicosSelecionados);
			
		} catch (Exception e) {
			
	  		redirectAtributes.addFlashAttribute("msgSemTaxas", "true");
			ModelAndView model = new ModelAndView("redirect:/exame/agendar/" + paciente.getId());

			return model;
		}
		ModelAndView model = new ModelAndView("redirect:/exame/" + exame.getId() + "/detalhes");

		return model;
		
	}
	
	@GetMapping("{id}/cancelar")
	public ModelAndView cancelarExame(Exame exame, @PathVariable Long id) {
	
		exame = exameService.find(id);
		exame.setEstado(EstadoDaConsulta.CANCELA);
		
		Paciente paciente = pacienteService.find(exame.getPaciente().getId());
		
		List<Exame> exames = exameService.getExamesDoPaciente(paciente);
		
		ModelAndView model = new ModelAndView("/exames/paciente-exames" , "exames", exames);
		model.addObject("paciente", paciente);

		return model;
		
	}
	
	@GetMapping("{id}/cancelar/select")
	public ModelAndView cancelarExameSelecionada(Exame exame, @PathVariable Long id) {
		System.err.println(exame.getEstado());
		
		exame = exameService.find(id);
		exame.setEstado(EstadoDaConsulta.CANCELA);
		
        Paciente paciente = pacienteService.find(exame.getPaciente().getId());

		ModelAndView model = new ModelAndView("/exames/paciente-um-exame" , "exame", exame);
		model.addObject("paciente", paciente);

		return model;
	}
	
	@GetMapping("{id}/detalhes")
	public ModelAndView detalhesExame(@PathVariable Long id) {
		
		Exame exame = exameService.find(id);
		Paciente paciente = exame.getPaciente();
		
		ModelAndView model = new ModelAndView("/exames/detalhes-de-exame", "exame", exame);
		model.addObject("paciente", paciente);
		
		return model;
	}
	
	@GetMapping(value = { "/listAgendadas", "/listAgendadas/" })
	public ModelAndView listarExamesAgendadas() {
		
		List<Exame> exames = exameService.getAll();
		List<Exame> examesSelecionados = new ArrayList<>();
		
		for (Exame exame : exames){
			
			if(exame.getEstado().equals(EstadoDaConsulta.AGENDADA)){
				examesSelecionados.add(exame);
			}
		}
		ModelAndView model = new ModelAndView("/exames/list-exames-agendadas", "exames", examesSelecionados);

		return model;
	}
	
	private void pesquisarPacientes() {

		List<Paciente> pacietesSelecionados = pacienteService.getAll();
		pacientes = new ArrayList<>();
		for (Paciente p : pacietesSelecionados) {
			if (p.getTipoDePaciente() != null) {
				pacientes.add(p);
			}
		}

	}
	
	@GetMapping("/procurar")
	public ModelAndView pesquisarPacienteExame(PesquisarPacienteForm pesquisarPacienteForm) {
		pesquisarPacientes();
		ModelAndView model = new ModelAndView("/exames/procurar-paciente-exame");
		model.addObject("pacientes", pacientes);
		model.addObject("tipoParametro", ParametrosDePesquisaPaciente.values());
		return model;
	}
	
	@PostMapping("/procurar")
	public ModelAndView procurarPaciente(PesquisarPacienteForm pesquisarPacienteForm) {
        
		List<Paciente> pacientes = new ArrayList<>();
		List<Paciente> pacietesSelecionados = pacienteService.procurarPacientePor(pesquisarPacienteForm.getTipo(),
				pesquisarPacienteForm.getParametroDePesquisa());
        for (Paciente p : pacietesSelecionados){
        	if (p.getTipoDePaciente() != null) {
				pacientes.add(p);
			}
        }
		ModelAndView model = new ModelAndView("/exames/procurar-paciente-exame");
	
		model.addObject("pacientes", pacientes);
		model.addObject("tipoParametro", ParametrosDePesquisaPaciente.values());
		return model;

	}
	
		
	@RequestMapping(value = { "/agendar/{id}/laboratorios", "/{id}/update/laboratorios" })
	public @ResponseBody List<LaboratorioJson> getLaboratoriosPorEspecialidade(@RequestParam String especialidade){ 
		
		List<LaboratorioJson> laboratorios = laboratorioService.getTodosLaboratoriosDaEspecialidade(especialidade);
		
		return laboratorios;
	}
	
	@RequestMapping(value = { "/agendar/{id}/servicos", "/{id}/update/servicos"} )
	public @ResponseBody List<ServicoJason> getServicosPorEspecialidade(@RequestParam String especialidade){
		
		List<ServicoJason> servicos = servicoService.getTodosServicosDaEspecialidadeToJson(especialidade);

		return servicos;
	}

}
