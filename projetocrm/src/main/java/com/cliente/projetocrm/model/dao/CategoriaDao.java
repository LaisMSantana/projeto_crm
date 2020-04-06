package com.cliente.projetocrm.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cliente.projetocrm.model.vo.Categoria;

public class CategoriaDao {

	public int salvarCategoria(Categoria categoria) {
		int novoId = 0;

		String sql = " INSERT INTO CATEGORIA (CATEGORIA) VALUES (?) ";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);

		try {
			prepStmt.setString(1, categoria.getCategoria());
			prepStmt.execute();

			ResultSet generatedKeys = prepStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				novoId = generatedKeys.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar Categoria. Causa: \n: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}
		return novoId;
	}

	public ArrayList<Categoria> listarTodos() {
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();

		String sql = "SELECT * FROM CATEGORIA";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rs.getInt(1));
				categoria.setCategoria(rs.getString(2));
				categorias.add(categoria);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao listar Categorias. Causa: \n: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}
		return categorias;
	}

	public ArrayList<Categoria> listarPorFiltro(String categoriaFiltro) {
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		
		String sql = "SELECT * FROM CATEGORIA WHERE CATEGORIA LIKE '%" + categoriaFiltro + "%'";
		
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rs.getInt(1));
				categoria.setCategoria(rs.getString(2));
				categorias.add(categoria);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao listar Categoria por filtro. Causa: \n: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}
		return categorias;
	}

	public boolean atualizar (Categoria categoria) {
		boolean sucessoUpdate = false;

		String sql = " UPDATE CATEGORIA SET CATEGORIA = ? WHERE IDCATEGORIA = " + categoria.getIdCategoria();
		
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);

		try {
			prepStmt.setString(1, categoria.getCategoria());

			int codigoRetorno = prepStmt.executeUpdate();

			if (codigoRetorno == 1) {
				sucessoUpdate = true;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Categoria. Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}

		return sucessoUpdate;
	}

	public Categoria encontrarPorId(int id) {
		Categoria categoria = new Categoria();

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;

		String query = "SELECT * FROM CATEGORIA WHERE IDCATEGORIA = " + id;
		try {
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				categoria.setIdCategoria(rs.getInt(1));
				categoria.setCategoria(rs.getString(2));
			}
		} catch (SQLException e) {
			System.out.println(
					"Erro ao executar a Query que verifica existência de Categoria por ID. Erro:" + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return categoria;
	}

	
}

