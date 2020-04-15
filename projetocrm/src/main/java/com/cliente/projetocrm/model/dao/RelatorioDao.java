package com.cliente.projetocrm.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cliente.projetocrm.model.vo.Relatorio;
import com.cliente.projetocrm.model.vo.Venda;

public class RelatorioDao {
	
	public ArrayList<Venda> totalPorData(Relatorio relatorio) {
		ArrayList<Venda> vendas = new ArrayList<Venda>();

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;

		String query = "SELECT *, to_char(\"data_venda\", 'DD/MM/YYYY') FROM VENDA WHERE";

		if(!relatorio.getDataInicial().isEmpty() && !relatorio.getDataFinal().isEmpty()) {
			query += " DATA_VENDA::text BETWEEN '"+ relatorio.getDataInicial() +"' AND '"+ relatorio.getDataFinal() +"'";
		} else if(!relatorio.getDataInicial().isEmpty()) {
			query += " DATA_VENDA::text = '"+ relatorio.getDataInicial() +"'";
		}
		
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Venda venda = new Venda();
				venda.setIdVenda(rs.getInt(1));
				venda.setDataVenda(rs.getString(7));
				venda.setValor(rs.getDouble(3));
				venda.setFormaDePagamento(rs.getString(4));
				venda.setParcelas(rs.getInt(5));
				venda.setIdCliente(rs.getInt(6));
				vendas.add(venda);
			}
		} catch (SQLException e) {
			System.out.println(
					"Erro ao executar a Query que verifica existência de Venda por data. Erro:" + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vendas;
	}
	
	public ArrayList<Venda> totalPorMarca(Relatorio relatorio) {
		ArrayList<Venda> vendas = new ArrayList<Venda>();

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;

		String query = "SELECT v.IDVENDA, to_char(\"data_venda\", 'DD/MM/YYYY'), v.VALOR, "
				+ "v.FORMA_DE_PAGAMENTO, v.PARCELAS, v.IDCLIENTE FROM VENDA v "
				+ "INNER JOIN ITEMVENDA i"
				+ "ON v.IDVENDA = i.IDVENDA"
				+ "WHERE i.IDMARCA = " + relatorio.getIdMarca();
		
		if(!relatorio.getDataInicial().isEmpty() && !relatorio.getDataFinal().isEmpty()) {
			query += " AND v.DATA_VENDA::text BETWEEN '"+ relatorio.getDataInicial() +"' AND '"+ relatorio.getDataFinal() +"'";
		} else if(!relatorio.getDataInicial().isEmpty()) {
			query += " AND v.DATA_VENDA::text = '"+ relatorio.getDataInicial() +"'";
		}

		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Venda venda = new Venda();
				venda.setIdVenda(rs.getInt(1));
				venda.setDataVenda(rs.getString(7));
				venda.setValor(rs.getDouble(3));
				venda.setFormaDePagamento(rs.getString(4));
				venda.setParcelas(rs.getInt(5));
				venda.setIdCliente(rs.getInt(6));
				vendas.add(venda);
			}
		} catch (SQLException e) {
			System.out.println(
					"Erro ao executar a Query que verifica existência de Venda por marca. Erro:" + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vendas;
	}

	public ArrayList<Venda> totalPorCategoria(Relatorio relatorio) {
		ArrayList<Venda> vendas = new ArrayList<Venda>();

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;

		String query = "SELECT v.IDVENDA, to_char(\"data_venda\", 'DD/MM/YYYY'), v.VALOR, "
				+ "v.FORMA_DE_PAGAMENTO, v.PARCELAS, v.IDCLIENTE FROM VENDA v "
				+ "INNER JOIN ITEMVENDA i"
				+ "ON v.IDVENDA = i.IDVENDA"
				+ "WHERE i.IDCATEGORIA = " + relatorio.getIdCategoria();

		if(!relatorio.getDataInicial().isEmpty() && !relatorio.getDataFinal().isEmpty()) {
			query += " AND v.DATA_VENDA::text BETWEEN '"+ relatorio.getDataInicial() +"' AND '"+ relatorio.getDataFinal() +"'";
		} else if(!relatorio.getDataInicial().isEmpty()) {
			query += " AND v.DATA_VENDA::text = '"+ relatorio.getDataInicial() +"'";
		}

		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Venda venda = new Venda();
				venda.setIdVenda(rs.getInt(1));
				venda.setDataVenda(rs.getString(7));
				venda.setValor(rs.getDouble(3));
				venda.setFormaDePagamento(rs.getString(4));
				venda.setParcelas(rs.getInt(5));
				venda.setIdCliente(rs.getInt(6));
				vendas.add(venda);
			}
		} catch (SQLException e) {
			System.out.println(
					"Erro ao executar a Query que verifica existência de Venda por categoria. Erro:" + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vendas;
	}

	public ArrayList<Venda> totalPorPagamento(Relatorio relatorio) {
		ArrayList<Venda> vendas = new ArrayList<Venda>();

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;

		String query = "SELECT v.IDVENDA, to_char(\"data_venda\", 'DD/MM/YYYY'), v.VALOR, "
				+ "v.FORMA_DE_PAGAMENTO, v.PARCELAS, v.IDCLIENTE FROM VENDA v "
				+ "INNER JOIN ITEMVENDA i"
				+ "ON v.IDVENDA = i.IDVENDA"
				+ "WHERE v.FORMA_DE_PAGAMENTO = " + relatorio.getFormaDePagamento();

		if(!relatorio.getDataInicial().isEmpty() && !relatorio.getDataFinal().isEmpty()) {
			query += " AND v.DATA_VENDA::text BETWEEN '"+ relatorio.getDataInicial() +"' AND '"+ relatorio.getDataFinal() +"'";
		} else if(!relatorio.getDataInicial().isEmpty()) {
			query += " AND v.DATA_VENDA::text = '"+ relatorio.getDataInicial() +"'";
		}

		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Venda venda = new Venda();
				venda.setIdVenda(rs.getInt(1));
				venda.setDataVenda(rs.getString(7));
				venda.setValor(rs.getDouble(3));
				venda.setFormaDePagamento(rs.getString(4));
				venda.setParcelas(rs.getInt(5));
				venda.setIdCliente(rs.getInt(6));
				vendas.add(venda);
			}
		} catch (SQLException e) {
			System.out.println(
					"Erro ao executar a Query que verifica existência de Venda por categoria. Erro:" + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vendas;
	}

}
