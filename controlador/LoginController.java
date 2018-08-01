package com.mpersd.spring.controlador;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mpersd.spring.dominio.Role;
import com.mpersd.spring.dominio.User;
import com.mpersd.spring.negocio.IUserBO;


@Controller
public class LoginController {
	
	@Autowired
	private IUserBO ubo;

	public LoginController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(path = "/login", method=RequestMethod.GET)
	public String loginGet(ModelMap modelo) {
		User user = new User();
		modelo.addAttribute("user", user);
		return "login";
	}
	
	@RequestMapping(path = "/login", method=RequestMethod.POST)
	public String loginPost(ModelMap modelo, HttpSession session,
			@ModelAttribute("user") @Valid User user, BindingResult result) {
		if(result.hasErrors()){
			System.out.println("hubo errores de validacion");
			return "login";
		} else {
			User u = ubo.login(user);
			if (u != null) {
				session.setAttribute("user", u);
				for (Role role : u.getRoles()) {
					if(role.getRole().equals("ROLE_ADMIN")) {
						return "redirect: admin/users";
					}
				}
				return "redirect:/";
			}else {
				modelo.addAttribute("error", "Password y correo electrónico invalidos");
				return "login";
			}
		}
	}
	
	@RequestMapping(path = "/registro", method = RequestMethod.GET)
	public String registroGet(ModelMap modelo) {
		User user = new User();
		modelo.addAttribute("user", user);
		return "registro";
	}
	
	@RequestMapping(path = "/registro", method = RequestMethod.POST)
	public String registroPost(ModelMap modelo, HttpSession session,
			@ModelAttribute("user") @Valid User user, BindingResult result) {
		if(result.hasErrors()){
			System.out.println("hubo errores de validacion");
			return "registro";
		} else {
			ubo.registerUser(user);
			session.setAttribute("user", user);
			return "redirect:/";
		}
	}
	
	@RequestMapping("/logout")
	public String salir(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
