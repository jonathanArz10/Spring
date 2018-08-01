package com.mpersd.spring.dominio;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the passengers database table.
 * 
 */
@Entity
@Table(name="passengers")
@NamedQuery(name="Passenger.findAll", query="SELECT p FROM Passenger p")
public class Passenger implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int edad;

	private String lastName1;

	private String lastName2;

	private String name;

	private int seat;

	private String secondName;

	//bi-directional many-to-one association to Detail
	@ManyToOne
	private Detail detail;

	public Passenger() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEdad() {
		return this.edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getLastName1() {
		return this.lastName1;
	}

	public void setLastName1(String lastName1) {
		this.lastName1 = lastName1;
	}

	public String getLastName2() {
		return this.lastName2;
	}

	public void setLastName2(String lastName2) {
		this.lastName2 = lastName2;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeat() {
		return this.seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public String getSecondName() {
		return this.secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public Detail getDetail() {
		return this.detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}

}