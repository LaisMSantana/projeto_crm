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

import com.cliente.projetocrm.model.dao.MarcaDao;
import com.cliente.projetocrm.model.vo.Cliente;
import com.cliente.projetocrm.model.vo.Marca;

@Path("/marcas")
@RequestScoped
public class MarcaResource {

	@Inject
	private MarcaDao marcaDao;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salvarMarca(Marca marca) {
		int marcaId = marcaDao.salvarMarca(marca);
		marca.setIdMarca(marcaId);
		System.out.println(marca.toString());
		
		return Response.status(Status.CREATED).entity(marca).build();
	}

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response listarTodasMarcas() {
		return Response.ok(marcaDao.listarTodos()).build();
	}
	
	@GET
	@Path("{marca}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response listarPorFiltro(@PathParam("marca") String marca) {
		ArrayList<Marca> marcas = marcaDao.listarPorFiltro(marca);
		
		return Response.ok(marcas).build();
	}
	
	@GET
	@Path("/marca/{idMarca}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response listarPorId(@PathParam("idMarca") int idMarca) {
		Marca marca = marcaDao.encontrarPorId(idMarca);
		return Response.ok(marca).build();
	}
	
	@PUT
	@Path("/{idMarca}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizarMarca(@PathParam("idMarca") int id, @RequestBody Marca marca) {
		Marca atualizarMarca = marcaDao.encontrarPorId(id);
		
		atualizarMarca.setIdMarca(id);
		atualizarMarca.setMarca(marca.getMarca());
		System.out.println(atualizarMarca);
		
		marcaDao.atualizar(atualizarMarca);
		
		return Response.ok().build();
	}

}
