package com.cliente.projetocrm.model.vo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Venda {
	
	private int idVenda;
	private String idCliente;
	private ArrayList<ItemProduto> produtos;
	private LocalDate dataVenda;
	private String valor;
	private String formaDePagamento;
	
	public Venda() {
		super();
	}

	public Venda(int idVenda, String idCliente, ArrayList<ItemProduto> produtos, LocalDate dataVenda, String valor,
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

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public ArrayList<ItemProduto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<ItemProduto> produtos) {
		this.produtos = produtos;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
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
