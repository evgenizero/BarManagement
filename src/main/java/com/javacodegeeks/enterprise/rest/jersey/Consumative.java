package com.javacodegeeks.enterprise.rest.jersey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@NamedQueries({
	@NamedQuery(
	name = "getConsumativesByType",
	query = "from Consumative con where con.type = :type"
	)
}) 

@Entity
@Table(name="CONSUMATIVE")
public class Consumative {
	public enum ConsumativeType {
		Drink,
		Coctail,
		Snack
	};
	
	@Id
    @GeneratedValue
    private Long id;

	@Column(name="consumative_name")
	private String consumativeName;
	
	@Column(name="price")
	private Float price;
	
	@Enumerated(EnumType.STRING)
	@Column(name="type")
	private ConsumativeType type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConsumativeName() {
		return consumativeName;
	}

	public void setConsumativeName(String consumativeName) {
		this.consumativeName = consumativeName;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public ConsumativeType getType() {
		return type;
	}

	public void setType(ConsumativeType type) {
		this.type = type;
	}
}
