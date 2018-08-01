package com.mpersd.spring.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mpersd.spring.negocio.ITerminalBO;
import com.mpersd.spring.negocio.ITravelBO;
import com.mpersd.spring.dominio.Terminal;
import com.mpersd.spring.dominio.Travel;

@RestController @RequestMapping("/admin/api/terminales")
public class TerminalRestController {
	
	@Autowired
	private ITerminalBO terminalbo;
	
	@Autowired
	private ITravelBO travelbo;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Terminal>> terminalesGet() {
		List<Terminal> terminales = terminalbo.findAll();
		if (terminales.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(terminales, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> crearTerminal(@RequestBody Terminal terminal) {
		if (terminalbo.buscarPorNombre(terminal.getName()) == null) {
			terminalbo.create(terminal);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@RequestMapping(path="/{id}",
			method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Terminal> obtenerTerminal(@PathVariable("id") int id) {
		Terminal terminalpers = terminalbo.findOne(id);
		if(terminalpers != null) {
			return new ResponseEntity<>(terminalpers, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.PUT,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Terminal> actualizarTerminal(@PathVariable("id") int id,
			@RequestBody Terminal terminal) {
		System.out.println(terminal.getId());
		if (id != terminal.getId()) {
			return new ResponseEntity<Terminal>(HttpStatus.CONFLICT);
		}
		
		if (terminalbo.findOne(id) == null) {
			return new ResponseEntity<Terminal>(HttpStatus.NOT_FOUND);
		}
		terminalbo.update(terminal);
		return new ResponseEntity<Terminal>(terminal, HttpStatus.OK);
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> eliminarTerminal(@PathVariable("id") int id) {
		Terminal terminalpers = terminalbo.findOne(id);
		if (terminalpers == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			List<Travel> lista = travelbo.findAll();
			for(Travel travel : lista) {
				if(travel.getTerminal1().getId() == id || travel.getTerminal2().getId() == id) {
					return new ResponseEntity<>(HttpStatus.CONFLICT);
				}
			}
		}
		
		terminalbo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
