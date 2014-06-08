package com.javacodegeeks.enterprise.rest.jersey.test;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class OrderTest extends JerseyTest {

	private WebResource webResource;

	@Override
	protected AppDescriptor configure() {

		DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
		defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(defaultClientConfig);

		webResource = client.resource("http://localhost:8080/BarManagement");

		return new WebAppDescriptor.Builder().build();
	}

	@Test
	public void testCreateOrder() {
		String consumativeIds = "[1, 2, 3, 4]";
		
		webResource.path("/rest/orders/orders/newOrder").type(
				MediaType.APPLICATION_JSON).post(String.class, consumativeIds);

	}
}