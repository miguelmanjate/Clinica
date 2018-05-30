package mz.ciuem.uclinica.auth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import groovyjarjarcommonscli.ParseException;
import mz.ciuem.uclinica.auth.Authorization;
import mz.ciuem.uclinica.auth.User;
import mz.ciuem.uclinica.auth.service.RoleService;
import mz.ciuem.uclinica.auth.service.UserService;

@Controller
@RequestMapping("utilizadores")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;
	//@PreAuthorize("hasRole('ROLE_REGISTAR_UTILIZADOR')")
	@RequestMapping(method = RequestMethod.POST, value = { "/add", "/add/" })
	public ModelAndView registarUtilizador(@Valid User user, BindingResult bindresult,
			RedirectAttributes redirectAttributes) {

		if (bindresult.hasErrors()) {
			System.out.println(bindresult.toString());

		} else {
			userService.saveOrUpdate(user);
			redirectAttributes.addFlashAttribute("message_save_user", "true");
		}

		ModelAndView modelAndView = new ModelAndView("redirect:/utilizadores/list");

		return modelAndView;

	}

	//@PreAuthorize("hasRole('ROLE_REGISTAR_UTILIZADOR')")


	@RequestMapping(method = RequestMethod.GET, value = { "/", "" })
	public ModelAndView addUtilizador(User user) {

		ModelAndView modelAndView = new ModelAndView("/autenticacao/add-user");
		modelAndView.addObject("roles", roleService.getAll());
		return modelAndView;

	}
	
	
	//@PreAuthorize("hasRole('ROLE_REGISTAR_UTILIZADOR')")

	@GetMapping(value = { "/{id}/update", "/{id}/update/" })
	public ModelAndView updateUtilizador(@PathVariable Long id) throws ParseException {

		User user = userService.find(id);

		ModelAndView model = new ModelAndView("/autenticacao/update-user", "user", user);
		model.addObject("roles", roleService.getAll());
		model.addObject("userroles", user.getRoles());

		return model;
	}

	//@PreAuthorize("hasRole('ROLE_REGISTAR_UTILIZADOR')")
	@PostMapping(value = "/update")

	public ModelAndView updateUtilizador(@Valid User user, BindingResult bindResults,
			RedirectAttributes redirectAttributes) {

		if (bindResults.hasErrors()) {

		}

		userService.saveOrUpdate(user);
		redirectAttributes.addFlashAttribute("message_update_user", "true");
		ModelAndView model = new ModelAndView("redirect:/utilizadores/list");
		
		return model;
	}
	
	//@PreAuthorize("hasRole('ROLE_LISTAR_UTILIZADOR')")
	
	@GetMapping(value = {"/list","/lista","/list/"})
	public ModelAndView list() {
		
		ModelAndView modelAndView = new ModelAndView("/autenticacao/list-users");
		modelAndView.addObject("users", userService.getAll());
		return modelAndView;
	}

}
