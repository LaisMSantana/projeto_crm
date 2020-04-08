package com.cliente.projetocrm.resources;

import java.util.ArrayList;

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

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.ws.rs.core.MediaType;

import com.cliente.projetocrm.model.dao.VendaDao;
import com.cliente.projetocrm.model.vo.Venda;

@Path("/vendas")
@RequestScoped
public class VendaResource {

	@Inject
	private VendaDao vendaDao;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salvarVenda(Venda venda) throws Exception {
		int novoId = vendaDao.cadastrarVenda(venda);
		venda.setIdVenda(novoId);
		System.out.println(venda.toString());
		vendaDao.cadastrarItemVenda(venda);
		
		return Response.status(Status.CREATED).entity(venda).build();
	}

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response listarTodasVendas() throws Exception {
		return Response.ok(vendaDao.listarTodos()).build();
	}
	
	@GET
	@Path("{venda}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response listarPorFiltro(@PathParam("venda") String venda) throws Exception {
		ArrayList<Venda> vendas = vendaDao.listarPorFiltro(venda);
		
		return Response.ok(vendas).build();
	}
	
	@GET
	@Path("/venda/{idVenda}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response listarPorId(@PathParam("idVenda") int idVenda) {
		Venda venda = vendaDao.encontrarPorId(idVenda);
		return Response.ok(venda).build();
	}
	
	@PUT
	@Path("/{idVenda}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizarCliente(@PathParam("idVenda") int id, @RequestBody Venda venda) throws Exception {
		Venda atualizarVenda = vendaDao.encontrarPorId(id);
		
		atualizarVenda.setIdCliente(id);
		System.out.println(atualizarVenda);
		
		vendaDao.atualizar(atualizarVenda);
		
		return Response.ok().build();
	}
}

