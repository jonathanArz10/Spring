package com.mpersd.spring.datos;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.mpersd.spring.dominio.Terminal;

@Repository
public class TerminalDAOJPA extends AbstractDAO<Terminal> implements ITerminalDAO {

	public TerminalDAOJPA() {
		super();
		super.setClazz(Terminal.class);
	}

	@Override
	public Terminal buscarPorNombre(String nombre) {
		Query query = super.entityManager
				.createQuery("SELECT t from Terminal t WHERE t.name = :nombre")
				.setParameter("nombre", nombre);
		List<Terminal> lista = query.getResultList();
		if (!lista.isEmpty()) {
			return lista.get(0);
		}
		return null;
	}

	@Override
	public Terminal esTerminalLlegada(int id) {
		Query query = super.entityManager
				.createQuery("SELECT t from Terminal t "
						+ "JOIN FETCH t.travels1 "
						+ " WHERE t.id = :id")
				.setParameter("id", id);
		List<Terminal> lista = query.getResultList();
		if (!lista.isEmpty()) {
			return lista.get(0);
		}
		return null;
	}

	@Override
	public Terminal esTerminalSalida(int id) {
		Query query = super.entityManager
				.createQuery("SELECT t from Terminal t "
						+ "JOIN FETCH t.travels2 "
						+ " WHERE t.id = :id")
				.setParameter("id", id);
		List<Terminal> lista = query.getResultList();
		if (!lista.isEmpty()) {
			return lista.get(0);
		}
		return null;
	}	
}
