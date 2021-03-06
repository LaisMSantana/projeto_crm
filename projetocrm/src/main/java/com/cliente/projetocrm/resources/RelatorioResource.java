package com.cliente.projetocrm.resources;

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.cliente.projetocrm.model.dao.RelatorioDao;
import com.cliente.projetocrm.model.dao.VendaDao;
import com.cliente.projetocrm.model.vo.Relatorio;
import com.cliente.projetocrm.model.vo.Venda;

@Path("/relatorios")
@RequestScoped
public class RelatorioResource {
	
	@Inject
	private RelatorioDao relatorioDao;
	
	@GET
	@Path("/data")
    @Produces(MediaType.APPLICATION_JSON)
	public Response listarPorData(@RequestBody Relatorio relatorio) {
		ArrayList<Venda> vendas = new ArrayList<Venda>();
		System.out.println(relatorio.toString());
		vendas = relatorioDao.totalPorData(relatorio);
		return Response.ok(vendas).build();
	}

	private ArrayList<Venda> validarDados(Relatorio relatorio) {
		if((validarData(relatorio) > 0) && validarMarcaCategoriaPgmt(relatorio) > 0) {
			
		} else if(!relatorio.getDataInicial().isEmpty()) {
			
		}
		
		return null;
	}
	
	private int validarData(Relatorio relatorio) {
		int resposta = 0; 
		if(!relatorio.getDataInicial().isEmpty() && !relatorio.getDataFinal().isEmpty()) {
		 resposta = 1;
		} else if(!relatorio.getDataInicial().isEmpty()) {
			resposta = 2;
		}
		return resposta;
	}
	
	private int validarMarcaCategoriaPgmt(Relatorio relatorio) {
		int resposta = 0; 
		if(relatorio.getIdMarca() > 0) {
		 resposta = 1;
		} else if(relatorio.getIdCategoria() > 0) {
			resposta = 2;
			} else if(!relatorio.getFormaDePagamento().isEmpty()) {
					resposta = 3;
				}
		return resposta;
	}
	
	

}
