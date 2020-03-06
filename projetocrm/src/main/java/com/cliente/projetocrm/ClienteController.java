package com.cliente.projetocrm;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 */
@Path("/hello")
@Singleton

public class ClienteController {
	
	@GET
    public String sayHello() {
        return "Hello !";
    }

}
