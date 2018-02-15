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

import mz.ciuem.uclinica.entity.parametro.Sector;
import mz.ciuem.uclinica.entity.parametro.Unidades;
import mz.ciuem.uclinica.service.parametro.SectorService;
import mz.ciuem.uclinica.service.parametro.UnidadesService;

@Controller
@RequestMapping("/parametro/sector")
public class SectorController {

	@Autowired
	private UnidadesService unidadesService;
	@Autowired
	private SectorService sectorService;

	@GetMapping(value = { "", "/", "add" })
	private ModelAndView sector(Sector sector) {

		
		ModelAndView model = new ModelAndView("/parametros/add-sector");
		
		return model;
	}

	@PostMapping(value = { "add" })
	public ModelAndView addSector(@Valid Sector sector, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()){
			return	sector(sector);
		}
		sectorService.saveOrUpdate(sector);
		
		String redirect = "redirect:/parametro/sector/";
		ModelAndView modelAndView = new ModelAndView(redirect);
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = { "/{id}", "/{id}/"})
	public ModelAndView detalhes(@PathVariable Long id) {
		
		Sector sector = sectorService.find(id);
		
		ModelAndView model = new ModelAndView("parametros/detalhes-sector", "sector", sector);
		
		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public ModelAndView listar() {

		List<Sector> sectores = sectorService.getAll();

		ModelAndView modelAndView = new ModelAndView("/parametros/sectores");
		modelAndView.addObject("sectores", sectores);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/update")
	public ModelAndView editar(@PathVariable Long id) {

		Sector sector = sectorService.find(id);
		List<Unidades> unidades = unidadesService.getAll();
		ModelAndView model = new ModelAndView("/parametros/update-sector", "sector", sector);
		model.addObject("unidades", unidades);

		return model;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/update")
	public ModelAndView editar(@Valid Sector sector, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
         System.err.println("Tem error");
			return editar(sector.getId());
		}

		sectorService.saveOrUpdate(sector);
		ModelAndView model = new ModelAndView("redirect:/parametro/sector/list");
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		return model;
	}
	

}
