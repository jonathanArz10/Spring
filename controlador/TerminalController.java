package com.mpersd.spring.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/terminales")
public class TerminalController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String indexTerminal() {
		return "admin/terminales";
	}
}
