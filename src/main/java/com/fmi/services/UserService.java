package com.fmi.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fmi.beans.Employee;
import com.fmi.utils.HibernateUtil;

@Path("/users")
public class UserService {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEmployee(Employee employee) {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
	    Session session = sf.openSession();
	    session.beginTransaction();
	    
	    Long id = (Long) session.save(employee);
	    employee.setId(id);
	    
	    session.getTransaction().commit();
	         
	    session.close();
		
	    if(id != null) {
	    	return Response.status(Status.CREATED).entity(employee).build();
	    }
	    
	    return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeeById(@PathParam("id") String id) {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		Employee employee = (Employee) session
				.getNamedQuery("findEmployeeById").setString("id", id)
				.uniqueResult();

		session.getTransaction().commit();
		session.close();

		if (null == employee)
			return Response.status(Status.NO_CONTENT).build();

		return Response.ok(employee).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployees() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
	    Session session = sf.openSession();
	    session.beginTransaction();
	    
	    List<Employee> employees = session.createQuery("FROM Employee").list();

	    session.getTransaction().commit();
	    
	    session.close();
	    
	    if(employees != null) 
	    	return Response.ok(employees).build();
	    
	    return Response.status(Status.NO_CONTENT).build();
	}

}