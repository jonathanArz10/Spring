package com.mpersd.spring.dominio;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the sales database table.
 * 
 */
@Entity
@Table(name="sales")
@NamedQuery(name="Sale.findAll", query="SELECT s FROM Sale s")
public class Sale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String date;

	private String payment;

	private Integer total;

	//bi-directional many-to-one association to Detail
	@OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Detail> details;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public Sale() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPayment() {
		return this.payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public Integer getTotal() {
		return this.total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<Detail> getDetails() {
		return this.details;
	}

	public void setDetails(List<Detail> details) {
		this.details = details;
	}

	public Detail addDetail(Detail detail) {
		getDetails().add(detail);
		detail.setSale(this);

		return detail;
	}

	public Detail removeDetail(Detail detail) {
		getDetails().remove(detail);
		detail.setSale(null);

		return detail;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}