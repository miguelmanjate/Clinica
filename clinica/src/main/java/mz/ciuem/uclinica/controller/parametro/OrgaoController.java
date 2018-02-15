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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ciuem.uclinica.entity.parametro.Orgao;
import mz.ciuem.uclinica.entity.parametro.Servico;
import mz.ciuem.uclinica.entity.parametro.ServicoDaUnidade;
import mz.ciuem.uclinica.service.parametro.OrgaoService;

@Controller
@RequestMapping("/parametro/orgao")
public class OrgaoController  {
	
	@Autowired
	private OrgaoService orgaoService;
	
	@GetMapping(value={"", "/", "add"})
	private ModelAndView orgao(Orgao orgao){
			
		ModelAndView model = new ModelAndView("/parametros/add-unidade");
		
		return model;
		
	}
	
	@PostMapping(value = { "add" })
	public ModelAndView addOrgao(@Valid Orgao orgao, BindingResult bindingResult,
			RedirectAttributes redirectAttributes){
		
		if(bindingResult.hasErrors()){
			
			return orgao( orgao);
		}
		
		orgaoService.saveOrUpdate(orgao);
		
		String redirect = "redirect:/parametro/orgao/"+orgao.getId();
		
		ModelAndView modelAndView = new ModelAndView(redirect);
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = { "/{id}", "/{id}/"})
	public ModelAndView detalhes(@PathVariable Long id) {
		
		Orgao orgao = orgaoService.find(id);
		
		ModelAndView model = new ModelAndView("parametros/detalhes-orgao", "orgao", orgao);
		
		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public ModelAndView listar() {

		List<Orgao> orgaoes = orgaoService.getAll();

		ModelAndView modelAndView = new ModelAndView("/parametros/orgaos");
		modelAndView.addObject("orgaoes", orgaoes);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/update")
	public ModelAndView editar(@PathVariable Long id) {

		Orgao orgao = orgaoService.find(id);

		ModelAndView model = new ModelAndView("/parametros/update-orgao", "orgao", orgao);
	
		return model;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/update")
	public ModelAndView editar(@Valid Orgao orgao, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
         System.err.println("Tem error");
			return editar(orgao.getId());
		}

		orgaoService.saveOrUpdate(orgao);
		ModelAndView model = new ModelAndView("redirect:/parametro/orgao/list");
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		return model;
	}
	
	

}
