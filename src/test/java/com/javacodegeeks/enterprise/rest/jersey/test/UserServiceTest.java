package com.javacodegeeks.enterprise.rest.jersey.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.junit.Test;

import com.javacodegeeks.enterprise.rest.jersey.Employee;
import com.javacodegeeks.enterprise.rest.jersey.Employee.EmployeeType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class UserServiceTest extends JerseyTest {

	private WebResource webResource;

	@Override
	protected AppDescriptor configure() {

		DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(defaultClientConfig);

		webResource = client.resource("http://localhost:8080/JAXRS-HelloWorld");

		return new WebAppDescriptor.Builder().build();
	}

	@Test
	public void testCreateUser() {
		Employee empl = new Employee("asdf", "sfsd", EmployeeType.Waiter);
		empl = webResource.path("/rest/users/")
				.type(MediaType.APPLICATION_JSON).post(Employee.class, empl);
		assertNotNull(empl.getId());
	}

	@Test
	public void testGetUserById() {
		Employee emp = webResource.path("/rest/users/1")
				.accept(MediaType.APPLICATION_JSON).get(Employee.class);
		assertNotNull(emp);
		ClientResponse response = webResource.path("/rest/users/100").get(
				ClientResponse.class);
		assertEquals(204, response.getStatus());
	}

	@Test
	public void testGetAllUsers() {
		System.out.println("-------- Testing testGetAllUesrs --------");
		List<Employee> employees = webResource.path("/rest/users/").accept(MediaType.APPLICATION_JSON)
				.get(List.class);
		assertNotNull(employees);
	}

	@Test
	public void testUpdateUser() {

	}

	@Test
	public void testDeleteUser() {

	}

}