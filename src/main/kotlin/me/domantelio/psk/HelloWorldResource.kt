package org.eclipse.jakarta.hello

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType

@Path("/hello")
public class HelloWorldResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public fun hello(@QueryParam("name") name: String): Hello {
		val name = if (name == null || name.trim().isEmpty())  {
			"world"
		} else { name }

		return Hello(name)
	}
}