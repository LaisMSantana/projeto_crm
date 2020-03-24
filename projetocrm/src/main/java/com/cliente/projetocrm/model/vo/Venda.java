package com.cliente.projetocrm.model.vo;

import java.util.ArrayList;
import java.util.Date;

public class Venda {
	
	private int idVenda;
	private Cliente cliente;
	private ArrayList<ItemProduto> produtos;
	private Date dataVenda;
	private Double valor;
	private String formaDePagamento;
	
	public Venda() {
		super();
	}

	public Venda(int idVenda, Cliente cliente, ArrayList<ItemProduto> produtos, Date dataVenda, Double valor,
			String formaDePagamento) {
		super();
		this.idVenda = idVenda;
		this.cliente = cliente;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
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
		return "Venda [idVenda=" + idVenda + ", cliente=" + cliente + ", produtos=" + produtos + ", dataVenda="
				+ dataVenda + ", valor=" + valor + ", formaDePagamento=" + formaDePagamento + "]";
	}
	
}
