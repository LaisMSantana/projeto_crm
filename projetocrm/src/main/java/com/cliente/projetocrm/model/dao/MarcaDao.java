package com.cliente.projetocrm.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cliente.projetocrm.model.vo.Marca;

public class MarcaDao {

	public int salvarMarca(Marca marca) {
		int novoId = 0;

		String sql = " INSERT INTO MARCA (MARCA) VALUES (?) ";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);

		try {
			prepStmt.setString(1, marca.getMarca());
			prepStmt.execute();

			ResultSet generatedKeys = prepStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				novoId = generatedKeys.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar Marca. Causa: \n: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}
		return novoId;
	}

	public ArrayList<Marca> listarTodos() {
		ArrayList<Marca> marcas = new ArrayList<Marca>();

		String sql = "SELECT * FROM MARCA";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Marca marca = new Marca();
				marca.setIdMarca(rs.getInt(1));
				marca.setMarca(rs.getString(2));
				marcas.add(marca);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao listar marcas. Causa: \n: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}
		return marcas;
	}

	public ArrayList<Marca> listarPorFiltro(String marcaFiltro) {
		ArrayList<Marca> marcas = new ArrayList<Marca>();
		
		String sql = "SELECT * FROM MARCA WHERE MARCA LIKE '%" + marcaFiltro + "%'";
		
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Marca marca = new Marca();
				marca.setIdMarca(rs.getInt(1));
				marca.setMarca(rs.getString(2));
				marcas.add(marca);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao listar Marca por filtro. Causa: \n: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}
		return marcas;
	}

	public boolean atualizar (Marca marca) {
		boolean sucessoUpdate = false;

		String sql = " UPDATE MARCA SET MARCA = ? WHERE IDMARCA = " + marca.getIdMarca();
		
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);

		try {
			prepStmt.setString(1, marca.getMarca());

			int codigoRetorno = prepStmt.executeUpdate();

			if (codigoRetorno == 1) {
				sucessoUpdate = true;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Marca. Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}

		return sucessoUpdate;
	}

	public Marca encontrarPorId(int id) {
		Marca marca = new Marca();

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;

		String query = "SELECT * FROM MARCA WHERE IDMARCA = " + id;
		try {
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				marca.setIdMarca(rs.getInt(1));
				marca.setMarca(rs.getString(2));
			}
		} catch (SQLException e) {
			System.out.println(
					"Erro ao executar a Query que verifica existência de Marca por ID. Erro:" + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return marca;
	}

	
}

