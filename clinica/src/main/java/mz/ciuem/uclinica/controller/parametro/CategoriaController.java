package mz.ciuem.uclinica.controller.parametro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ciuem.uclinica.entity.parametro.Categoria;
import mz.ciuem.uclinica.service.parametro.CategoriaService;

@Controller
@RequestMapping("/parametro")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	@RequestMapping(value = "/categoria", method = RequestMethod.GET)
	public ModelAndView categorias(Categoria categoria){
		
		ModelAndView model = new ModelAndView("/parametros/categoria");

		return model;
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "categoria")
	public ModelAndView gravarCategoria(Categoria categoria, RedirectAttributes redirectAttributes){
		
		categoriaService.saveOrUpdate(categoria);
		
		ModelAndView model = new ModelAndView("redirect:categoria");
		redirectAttributes.addFlashAttribute("messageVisible", "true");
		return model;
	}
}
