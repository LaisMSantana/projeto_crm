package com.cliente.projetocrm.model.vo;

public class Produto {
	
	private int idProduto;
	private String nome;
	private String codigo;
	private String marca;
	private String tipo;
	
	public Produto() {
		super();
	}
	
	public Produto(int idProduto, String nome, String codigo, String marca, String tipo) {
		super();
		this.idProduto = idProduto;
		this.nome = nome;
		this.codigo = codigo;
		this.marca = marca;
		this.tipo = tipo;
	}
	
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Produto [idProduto=" + idProduto + ", nome=" + nome + ", codigo=" + codigo + ", marca=" + marca
				+ ", tipo=" + tipo + "]";
	}
	
	
	

}
