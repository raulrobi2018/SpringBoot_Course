package com.rr.springbootweb.web.app.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rr.springbootweb.web.app.models.User;

//Basicamente un controlador se encarga de manejar las peticiones del usuario,
//mostrar la página con los datos por ejemplo
@Controller
@RequestMapping("/app")
public class IndexController {

	//ACCEDIENDO A VALORES EN ARCHIVO application.properties mediante INYECCIÓN DE DEPENDENCIA con strings
	@Value("${text.indexController.index.title}")
	private String indexTitle;

	@Value("${text.indexController.profile.title}")
	private String profileTitle;
	
	@Value("${text.indexController.userList.title}")
	private String userListTitle;
	
	
	// Esta es otra forma de utilizar
//	@RequestMapping(value="/index", method = RequestMethod.GET)
//Esta forma también es válida
	// @GetMapping("/index")
	// Si quiero que mapee a varios path lo utilizo así
	// @GetMapping({ "/index", "/", "/home" })
	// @GetMapping(value = "/index")
//	public String index(ModelMap model) {
//		model.addAttribute("titulo", "Hello Spring with ModelMap!");
//		return "index";
//	}
//	
	// SEGUNDA FORMA - Esta es la más utilizada
	@GetMapping({ "/index", "/", "", "/home" })
	public String index(Model model) {
		model.addAttribute("titulo", indexTitle);
		return "index";
	}

	// TERCERA FORMA
	// @GetMapping({ "/index", "/", "/home" })
//	public String index(Map<String, Object> map) {
//		map.put("titulo", "Hello Spring with Map!");
//		return "index";
//	}

	// CUARTA FORMA
	// @GetMapping({ "/index", "/", "/home" })
//	public ModelAndView index(ModelAndView mv) {
//		mv.addObject("titulo", "Hello Spring with ModelAndView!");
//		mv.setViewName("index");		
//		return mv;
//	}

	@RequestMapping("/profile")
	public String perfil(Model model) {
		User user = new User();
		user.setName("Raul");
		user.setLastname("Rodriguez");
		user.setEmail("rr@gmail.com");
		model.addAttribute("user", user);
		model.addAttribute("title", profileTitle.concat(user.getName() + " " + user.getLastname()));
		return "profile";
	}

	@RequestMapping("/user-list")
	public String list(Model model) {

		model.addAttribute("title", userListTitle);
		return "userlist";
	}

	/*
	 * Cuando utilizamos @ModelAttribute, los datos del método estarán disponibles para todo el controlador
	 * Accediendo al atributo "users" desde cualquier vista, los datos estarán disponibles
	 */
	@ModelAttribute("users")
	public List<User> getUsers() {
		return Arrays.asList(new User("Jorge", "Suarez", "jsuarez@algo.com"),
				new User("Maria", "Fernandez", "mfernandez@algo.com"),
				new User("Mario", "Rodriguez", "mrodriguez@algo.com"), new User("Leon", "Extremo", "elleon@algo.com"));
	}

}
