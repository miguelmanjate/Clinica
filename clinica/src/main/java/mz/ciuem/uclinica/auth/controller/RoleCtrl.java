package mz.ciuem.uclinica.auth.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ciuem.uclinica.auth.Role;
import mz.ciuem.uclinica.auth.service.PermissionService;
import mz.ciuem.uclinica.auth.service.RoleService;

@Controller
@RequestMapping("roles")
public class RoleCtrl {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService PermissionService;

	
	
	@GetMapping(value  = {"/", ""})
	public ModelAndView role(Role role) {
		
		
		ModelAndView model = new ModelAndView("/autenticacao/add-role");
		model.addObject("permissions", PermissionService.getAll());
		return model;
	}
	
	@PostMapping(value = {"/add","/add/"})
	public ModelAndView add(@Valid Role role, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			
			System.err.println(role.getRolename()+ " "+role.getPermissions());
			redirectAttributes.addFlashAttribute("message_role_save_error","true");
			return role(new Role());
			
		}
		roleService.saveOrUpdate(role);
		ModelAndView model = new ModelAndView("redirect:/roles/list");
		redirectAttributes.addFlashAttribute("message_role_save", "true");
		return model;
	}
	
	@GetMapping(value = {"/list", "/list/"})
	public ModelAndView list() {
		
		ModelAndView model = new ModelAndView("/autenticacao/list-roles");
		model.addObject("roles", roleService.getAll());
		return model;
	}

	@GetMapping(value = "/{id}/update")
	public ModelAndView actualizar(@PathVariable Long id) {
		
		Role role = roleService.find(id);
		ModelAndView modelAndView = new ModelAndView("/autenticacao/update-role", "role", role);
		modelAndView.addObject("permissions", PermissionService.getAll());
		return modelAndView;
	}
	
	@PostMapping(value = {"/update", "/update/"})
	public ModelAndView actualizar(@Valid Role role, BindingResult bindingResult, RedirectAttributes attribute) {
		
		if (bindingResult.hasErrors()) {
			
			
		}
		
		roleService.update(role);
		
		ModelAndView modelAndView = new ModelAndView("redirect:/roles/list");
		attribute.addFlashAttribute("message_update_role","true");
		return modelAndView;
	}
	
}
