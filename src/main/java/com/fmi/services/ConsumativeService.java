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

import com.fmi.beans.Consumative;
import com.fmi.utils.HibernateUtil;

@Path("/consumatives")
public class ConsumativeService {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEmployee(Consumative consumative) {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
	    Session session = sf.openSession();
	    session.beginTransaction();
	    
	    Long id = (Long) session.save(consumative);
	    consumative.setId(id);
	    
	    session.getTransaction().commit();
	         
	    session.close();
		
	    if(id != null) {
	    	return Response.status(Status.CREATED).entity(consumative).build();
	    }
	    
	    return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@GET
	@Path("byType")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeeById(@QueryParam("type") String type) {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		List<Consumative> consumatives = (List<Consumative>) session
				.getNamedQuery("getConsumativesByType").setString("type", type)
				.list();

		session.getTransaction().commit();
		session.close();

		if (null == consumatives)
			return Response.status(Status.NO_CONTENT).build();

		return Response.ok(consumatives).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployees() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
	    Session session = sf.openSession();
	    session.beginTransaction();
	    
	    List<Consumative> consumatives = session.createQuery("FROM Consumative").list();

	    session.getTransaction().commit();
	    
	    session.close();
	    
	    if(consumatives != null) 
	    	return Response.ok(consumatives).build();
	    
	    return Response.status(Status.NO_CONTENT).build();
	}

}