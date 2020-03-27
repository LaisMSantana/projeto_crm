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
		System.out.println(produto.toString());
		int produtoId = produtoDao.salvarProduto(produto);
		Map<String, Integer> stringMessage = new HashMap<String, Integer>();
		stringMessage.put("idProduto", produtoId);
		return Response.status(Status.CREATED).entity(stringMessage).build();
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
	@Path("{idProduto}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizarProduto(@PathParam("idProduto") int id, Produto produto) {
		Produto atualizarProduto = produtoDao.encontrarPorId(id);
		
		System.out.println(produto.toString());
		
		atualizarProduto.setIdProduto(produto.getIdProduto());
		atualizarProduto.setNome(produto.getNome());
		atualizarProduto.setCodigo(produto.getCodigo());
		atualizarProduto.setMarca(produto.getMarca());
		atualizarProduto.setTipo(produto.getTipo());
		
		produtoDao.atualizar(atualizarProduto);
		
		return Response.ok().build();
	}

}
