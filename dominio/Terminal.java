package com.mpersd.spring.dominio;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the terminals database table.
 * 
 */
@Entity
@Table(name="terminals")
@NamedQuery(name="Terminal.findAll", query="SELECT t FROM Terminal t")
public class Terminal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String city;

	private String direction;

	private String name;

	private String phoneNumber;

	//bi-directional many-to-one association to Travel
	@OneToMany(mappedBy="terminal1", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Travel> travels1;

	//bi-directional many-to-one association to Travel
	@OneToMany(mappedBy="terminal2", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Travel> travels2;

	public Terminal() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Travel> getTravels1() {
		return this.travels1;
	}

	public void setTravels1(List<Travel> travels1) {
		this.travels1 = travels1;
	}

	public Travel addTravels1(Travel travels1) {
		getTravels1().add(travels1);
		travels1.setTerminal1(this);

		return travels1;
	}

	public Travel removeTravels1(Travel travels1) {
		getTravels1().remove(travels1);
		travels1.setTerminal1(null);

		return travels1;
	}

	public List<Travel> getTravels2() {
		return this.travels2;
	}

	public void setTravels2(List<Travel> travels2) {
		this.travels2 = travels2;
	}

	public Travel addTravels2(Travel travels2) {
		getTravels2().add(travels2);
		travels2.setTerminal2(this);

		return travels2;
	}

	public Travel removeTravels2(Travel travels2) {
		getTravels2().remove(travels2);
		travels2.setTerminal2(null);

		return travels2;
	}

}