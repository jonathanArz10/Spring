package com.mpersd.spring.controlador;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mpersd.spring.dominio.Travel;
import com.mpersd.spring.dominio.User;
import com.mpersd.spring.modelo.Carrito;
import com.mpersd.spring.negocio.ITravelBO;

@Controller
public class HomeController {
	
	@Autowired
	private ITravelBO travelbo;

	public HomeController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/viajes")
	public String viajes(ModelMap modelo, HttpSession session) {
		Carrito carrito = (Carrito) session.getAttribute("carrito");
		if(carrito != null) {
			carrito = null;
			session.setAttribute("carrito", carrito);
		}
		List<Travel> viajes = travelbo.findAll();
		modelo.addAttribute("viajes", viajes);
		return "travels";
	}
	
	@RequestMapping(path="/viaje", method=RequestMethod.GET)
	public String viajeGET(ModelMap modelo, @RequestParam("id") int id, HttpSession session) {
		User u = (User) session.getAttribute("user");
		if (u == null) {
			User user = new User();
			modelo.addAttribute("user", user);
			return "redirect:/login";
		}
		Travel travel = travelbo.findOne(id);
		Carrito carrito = new Carrito();
		modelo.addAttribute("travel", travel);
		modelo.addAttribute("carrito", carrito);
		return "sales";
	}
}
