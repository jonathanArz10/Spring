package com.mpersd.spring.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/autobuses")
public class BusController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String indexTerminal() {
		return "admin/autobuses";
	}
}
