package com.cliente.projetocrm.resources;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import javax.ws.rs.core.MediaType;

import com.cliente.projetocrm.model.dao.ClienteDao;
import com.cliente.projetocrm.model.vo.Cliente;

@Path("/clientes")
@RequestScoped
public class ClienteResource {

	@Inject
	private ClienteDao clienteDao;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salvarCliente(Cliente cliente) {
		System.out.println(cliente.toString());
		int clienteId = clienteDao.salvarCliente(cliente);
		Map<String, Integer> stringMessage = new HashMap<String, Integer>();
		stringMessage.put("clienteId", clienteId);
		return Response.status(Status.CREATED).entity(stringMessage).build();
	}

	@GET
	public String sayHello() {
		return "Hello !";
	}

}
