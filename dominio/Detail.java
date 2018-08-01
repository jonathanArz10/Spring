package com.mpersd.spring.dominio;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the details database table.
 * 
 */
@Entity
@Table(name="details")
@NamedQuery(name="Detail.findAll", query="SELECT d FROM Detail d")
public class Detail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private double price;
	
	private String type;

	//bi-directional many-to-one association to Sale
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sale_id")
	private Sale sale;

	//bi-directional many-to-one association to Passenger
	@OneToMany(mappedBy="detail")
	private List<Passenger> passengers;

	public Detail() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Sale getSale() {
		return this.sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public List<Passenger> getPassengers() {
		return this.passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public Passenger addPassenger(Passenger passenger) {
		getPassengers().add(passenger);
		passenger.setDetail(this);

		return passenger;
	}

	public Passenger removePassenger(Passenger passenger) {
		getPassengers().remove(passenger);
		passenger.setDetail(null);

		return passenger;
	}

}