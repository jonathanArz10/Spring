package com.mpersd.spring.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mpersd.spring.dominio.Sale;
import com.mpersd.spring.negocio.ISaleBO;

@Controller
@RequestMapping("/admin/ventas")
public class SalesAdminController {
	
	@Autowired
	private ISaleBO sbo;

	@RequestMapping(method=RequestMethod.GET)
	public String indexTerminal(ModelMap modelo) {
		List<Sale> ventas = sbo.findAll();
		modelo.addAttribute("ventas", ventas);
		return "admin/ventas";
	}

}
