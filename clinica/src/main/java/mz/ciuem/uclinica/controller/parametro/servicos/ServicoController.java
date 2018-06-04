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

import mz.ciuem.uclinica.entity.consulta.Taxa;
import mz.ciuem.uclinica.entity.parametro.Servico;
import mz.ciuem.uclinica.entity.parametro.ServicoDaUnidade;
import mz.ciuem.uclinica.service.parametro.EspecialidadeService;
import mz.ciuem.uclinica.service.parametro.SectorService;
import mz.ciuem.uclinica.service.parametro.ServicoService;
import mz.ciuem.uclinica.service.parametro.TaxasServie;
import mz.ciuem.uclinica.service.parametro.TipoServicoService;

@Controller
@RequestMapping("/parametro/servico")
public class ServicoController {
	@Autowired
	private ServicoService servicoService;
	@Autowired
	TipoServicoService tipoServicoService;
	
	@Autowired
	private TaxasServie taxasService;
	
	@Autowired
	private SectorService sectorService;
	
	@Autowired
	private EspecialidadeService especialidadeService;
	
	@Autowired
	private TaxasServie taxasServie;
	
	private Servico servico;

	@GetMapping(value = { "", "/", "add" })
	public ModelAndView servicos(Servico servico) {

		ModelAndView model = new ModelAndView("parametros/servico/add-servico");
		List<ServicoDaUnidade> tipoServicos = tipoServicoService.getAll();
		model.addObject("tipos", tipoServicos);
		inicializarForm(model);

		return model;
	}

	private void inicializarForm(ModelAndView model) {
		model.addObject("servicos", servicoService.getAll());
		model.addObject("especialidades",especialidadeService.getAll());
	}

	@PostMapping( value ={ "/add"})
	public ModelAndView gravarServico(@Valid Servico servico, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return servicos(servico);
		}

		servicoService.saveOrUpdate(servico);
		 
		String redirect = "redirect:/parametro/servico/"+servico.getId();
	
		ModelAndView modelAndView = new ModelAndView(redirect);
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = { "/{id}", "/{id}/"})
	public ModelAndView detalhes(@PathVariable Long id) {
		
		Servico servico = servicoService.find(id);
		
		ModelAndView model = new ModelAndView("parametros/servico/detalhes-servico", "servico", servico);
		
		return model;
	}
	

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public ModelAndView listar() {

		List<Servico> servicos = servicoService.getAll();

		ModelAndView modelAndView = new ModelAndView("/parametros/servico/servicos");
		modelAndView.addObject("servicos", servicos);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/update")
	public ModelAndView editar(@PathVariable Long id) {

		Servico servico = servicoService.find(id);
		List<ServicoDaUnidade> tipoServicos = tipoServicoService.getAll();
		ModelAndView model = new ModelAndView("/parametros/servico/update-servico", "servico", servico);
		model.addObject("tipos", tipoServicos);
		inicializarForm(model);

		return model;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/update")
	public ModelAndView editar(@Valid Servico servico, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
         System.err.println(bindingResult.toString());
			return editar(servico.getId());
		}

		servicoService.saveOrUpdate(servico);
		ModelAndView model = new ModelAndView("redirect:/parametro/servico/"+servico.getId());
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		return model;
	}
	
	@GetMapping(value = {"/{id}/taxas"})
	public ModelAndView configurarTaxas(@PathVariable Long id, Taxa taxa) {
		
		servico = servicoService.find(id);
		ModelAndView model = new ModelAndView("/parametros/servico/configuracao-taxas");
		model.addObject(servico);
		model.addObject("tipoCliente", TipoCliente.values());
		listarServicos(model);
		
		
		return model;
	}
	
	@PostMapping(value = {"/taxa/update"})
	public ModelAndView configurarTaxas(@Valid Taxa taxa,BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		
		if (bindingResult.hasErrors()) {
			return configurarTaxas(servico.getId(), taxa);
		}
		List<Taxa> taxas = taxasServie.buscarTaxasPorIdServico(servico.getId());
		taxa.setServico(servico);
			
			if(verificarTxas(taxas, taxa)){
				
				taxasService.saveOrUpdate(taxa);
			}else{
				ModelAndView model = new ModelAndView("redirect:/parametro/servico/"+servico.getId()+"/taxas");
				return model;
			}
	
		//ModelAndView model = new ModelAndView("redirect:/parametro/servico/taxas");
		redirectAttributes.addFlashAttribute("messageVisible", "true");

		
		return configurarTaxas(servico.getId(), new Taxa());
	}
	
	private boolean verificarTxas(List<Taxa> taxas, Taxa taxa){
		
		for(Taxa t : taxas){
			if(t.getTipoCliente().equals(taxa.getTipoCliente())){
				return false;
			}
				
		}
		return true;
	}
	
	@GetMapping(value = {"/taxas"})
	public ModelAndView configurarTaxas() {
		
	
	
		ModelAndView model = new ModelAndView("/parametros/servico/taxas");
		listarServicos(model);
		return model;
	}

	private void listarServicos(ModelAndView model) {
		List<Servico> servicos = servicoService.getServicosComSuasTaxas();
		model.addObject("servicos",servicos);
	}
	
	
	


}
