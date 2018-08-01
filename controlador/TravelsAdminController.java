package com.mpersd.spring.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mpersd.spring.dominio.Travel;
import com.mpersd.spring.negocio.ITravelBO;

@Controller
@RequestMapping("/admin/travels")
public class TravelsAdminController {
	@Autowired
	private ITravelBO travelbo;

	public TravelsAdminController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String travelsGet(ModelMap modelo) {
		List<Travel> viajes = travelbo.findAll();
		modelo.addAttribute("viajes", viajes);
		return "admin/index";
	}
}
