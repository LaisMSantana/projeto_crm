package com.cliente.projetocrm.model.vo;

import java.util.ArrayList;
import java.util.Date;

public class Venda {
	
	private int idVenda;
	private int idCliente;
	private ArrayList<ItemProduto> produtos;
	private Date dataVenda;
	private double valor;
	private String formaDePagamento;
	
	public Venda() {
		super();
	}

	public Venda(int idVenda, int idCliente, ArrayList<ItemProduto> produtos, Date dataVenda, double valor,
			String formaDePagamento) {
		super();
		this.idVenda = idVenda;
		this.idCliente = idCliente;
		this.produtos = produtos;
		this.dataVenda = dataVenda;
		this.valor = valor;
		this.formaDePagamento = formaDePagamento;
	}


	public int getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public ArrayList<ItemProduto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<ItemProduto> produtos) {
		this.produtos = produtos;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(String formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	@Override
	public String toString() {
		return "Venda [idVenda=" + idVenda + ", cliente=" + idCliente + ", dataVenda="
				+ dataVenda + ", valor=" + valor + ", formaDePagamento=" + formaDePagamento + "]";
	}
	
}
