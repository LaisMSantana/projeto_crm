package com.cliente.projetocrm.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cliente.projetocrm.model.vo.Venda;

public class VendaDao {
	
	public int cadastrarVenda(Venda venda) throws Exception {
		int novoId = 0;
		
		String sql = " INSERT INTO VENDA (DATA_VENDA, VALOR, FORMA_DE_PAGAMENTO, IDCLIENTE) VALUES (?,?,?,?) ";
		
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		
		String dataAtual = this.getDateTime();
		Date data = new SimpleDateFormat("yyyy-MM-dd").parse(dataAtual);
		
		try {
			prepStmt.setDate(1, new java.sql.Date(data.getTime()));
			prepStmt.setDouble(2, venda.getValor());
			prepStmt.setString(3, venda.getFormaDePagamento());
			prepStmt.setInt(4, venda.getIdCliente());
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
		int resultado = 0;
		int contador = 0;
		
		String sql = "";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		
		try {
			for(int i = 0; i < venda.getProdutos().size(); i++){
				
				venda.getProdutos().get(i).setIdVenda(venda.getIdVenda());
				
				sql += "INSERT INTO ITEMPRODUTO (IDPRODUTO, IDVENDA, QUANTIDADE) VALUES (?,?,?)";
				
				prepStmt.setInt(1, venda.getProdutos().get(i).getIdProduto());
				prepStmt.setInt(2, venda.getProdutos().get(i).getIdVenda());
				prepStmt.setInt(3, venda.getProdutos().get(i).getQuantidade());
				
				resultado = prepStmt.executeUpdate(sql);
				if(resultado == 1){
					contador++;
				}
			}
			ResultSet generatedKeys = prepStmt.getGeneratedKeys();
			if((contador == venda.getProdutos().size()) && (generatedKeys.next())){
				retorno = generatedKeys.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar Venda com produtos. Causa: \n: " + e.getMessage());
		} finally {
			Banco.closeStatement(prepStmt);
			Banco.closeConnection(conexao);
		}
		
		return retorno;
	}
	
	private String getDateTime() {
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = new Date();
	    return dateFormat.format(date);
	}

}
