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

import com.cliente.projetocrm.model.dao.ProdutoDao;
import com.cliente.projetocrm.model.vo.Produto;

@Path("/produtos")
@RequestScoped
public class ProdutoResource {

	@Inject
	private ProdutoDao produtoDao;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salvarProduto(Produto produto) {
		int produtoId = produtoDao.salvarProduto(produto);
		produto.setIdProduto(produtoId);
		System.out.println(produto.toString());
		
		return Response.status(Status.CREATED).entity(produto).build();
	}

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response listarTodosProdutos() {
		return Response.ok(produtoDao.listarTodos()).build();
	}
	
	@GET
	@Path("{produto}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response listarPorFiltro(@PathParam("produto") String produto) {
		ArrayList<Produto> produtos = produtoDao.listarPorFiltro(produto);
		
		return Response.ok(produtos).build();
	}
	

	@PUT
	@Path("/{idProduto}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizarProduto(@PathParam("idProduto") int id, @RequestBody Produto produto) {
		Produto atualizarProduto = produtoDao.encontrarPorId(id);
		
		atualizarProduto.setIdProduto(id);
		atualizarProduto.setNome(produto.getNome());
		atualizarProduto.setCodigo(produto.getCodigo());
		atualizarProduto.setMarca(produto.getMarca());
		atualizarProduto.setTipo(produto.getTipo());
		System.out.println(atualizarProduto);
		
		produtoDao.atualizar(atualizarProduto);
		
		return Response.ok().build();
	}

}
