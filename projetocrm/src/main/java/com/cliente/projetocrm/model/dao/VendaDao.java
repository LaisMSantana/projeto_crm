package com.cliente.projetocrm.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import com.cliente.projetocrm.model.vo.Venda;

public class VendaDao {
	
	public int cadastrarVenda(Venda venda) {
		int novoId = 0;
		
		String sql = " INSERT INTO VENDA (DATA_VENDA, VALOR, FORMA_DE_PAGAMENTO, IDCLIENTE) VALUES (CURRENT_DATE,?,?,?) ";
		
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		
		try {
			prepStmt.setDouble(1, Double.parseDouble(venda.getValor()));
			prepStmt.setString(2, venda.getFormaDePagamento());
			prepStmt.setInt(3, Integer.parseInt(venda.getIdCliente()));
			prepStmt.execute();

			ResultSet generatedKeys = prepStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				novoId = generatedKeys.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar Venda. Causa: \n: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}
		
		return novoId;
	}
	
	public int cadastrarItemProduto(Venda venda) {
		int retorno = 0;
		boolean resultado = false;
		int contador = 0;
		
		String sql = "";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		
		try {
			for(int i = 0; i < venda.getProdutos().size(); i++){
				
				venda.getProdutos().get(i).setIdVenda(venda.getIdVenda());
				
				sql = "INSERT INTO ITEMPRODUTO (IDPRODUTO, IDVENDA, QUANTIDADE) VALUES (" 
						+ venda.getProdutos().get(i).getIdProduto() + ", " 
						+ venda.getIdVenda() + ","
						+ venda.getProdutos().get(i).getQuantidade();
				
				resultado = prepStmt.execute(sql);
				if(resultado == true){
					contador++;
				}
			}
			ResultSet generatedKeys = prepStmt.getGeneratedKeys();
			if((contador == venda.getProdutos().size()) && (generatedKeys.next())){
				retorno = generatedKeys.getInt(1);
			} else {
				System.out.println("Nem todos os pratos do pedido foram cadastrados!");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar Venda com produtos. Causa: \n: " + e.getMessage());
		} finally {
			Banco.closeStatement(prepStmt);
			Banco.closeConnection(conexao);
		}
		
		return retorno;
	}

}
