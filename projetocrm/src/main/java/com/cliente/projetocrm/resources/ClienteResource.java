package com.cliente.projetocrm.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
		stringMessage.put("IDCLIENTE", clienteId);
		return Response.status(Status.CREATED).entity(stringMessage).build();
	}

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response listarTodosClientes() {
		return Response.ok(clienteDao.listarTodos()).build();
	}
	
	@GET
	@Path("{cliente}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response listarPorFiltro(@PathParam("cliente") String cliente) {
		ArrayList<Cliente> clientes = clienteDao.listarPorFiltro(cliente);
		
		return Response.ok(clientes).build();
	}
	

	@PUT
	@Path("{idCliente}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizarCliente(@PathParam("idCliente") int id, Cliente cliente) {
		Cliente atualizarCliente = clienteDao.encontrarPorId(id);
		
		System.out.println(cliente.toString());
		
		atualizarCliente.setIdCliente(cliente.getIdCliente());
		atualizarCliente.setNome(cliente.getNome());
		atualizarCliente.setCpf(cliente.getCpf());
		atualizarCliente.setEmail(cliente.getEmail());
		atualizarCliente.setDataDeNascimento(cliente.getDataDeNascimento());
		
		clienteDao.atualizar(atualizarCliente);
		
		return Response.ok().build();
	}

}
