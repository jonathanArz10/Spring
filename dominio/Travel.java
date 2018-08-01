package com.mpersd.spring.dominio;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the travels database table.
 * 
 */
@Entity
@Table(name="travels")
@NamedQuery(name="Travel.findAll", query="SELECT t FROM Travel t")
public class Travel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String hourArrival;

	private String hourStar;

	private float price;

	private String scales;

	private int type;

	//bi-directional many-to-one association to Bus
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="bus")
	private Bus busBean;

	//bi-directional many-to-one association to Terminal
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="start")
	private Terminal terminal1;

	//bi-directional many-to-one association to Terminal
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="arrival")
	private Terminal terminal2;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="driver")
	private User user;

	public Travel() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHourArrival() {
		return this.hourArrival;
	}

	public void setHourArrival(String hourArrival) {
		this.hourArrival = hourArrival;
	}

	public String getHourStar() {
		return this.hourStar;
	}

	public void setHourStar(String hourStar) {
		this.hourStar = hourStar;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getScales() {
		return this.scales;
	}

	public void setScales(String scales) {
		this.scales = scales;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Bus getBusBean() {
		return this.busBean;
	}

	public void setBusBean(Bus busBean) {
		this.busBean = busBean;
	}

	public Terminal getTerminal1() {
		return this.terminal1;
	}

	public void setTerminal1(Terminal terminal1) {
		this.terminal1 = terminal1;
	}

	public Terminal getTerminal2() {
		return this.terminal2;
	}

	public void setTerminal2(Terminal terminal2) {
		this.terminal2 = terminal2;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}