package com.mpersd.spring.dominio;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int active;

	private String direction;

	private String email;

	private String lastName1;

	private String lastName2;

	private String name;

	private String password;

	private String phoneNumber;

	private String secondName;

	//bi-directional many-to-one association to Role
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Role> roles;

	//bi-directional many-to-one association to Sale
	@OneToMany(mappedBy="user")
	private List<Sale> sales;

	//bi-directional many-to-one association to Travel
	@OneToMany(mappedBy="user")
	private List<Travel> travels;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSecondName() {
		return this.secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Role addRole(Role role) {
		getRoles().add(role);
		role.setUser(this);

		return role;
	}

	public Role removeRole(Role role) {
		getRoles().remove(role);
		role.setUser(null);

		return role;
	}

	public List<Sale> getSales() {
		return this.sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	public Sale addSale(Sale sale) {
		getSales().add(sale);
		sale.setUser(this);

		return sale;
	}

	public Sale removeSale(Sale sale) {
		getSales().remove(sale);
		sale.setUser(null);

		return sale;
	}

	public List<Travel> getTravels() {
		return this.travels;
	}

	public void setTravels(List<Travel> travels) {
		this.travels = travels;
	}

	public Travel addTravel(Travel travel) {
		getTravels().add(travel);
		travel.setUser(this);

		return travel;
	}

	public Travel removeTravel(Travel travel) {
		getTravels().remove(travel);
		travel.setUser(null);

		return travel;
	}

}