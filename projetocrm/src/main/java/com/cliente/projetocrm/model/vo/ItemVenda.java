package com.cliente.projetocrm.model.vo;

public class ItemVenda {
	
	private int idItemVenda;
	private int idMarca;
	private int idCategoria;
	private int idVenda;
	private int quantidade;
	private double valor;
	
	public ItemVenda() {
		super();
	}
	
	public ItemVenda(int idItemVenda, int idMarca, int idCategoria, int idVenda, int quantidade, double valor) {
		super();
		this.idItemVenda = idItemVenda;
		this.idMarca = idMarca;
		this.idCategoria = idCategoria;
		this.idVenda = idVenda;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public int getIdItemVenda() {
		return idItemVenda;
	}

	public void setIdItemVenda(int idItemVenda) {
		this.idItemVenda = idItemVenda;
	}

	public int getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "ItemVenda [idItemVenda=" + idItemVenda + ", idMarca=" + idMarca + ", idCategoria=" + idCategoria
				+ ", idVenda=" + idVenda + ", quantidade=" + quantidade + ", valor=" + valor + "]";
	}
}
