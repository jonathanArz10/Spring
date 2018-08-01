package com.mpersd.spring.datos;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.mpersd.spring.dominio.Sale;
import com.mpersd.spring.dominio.User;

@Repository
public class SaleDAOJPA extends AbstractDAO<Sale> implements ISaleDAO {

	public SaleDAOJPA() {
		super();
		super.setClazz(Sale.class);
	}

	@Override
	public Sale obtenerPorUsuario(User user) {
		Query q = super.entityManager
				.createQuery("SELECT s FROM Sale s WHERE s.user = :user")
				.setParameter("user", user);
		List<Sale> lista = q.getResultList();
		if(!lista.isEmpty()) {
			return lista.get(0);
		}
		return null;
	}

	@Override
	public Sale obtenerPorUsuarioConDetalle(User user) {
		Query q = super.entityManager
				.createQuery("SELECT s FROM Sale s JOIN FETCH s.details WHERE s.user = :user")
				.setParameter("user", user);
		List<Sale> lista = q.getResultList();
		if(!lista.isEmpty()) {
			return lista.get(0);
		}
		return null;
	}
	
}
