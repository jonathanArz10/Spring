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

import com.mpersd.spring.negocio.IBusBO;
import com.mpersd.spring.negocio.ITravelBO;
import com.mpersd.spring.dominio.Bus;
import com.mpersd.spring.dominio.Travel;

@RestController @RequestMapping("/admin/api/autobuses")
public class BusRestController {
	
	@Autowired
	private IBusBO busbo;
	
	@Autowired
	private ITravelBO travelbo;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Bus>> terminalesGet() {
		List<Bus> buses = busbo.findAll();
		if (buses.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(buses, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> crearTerminal(@RequestBody Bus bus) {
		if (busbo.buscarPorNombre(bus.getEnrollment()) == null) {
			busbo.create(bus);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@RequestMapping(path="/{id}",
			method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bus> obtenerTerminal(@PathVariable("id") int id) {
		Bus buspers = busbo.findOne(id);
		if(buspers != null) {
			return new ResponseEntity<>(buspers, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
	}
	
//	@RequestMapping(path="/{id}", method=RequestMethod.PUT,
//			produces=MediaType.APPLICATION_JSON_VALUE,
//			consumes=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Bus> actualizarTerminal(@PathVariable("id") int id,
//			@RequestBody Bus bus) {
//		System.out.println(bus.getId());
//		if (id != bus.getId()) {
//			return new ResponseEntity<Bus>(HttpStatus.CONFLICT);
//		}
//		
//		if (busbo.findOne(id) == null) {
//			return new ResponseEntity<Bus>(HttpStatus.NOT_FOUND);
//		}
//		busbo.update(bus);
//		return new ResponseEntity<Bus>(bus, HttpStatus.OK);
//	}
//	
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> eliminarTerminal(@PathVariable("id") int id) {
		Bus buspers = busbo.findOne(id);
		if (buspers == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			List<Travel> lista = travelbo.findAll();
			for(Travel travel : lista) {
				if(travel.getBusBean().getId() == id) {
					return new ResponseEntity<>(HttpStatus.CONFLICT);
				}
			}
		}
		
		busbo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
