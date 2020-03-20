package com.cliente.projetocrm.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cliente.projetocrm.model.vo.Produto;

public class ProdutoDao {

	public int salvarProduto(Produto produto) {
		int novoId = 0;

		String sql = " INSERT INTO PRODUTO (NOME, CODIGO, MARCA, TIPO) VALUES (?,?,?,?) ";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);

		try {
			prepStmt.setString(1, produto.getNome());
			prepStmt.setString(2, produto.getCodigo());
			prepStmt.setString(3, produto.getMarca());
			prepStmt.setString(4,produto.getTipo());
			prepStmt.execute();

			ResultSet generatedKeys = prepStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				novoId = generatedKeys.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar Produto. Causa: \n: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}
		return novoId;
	}

	public ArrayList<Produto> listarTodos() {
		ArrayList<Produto> produtos = new ArrayList<Produto>();

		String sql = "SELECT * FROM PRODUTO";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Produto produto = new Produto();
				produto.setIdProduto(rs.getInt(1));
				produto.setNome(rs.getString(2));
				produto.setCodigo(rs.getString(3));
				produto.setMarca(rs.getString(4));
				produto.setTipo(rs.getString(5));
				produtos.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao listar Produtos. Causa: \n: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}
		return produtos;
	}

	public ArrayList<Produto> listarPorFiltro(String produtoFiltro) {
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		
		String sql = "SELECT * FROM PRODUTO P WHERE p.NOME LIKE '%" + produtoFiltro
				+ "%' OR p.CODIGO LIKE '%" + produtoFiltro + "%' OR p.MARCA LIKE '%" + produtoFiltro + "%'OR p.TIPO LIKE '%" + produtoFiltro + "%'";
		
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Produto produto = new Produto();
				produto.setIdProduto(rs.getInt(1));
				produto.setNome(rs.getString(2));
				produto.setCodigo(rs.getString(3));
				produto.setMarca(rs.getString(4));
				produto.setTipo(rs.getString(5));
				produtos.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao listar Produtos por filtro. Causa: \n: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}
		return produtos;
	}

	public boolean atualizar (Produto produto) {
		boolean sucessoUpdate = false;

		String sql = " UPDATE PRODUTO SET NOME = ? , CODIGO = ? , MARCA = ? , TIPO = ? WHERE IDPRODUTO = "
				+ produto.getIdProduto();
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);

		try {
			prepStmt.setString(1, produto.getNome());
			prepStmt.setString(2, produto.getCodigo());
			prepStmt.setString(3, produto.getMarca());
			prepStmt.setString(4,produto.getTipo());

			int codigoRetorno = prepStmt.executeUpdate(sql);

			if (codigoRetorno == 1) {
				sucessoUpdate = true;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Produto. Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}

		return sucessoUpdate;
	}

	public Produto encontrarPorId(int id) {
		Produto produto = new Produto();

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;

		String query = "SELECT * FROM PRODUTO WHERE IDPRODUTO = " + id;
		try {
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				produto.setIdProduto(rs.getInt(1));
				produto.setNome(rs.getString(2));
				produto.setCodigo(rs.getString(3));
				produto.setMarca(rs.getString(4));
				produto.setTipo(rs.getString(5));
			}
		} catch (SQLException e) {
			System.out.println(
					"Erro ao executar a Query que verifica existência de Produto por ID. Erro:" + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return produto;
	}

	
}

