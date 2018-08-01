package com.mpersd.spring.dominio;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the buses database table.
 * 
 */
@Entity
@Table(name="buses")
@NamedQuery(name="Bus.findAll", query="SELECT b FROM Bus b")
public class Bus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String enrollment;

	private String model;

	private int status;

	private int type;

	//bi-directional many-to-one association to Travel
	@OneToMany(mappedBy="busBean")
	@JsonIgnore
	private List<Travel> travels;

	public Bus() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEnrollment() {
		return this.enrollment;
	}

	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<Travel> getTravels() {
		return this.travels;
	}

	public void setTravels(List<Travel> travels) {
		this.travels = travels;
	}

	public Travel addTravel(Travel travel) {
		getTravels().add(travel);
		travel.setBusBean(this);

		return travel;
	}

	public Travel removeTravel(Travel travel) {
		getTravels().remove(travel);
		travel.setBusBean(null);

		return travel;
	}

}