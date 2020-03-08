package com.cliente.projetocrm.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cliente.projetocrm.model.vo.Cliente;

public class ClienteDao {
	
	public int salvarCliente(Cliente cliente){
		int novoId = 0;

		String sql = " INSERT INTO CLIENTE (NOME," + "CPF," + "EMAIL," + "SENHA) VALUES (?,?,?,?) ";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);

		try {
			prepStmt.setString(1, cliente.getNome());
			prepStmt.setString(2, cliente.getCpf());
			prepStmt.setString(3, cliente.getEmail());
			prepStmt.setString(4, cliente.getSenha());
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
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setIdCliente(rs.getInt(1));
				cliente.setNome(rs.getString(2));
				cliente.setCpf(rs.getString(3));
				cliente.setEmail(rs.getString(4));
				cliente.setSenha(rs.getString(5));
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

}
