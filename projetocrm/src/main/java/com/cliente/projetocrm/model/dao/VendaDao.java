package com.cliente.projetocrm.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.cliente.projetocrm.model.vo.ItemVenda;
import com.cliente.projetocrm.model.vo.Venda;

public class VendaDao {
	
	public int cadastrarVenda(Venda venda) throws Exception {
		int novoId = 0;
		
		String sql = " INSERT INTO VENDA (DATA_VENDA, VALOR, FORMA_DE_PAGAMENTO, IDCLIENTE, PARCELAS) VALUES (?,?,?,?,?) ";
		
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		
		String dataAtual = this.getDateTime();
		Date data = new SimpleDateFormat("yyyy-MM-dd").parse(dataAtual);
		venda.setDataVenda(data);
		
		try {
			prepStmt.setDate(1, new java.sql.Date(data.getTime()));
			prepStmt.setDouble(2, venda.getValor());
			prepStmt.setString(3, venda.getFormaDePagamento());
			prepStmt.setInt(4, venda.getIdCliente());
			prepStmt.setInt(5, venda.getParcelas());
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
	
	public int cadastrarItemVenda(Venda venda) {
		int retorno = 0;
		int resultado = 0;
		int contador = 0;
		
		String sql = "";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = null;
		
		try {
			for(int i = 0; i < venda.getItens().size(); i++){
				
				venda.getItens().get(i).setIdVenda(venda.getIdVenda());
				
				sql = "INSERT INTO ITEMVENDA (IDMARCA, IDCATEGORIA, IDVENDA, QUANTIDADE, VALOR) VALUES (?,?,?,?,?)";
				
				prepStmt = Banco.getPreparedStatement(conexao, sql); 
				
				prepStmt.setInt(1, venda.getItens().get(i).getIdMarca());
				prepStmt.setInt(2, venda.getItens().get(i).getIdCategoria());
				prepStmt.setInt(3, venda.getItens().get(i).getIdVenda());
				prepStmt.setInt(4, venda.getItens().get(i).getQuantidade());
				prepStmt.setDouble(5, venda.getItens().get(i).getValor());
				
				resultado = prepStmt.executeUpdate();
				if(resultado == 1){
					contador++;
				}
			}
			ResultSet generatedKeys = prepStmt.getGeneratedKeys();
			if((contador == venda.getItens().size()) && (generatedKeys.next())){
				retorno = generatedKeys.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar Venda com Itens. Causa: \n: " + e.getMessage());
		} finally {
			Banco.closeStatement(prepStmt);
			Banco.closeConnection(conexao);
		}
		
		return retorno;
	}
	
	public ArrayList<Venda> listarTodos() throws Exception {
		ArrayList<Venda> vendas = new ArrayList<Venda>();

		String sql = "SELECT c.NOME, v.IDVENDA, v.VALOR, v.FORMA_DE_PAGAMENTO, v.PARCELAS, v.DATA_VENDA FROM VENDA v "
				+ "INNER JOIN CLIENTE c "
				+ "ON v.IDCLIENTE = c.IDCLIENTE "
				+ "ORDER BY IDVENDA DESC";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				//Date data = new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString(6));
				
				Venda venda = new Venda();
				venda.setCliente(rs.getString(1));
				venda.setIdVenda(rs.getInt(2));
				venda.setValor(rs.getDouble(3));
				venda.setFormaDePagamento(rs.getString(4));
				venda.setParcelas(rs.getInt(5));
				//venda.setDataVenda(rs.getDate(6));
				vendas.add(venda);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao listar Venda. Causa: \n: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}
		return vendas;
	}

	public ArrayList<Venda> listarPorFiltro(String vendaFiltro) throws Exception {
		ArrayList<Venda> vendas = new ArrayList<Venda>();
		
		String sql = "SELECT * FROM VENDA v "
				+ "INNER JOIN CLIENTE c "
				+ "ON v.IDCLIENTE = c.IDCLIENTE "
				+ "WHERE LOWER(c.NOME) LIKE LOWER('%" + vendaFiltro + "%') OR "
				+ "LOWER(v.FORMA_DE_PAGAMENTO) LIKE LOWER('%" + vendaFiltro + "%') OR "
				+ "v.VALOR::text LIKE '%" + vendaFiltro + "%' OR "
				+ "v.DATA_VENDA::text LIKE '%" + vendaFiltro + "%'";
		
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Venda venda = new Venda();
				venda.setIdVenda(rs.getInt(1));
				//venda.setDataVenda(rs.getDate(2));
				venda.setValor(rs.getDouble(3));
				venda.setFormaDePagamento(rs.getString(4));
				venda.setParcelas(rs.getInt(5));
				venda.setIdCliente(rs.getInt(6));
				venda.setCliente(rs.getString(8));
				vendas.add(venda);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao listar Venda por filtro. Causa: \n: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}
		return vendas;
	}

	public boolean atualizarVenda(Venda venda) throws Exception {
		boolean sucessoUpdate = false;

		String sql = " UPDATE VENDA SET VALOR = ?, FORMA_DE_PAGAMENTO = ?, IDCLIENTE = ?, PARCELAS = ? WHERE IDVENDA = "
				+ venda.getIdVenda();
		
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);

		try {
			prepStmt.setDouble(1, venda.getValor());
			prepStmt.setString(2, venda.getFormaDePagamento());
			prepStmt.setInt(3, venda.getIdCliente());
			prepStmt.setInt(4, venda.getParcelas());

			int codigoRetorno = prepStmt.executeUpdate();

			if (codigoRetorno == 1) {
				sucessoUpdate = true;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Venda. Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}

		return sucessoUpdate;
	}
	
	public boolean atualizarItemVenda(Venda venda) throws Exception {
		boolean sucessoUpdate = false;
		int retorno = 0;
		int resultado = 0;
		int contador = 0;
		
		String sql = "";
		
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = null;

		try {
			for(int i = 0; i < venda.getItens().size(); i++){
				
				venda.getItens().get(i).setIdVenda(venda.getIdVenda());
				
				sql = " UPDATE ITEMVENDA SET IDMARCA = ?, IDCATEGORIA = ?, QUANTIDADE = ?, VALOR = ? WHERE IDVENDA = "
						+ venda.getIdVenda();
				
				prepStmt = Banco.getPreparedStatement(conexao, sql); 
				
				prepStmt.setInt(1, venda.getItens().get(i).getIdMarca());
				prepStmt.setInt(2, venda.getItens().get(i).getIdCategoria());
				prepStmt.setInt(3, venda.getItens().get(i).getQuantidade());
				prepStmt.setDouble(4, venda.getItens().get(i).getValor());
				
				resultado = prepStmt.executeUpdate();
				if(resultado == 1){
					contador++;
				}
			}
			if(contador == venda.getItens().size()){
				sucessoUpdate = true;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar itens Venda. Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}

		return sucessoUpdate;
	}

	public Venda encontrarPorId(int id) {
		Venda venda = new Venda();

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;

		String query = "SELECT * FROM VENDA WHERE IDVENDA = " + id;

		try {
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				venda.setIdVenda(rs.getInt(1));
				//venda.setDataVenda(rs.getDate(2));
				venda.setValor(rs.getDouble(3));
				venda.setFormaDePagamento(rs.getString(4));
				venda.setParcelas(rs.getInt(5));
				venda.setIdCliente(rs.getInt(6));
			}
		} catch (SQLException e) {
			System.out.println(
					"Erro ao executar a Query que verifica existência de Venda por ID. Erro:" + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return venda;
	}
	
	public ArrayList<ItemVenda> encontrarPorIdItem(int id) {
		ArrayList<ItemVenda> itens = new ArrayList<ItemVenda>();
		

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;

		String query = "SELECT * FROM ITEMVENDA v  WHERE v.IDVENDA = " + id;

		try {
			rs = stmt.executeQuery(query);
				while (rs.next()) {
					ItemVenda itemVenda = new ItemVenda();
					itemVenda.setIdItemVenda(rs.getInt(1));
					itemVenda.setIdVenda(rs.getInt(2));
					itemVenda.setIdMarca(rs.getInt(3));
					itemVenda.setIdCategoria(rs.getInt(4));
					itemVenda.setQuantidade(rs.getInt(5));
					itemVenda.setValor(rs.getInt(6));
					itens.add(itemVenda);
				}
			
		} catch (SQLException e) {
			System.out.println(
					"Erro ao executar a Query que verifica existência de Item Venda por ID. Erro:" + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return itens;
	}
	
	private String getDateTime() {
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = new Date();
	    return dateFormat.format(date);
	}

}
