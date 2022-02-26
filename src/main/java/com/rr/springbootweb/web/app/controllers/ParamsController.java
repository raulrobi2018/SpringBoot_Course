package com.rr.springbootweb.web.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/params")
public class ParamsController {

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("title", "Sending params to the Request");
		return "params/index";
	}

	@GetMapping("/string")
	public String param(@RequestParam(name = "text", required = false, defaultValue = "None") String text,
			Model model) {
		model.addAttribute("result", "The param sending is " + text);
		model.addAttribute("title", "Receiving params from the Request");
		return "params/look";
	}

	@GetMapping("/mix-params")
	public String param(@RequestParam String greeting, @RequestParam Integer number, Model model) {
		model.addAttribute("result", "The greeting is: " + greeting + " and the number is " + number);
		return "params/look";
	}

	@GetMapping("/mix-params-request")
	public String param(HttpServletRequest request, Model model) {
		String greeting = request.getParameter("greeting");
		Integer number = null;
		try {
			number = Integer.parseInt(request.getParameter("number"));
		} catch (NumberFormatException e) {
			number = 0;
		}

		model.addAttribute("result", "The greeting is: " + greeting + " and the number is " + number);
		return "params/look";
	}
}
