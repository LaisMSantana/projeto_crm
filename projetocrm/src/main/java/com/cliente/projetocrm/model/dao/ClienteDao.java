package com.cliente.projetocrm.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cliente.projetocrm.model.vo.Cliente;

public class ClienteDao {

	public int salvarCliente(Cliente cliente) {
		int novoId = 0;

		String sql = " INSERT INTO CLIENTE (NOME, CPF, EMAIL, DATA_NASCIMENTO) VALUES (?,?,?,?) ";
		
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);

		try {
			
			
			prepStmt.setString(1, cliente.getNome());
			prepStmt.setString(2, cliente.getCpf());
			prepStmt.setString(3, cliente.getEmail());
			prepStmt.setString(4,cliente.getDataDeNascimento());
			prepStmt.execute();

			ResultSet generatedKeys = prepStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				novoId = generatedKeys.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Cliente. Causa: \n: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}
		return novoId;
	}

	public ArrayList<Cliente> listarTodos() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		String sql = "SELECT * FROM CLIENTE";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setIdCliente(rs.getInt(1));
				cliente.setNome(rs.getString(2));
				cliente.setCpf(rs.getString(3));
				cliente.setEmail(rs.getString(4));
				cliente.setDataDeNascimento(rs.getString(5));
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao listar Clientes. Causa: \n: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}
		return clientes;
	}

	public ArrayList<Cliente> listarPorFiltro(String clienteFiltro) {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		String sql = "SELECT * FROM CLIENTE c WHERE c.NOME LIKE '%" + clienteFiltro
				+ "%' OR c.CPF LIKE '%" + clienteFiltro + "%' OR c.EMAIL LIKE '%" + clienteFiltro + "%'";
		
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setIdCliente(rs.getInt(1));
				cliente.setNome(rs.getString(2));
				cliente.setCpf(rs.getString(3));
				cliente.setEmail(rs.getString(4));
				cliente.setDataDeNascimento(rs.getString(5));
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao listar Clientes por filtro. Causa: \n: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}
		return clientes;
	}

	public boolean atualizar(Cliente cliente) {
		boolean sucessoUpdate = false;

		String sql = " UPDATE CLIENTE SET NOME = ? , CPF = ? , EMAIL = ? , DATA_NASCIMENTO = ? WHERE IDCLIENTE = "
				+ cliente.getIdCliente();
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);

		try {
			prepStmt.setString(1, cliente.getNome());
			prepStmt.setString(2, cliente.getCpf());
			prepStmt.setString(3, cliente.getEmail());
			prepStmt.setString(4,cliente.getDataDeNascimento());

			int codigoRetorno = prepStmt.executeUpdate(sql);

			if (codigoRetorno == 1) {
				sucessoUpdate = true;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Cliente. Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}

		return sucessoUpdate;
	}

	public Cliente encontrarPorId(int id) {
		Cliente cliente = new Cliente();

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;

		String query = "SELECT * FROM CLIENTE WHERE IDCLIENTE = " + id;
		try {
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				cliente.setIdCliente(rs.getInt(1));
				cliente.setNome(rs.getString(2));
				cliente.setCpf(rs.getString(3));
				cliente.setEmail(rs.getString(4));
				cliente.setDataDeNascimento(rs.getString(5));
			}
		} catch (SQLException e) {
			System.out.println(
					"Erro ao executar a Query que verifica existência de Cliente por ID. Erro:" + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return cliente;
	}

	
}

