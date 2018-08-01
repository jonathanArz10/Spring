package com.mpersd.spring.controlador;

import java.util.ArrayList;
import java.util.List;

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

import com.mpersd.spring.dominio.Role;
import com.mpersd.spring.dominio.User;
import com.mpersd.spring.negocio.IUserBO;


@Controller
@RequestMapping("/admin/users")
public class UsersController {

	@Autowired
	private IUserBO ubo;
	
	public UsersController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String usersGet(ModelMap modelo) {
		List<User> lista = ubo.buscarUsuarios();
		modelo.addAttribute("usuarios", lista);
		return "admin/users";
	}
	
	@RequestMapping(value="/borrar")
	public String borrar(ModelMap modelo, @RequestParam("id") int id) {
		User user = ubo.getUserWithRoles(id);
		for (Role role : user.getRoles()) {
			if(role.getRole().equals("ROLE_ADMIN")) {
				List<User> lista = ubo.getUsers();
				modelo.addAttribute("usuarios", lista);
				modelo.addAttribute("mensaje", "No puedes eliminar al Administrador");
				return "admin/users";
			}
		}
		ubo.borrarUser(user);
		return "redirect:/admin/users";
	}
	
	@RequestMapping(path = "/actualizar",method=RequestMethod.GET)
	public String updateGet(ModelMap modelo, @RequestParam("id") int id) {
		User user = ubo.getUser(id);
		modelo.addAttribute("user", user);
		return "admin/update";
	}
	
	@RequestMapping(path = "/actualizar",method=RequestMethod.POST)
	public String updatePost(ModelMap modelo,
			 @ModelAttribute("user") User user, BindingResult result) {
		if(result.hasErrors()){
			System.out.println("hubo errores de validacion");
			return "admin/update";
		} else {
			ubo.actualizarUser(user);
			return "redirect:/admin/users";
		}
	}
	
	@RequestMapping(path="/nuevo", method=RequestMethod.GET)
	public String createUserGet(ModelMap modelo) {
		User user = new User();
		modelo.addAttribute("user", user);
		return "admin/createUser";
	}
	
	@RequestMapping(path="/nuevo", method=RequestMethod.POST)
	public String createUserPost(ModelMap modelo, @ModelAttribute("user") @Valid User user, BindingResult result, @RequestParam("role") String role) {
		if(result.hasErrors()){
			System.out.println("hubo errores de validacion");
			if (role.equals("default")) {
				modelo.addAttribute("roleError", "Debes elegir una opción");
			}
			return "admin/createUser";
		} else {
			if (ubo.getUser(user.getEmail()) == null) {
				ubo.createWithRoles(user, role);
				return "redirect:/admin/users";
			}
			modelo.addAttribute("emailError", "Ya existe un usuario con esta cuenta.");
			return "admin/createUser";
		}
	}

}
