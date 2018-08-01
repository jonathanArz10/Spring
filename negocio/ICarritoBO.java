package com.mpersd.spring.negocio;

import com.mpersd.spring.modelo.Carrito;

public interface ICarritoBO {
	Integer calcularAsientos(Carrito carrito);
	Integer calcularTotal(Carrito carrito);
}
