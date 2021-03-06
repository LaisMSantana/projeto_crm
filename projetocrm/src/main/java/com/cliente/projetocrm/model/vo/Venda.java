package com.cliente.projetocrm.model.vo;

import java.util.ArrayList;
import java.util.Date;

public class Venda {
	
	private int idVenda;
	private int idCliente;
	private ArrayList<ItemVenda> itens;
	private String dataVenda;
	private double valor;
	private int parcelas;
	private String formaDePagamento;
	private String cliente;
	
	public Venda() {
		super();
	}

	public Venda(int idVenda, int idCliente, ArrayList<ItemVenda> itens, String dataVenda, double valor,
			int parcelas, String formaDePagamento, String cliente) {
		super();
		this.idVenda = idVenda;
		this.idCliente = idCliente;
		this.itens = itens;
		this.dataVenda = dataVenda;
		this.valor = valor;
		this.formaDePagamento = formaDePagamento;
		this.parcelas = parcelas;
		this.cliente = cliente;
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

	public ArrayList<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(ArrayList<ItemVenda> itens) {
		this.itens = itens;
	}

	public String getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(String dataVenda) {
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

	public int getParcelas() {
		return parcelas;
	}

	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}
	

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Venda [idVenda=" + idVenda + ", idCliente=" + idCliente + ", itens=" + itens + ", dataVenda="
				+ dataVenda + ", valor=" + valor + ", parcelas=" + parcelas + ", formaDePagamento=" + formaDePagamento
				+ ", cliente=" + cliente + "]";
	}

	
	
}
