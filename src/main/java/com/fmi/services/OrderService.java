package com.fmi.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fmi.beans.BarOrder;
import com.fmi.beans.Consumative;
import com.fmi.beans.BarOrder.OrderStatus;
import com.fmi.beans.OrderNumber;
import com.fmi.utils.HibernateUtil;

@Path("/orders")
public class OrderService {

	@GET
	@Path("getOrders")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrdersByStatus(@QueryParam("status") String status) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		List<BarOrder> orders = (List<BarOrder>) session
				.getNamedQuery("getOrdersByStatus").setString("status", status)
				.list();

		session.getTransaction().commit();
		session.close();

		if (null == orders)
			return Response.status(Status.NO_CONTENT).build();

		return Response.ok(orders).build();
	}
	
	@POST
	@Path("newOrder")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createOrder(@QueryParam("waiter") String waiterId, List<Long> consumativesIds) {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		waiterId = "1";
		
		BarOrder order = new BarOrder();
		order.setWaiterId(Long.valueOf(waiterId));
		order.setStatus(OrderStatus.PENDING);
		
		Long orderId = (Long) session.save(order);
		order.setId(orderId);
		
		if(orderId == null) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		OrderNumber orderNumber = null;
		for(Long conId : consumativesIds) {
			orderNumber = new OrderNumber();
			orderNumber.setConsumativeId(conId);
			orderNumber.setOrderId(orderId);
			session.save(orderNumber);
		}
		
		session.getTransaction().commit();

		session.close();

		return Response.status(Status.CREATED).entity(order).build();
		
	}
}
