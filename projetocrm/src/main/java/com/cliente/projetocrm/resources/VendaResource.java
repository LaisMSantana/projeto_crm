package com.cliente.projetocrm.resources;

import java.time.LocalDate;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
		venda.setDataVenda(LocalDate.now());
		System.out.println(venda.toString());
		vendaDao.cadastrarVenda(venda);
		vendaDao.cadastrarItemProduto(venda);
		
		return Response.status(Status.CREATED).entity(venda).build();
	}
}
