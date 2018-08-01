package com.mpersd.spring.negocio;

import org.springframework.stereotype.Service;

import com.mpersd.spring.modelo.Carrito;

@Service
public class CarritoBO implements ICarritoBO {

	@Override
	public Integer calcularAsientos(Carrito carrito) {
		return carrito.getAdultoAsientos() + carrito.getKidAsientos() + carrito.getTerceraEdadAsientos();
	}

	@Override
	public Integer calcularTotal(Carrito carrito) {
		return  (int) ((carrito.getAdultoAsientos() * carrito.getPrecio()) + 
				(carrito.getKidAsientos() * carrito.getPrecio() * .5) + 
				(carrito.getTerceraEdadAsientos() * carrito.getPrecio() * .5));
	}

}
