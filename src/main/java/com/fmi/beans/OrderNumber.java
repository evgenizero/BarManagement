package com.fmi.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "NUMBEROFORDER")
public class OrderNumber {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "consumative_id")
	private Long consumativeId;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getConsumativeId() {
		return consumativeId;
	}

	public void setConsumativeId(Long consumativeId) {
		this.consumativeId = consumativeId;
	}

}
