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

import com.cliente.projetocrm.model.dao.CategoriaDao;
import com.cliente.projetocrm.model.vo.Categoria;
import com.cliente.projetocrm.model.vo.Marca;

@Path("/categorias")
@RequestScoped
public class CategoriaResource {

	@Inject
	private CategoriaDao categoriaDao;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salvarCategoria(Categoria categoria) {
		int categoriaId = categoriaDao.salvarCategoria(categoria);
		categoria.setIdCategoria(categoriaId);
		System.out.println(categoria.toString());
		
		return Response.status(Status.CREATED).entity(categoria).build();
	}

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response listarTodasCategorias() {
		return Response.ok(categoriaDao.listarTodos()).build();
	}
	
	@GET
	@Path("{categoria}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response listarPorFiltro(@PathParam("categoria") String categoria) {
		ArrayList<Categoria> categorias = categoriaDao.listarPorFiltro(categoria);
		
		return Response.ok(categorias).build();
	}
	

	@GET
	@Path("/categoria/{idCategoria}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response listarPorId(@PathParam("idCategoria") int idCategoria) {
		Categoria categoria = categoriaDao.encontrarPorId(idCategoria);
		return Response.ok(categoria).build();
	}

	@PUT
	@Path("/{idCategoria}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizarCategoria(@PathParam("idCategoria") int id, @RequestBody Categoria categoria) {
		Categoria atualizarCategoria = categoriaDao.encontrarPorId(id);
		
		atualizarCategoria.setIdCategoria(id);
		atualizarCategoria.setCategoria(categoria.getCategoria());
		System.out.println(atualizarCategoria);
		
		categoriaDao.atualizar(atualizarCategoria);
		
		return Response.ok().build();
	}

}
