package com.javacodegeeks.enterprise.rest.jersey.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.junit.Test;

import com.fmi.beans.Consumative;
import com.fmi.beans.Employee;
import com.fmi.beans.Consumative.ConsumativeType;
import com.fmi.beans.Employee.EmployeeType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class ConsumativeServiceTest extends JerseyTest {

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
		Consumative consumative = new Consumative();
		consumative.setConsumativeName("asdfa");
		consumative.setPrice(234f);
		consumative.setType(ConsumativeType.Drink);
		
		consumative = webResource.path("/rest/consumatives/")
				.type(MediaType.APPLICATION_JSON).post(Consumative.class, consumative);
		assertNotNull(consumative.getId());
	}
}