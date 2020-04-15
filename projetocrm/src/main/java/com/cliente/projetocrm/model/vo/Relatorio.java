package com.cliente.projetocrm.model.vo;

public class Relatorio {
	
	private String dataInicial;
	private String dataFinal;
	private int idCategoria;
	private int idMarca;
	private String formaDePagamento;
	
	
	public Relatorio() {
		super();
	}
	
	public Relatorio(String dataInicial, String dataFinal, int idCategoria, int idMarca, String formaDePagamento) {
		super();
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.idCategoria = idCategoria;
		this.idMarca = idMarca;
		this.formaDePagamento = formaDePagamento;
	}
	public String getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}
	public String getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public int getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	public String getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(String formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	@Override
	public String toString() {
		return "Relatorio [dataInicial=" + dataInicial + ", dataFinal=" + dataFinal + ", idCategoria=" + idCategoria
				+ ", idMarca=" + idMarca + ", formaDePagamento=" + formaDePagamento + "]";
	}
	
	

}
