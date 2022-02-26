package com.rr.springbootweb.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vars")
public class PathVariablesController {
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("title", "Sending params from the path usin @PathVariable");
		return "vars/index";
	}
	
	//Recibiendo una variable
	@GetMapping("/string/{text}")
	//Cuando el nombre de la variable se llama igual, se puede omitir poner name="text"
	public String vars(@PathVariable(name="text") String text, Model model) {
		model.addAttribute("title", "Receiving a param from the path using @PathVariable");
		model.addAttribute("result", "Value received: " + text);
		return "vars/display";
	}
	
	
	//Recibiendo más de una variable
	@GetMapping("/string/{text}/{number}")
	//Cuando el nombre de la variable se llama igual, se puede omitir poner name="text"
	public String vars(@PathVariable(name="text") String text, @PathVariable Integer number, Model model) {
		model.addAttribute("title", "Receiving params from the path using @PathVariable");
		model.addAttribute("result", "Value and number received: " + text + " " + number);
		return "vars/display";
	}

}
