package com.mpersd.spring.controlador;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mpersd.spring.dominio.Detail;
import com.mpersd.spring.dominio.Sale;
import com.mpersd.spring.dominio.Travel;
import com.mpersd.spring.dominio.User;
import com.mpersd.spring.modelo.Carrito;
import com.mpersd.spring.negocio.ICarritoBO;
import com.mpersd.spring.negocio.IDetailBO;
import com.mpersd.spring.negocio.ISaleBO;
import com.mpersd.spring.negocio.ITravelBO;


@Controller @RequestMapping("/comprar")
public class CarritoController {
	
	@Autowired
	private ICarritoBO cbo;
	
	@Autowired
	private ITravelBO tbo;
	
	@Autowired
	private ISaleBO sbo;
	
	@Autowired
	private IDetailBO dbo;

	@RequestMapping(method=RequestMethod.POST)
	public String carritoGet(@ModelAttribute("carrito") 
		@Valid Carrito carrito, BindingResult result, 
		@RequestParam("fechaSalida") String fechaSalida,
		@RequestParam("fechaRegreso") String fechaRegreso,
		HttpSession session, ModelMap modelo) {
		if (carrito.getAdultoAsientos() == 0 && carrito.getKidAsientos() == 0 
				&& carrito.getTerceraEdadAsientos() == 0) {
			return "redirect:/viaje?id=" + carrito.getTravel_id();
		}
		carrito.setFechaSalida(fechaSalida);
		carrito.setFechaRegreso(fechaRegreso);
		carrito.setAsientos(cbo.calcularAsientos(carrito));
		Travel travel = tbo.findOne(carrito.getTravel_id());
		carrito.setPrecio((int) travel.getPrice());
		carrito.setTotal(cbo.calcularTotal(carrito));
		carrito.setUser((User) session.getAttribute("user"));
		System.out.println(carrito.toString());
		session.setAttribute("carrito", carrito);
		return "redirect:comprar/asientos";
	}
	
	@RequestMapping(path = "/asientos", method=RequestMethod.GET)
	public String asientosGet(HttpSession session,
				ModelMap modelo) {
		Carrito carrito = (Carrito) session.getAttribute("carrito");
		Travel travel = tbo.findOne(carrito.getTravel_id());
		modelo.addAttribute("travel", travel);
		return "asientos";
	}
	
	@RequestMapping(path = "/terminar", method=RequestMethod.GET)
	public String terminarGet(HttpSession session,
				ModelMap modelo) {
		Carrito carrito = (Carrito) session.getAttribute("carrito");
		Travel travel = tbo.findOne(carrito.getTravel_id());
		modelo.addAttribute("travel", travel);
		return "terminar";
	}
	
	@RequestMapping(path = "/terminar", method=RequestMethod.POST)
	public String guardarVenta(HttpSession session, @RequestParam("payment") String payment,
			ModelMap modelo) {
		Carrito carrito = (Carrito) session.getAttribute("carrito");
		carrito.setPayment(payment);
		System.out.println(carrito.toString());
		Sale sale = new Sale();
		sale.setTotal(carrito.getTotal());
		sale.setPayment(carrito.getPayment());
		sale.setDate(carrito.getFechaSalida());
		sbo.crearVenta(sale, carrito.getUser().getId());
		for (int i = 0; i < carrito.getAdultoAsientos(); i++) {
			Detail detail = new Detail();
			detail.setPrice(carrito.getPrecio());
			detail.setType("Adulto");
			dbo.crearDetalle(detail, carrito.getUser());
		}
		for (int i = 0; i < carrito.getKidAsientos(); i++) {
			Detail detail = new Detail();
			detail.setPrice(carrito.getPrecio() * .5);
			detail.setType("Niño");
			dbo.crearDetalle(detail, carrito.getUser());
		}
		for (int i = 0; i < carrito.getTerceraEdadAsientos(); i++) {
			Detail detail = new Detail();
			detail.setPrice(carrito.getPrecio());
			detail.setType("Tercera edad");
			dbo.crearDetalle(detail, carrito.getUser());
		}
		Sale newSale = sbo.obtenerPorUsuarioConDetalle(carrito.getUser());
		Travel travel = tbo.findOne(carrito.getTravel_id());
		carrito = null;
		session.setAttribute("carrito", carrito);
		modelo.addAttribute("travel", travel);
		modelo.addAttribute("sale", newSale);
		return "venta";
	}

}
