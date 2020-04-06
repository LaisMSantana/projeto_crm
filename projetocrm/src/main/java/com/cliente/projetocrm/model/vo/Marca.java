package com.cliente.projetocrm.model.vo;

public class Marca {
	
	private int idMarca;
	private String marca;

	public Marca() {
		super();
	}

	public Marca(int idMarca, String marca) {
		super();
		this.idMarca = idMarca;
		this.marca = marca;
	}

	public int getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}	

	@Override
	public String toString() {
		return "Marca [marca=" + marca + "]";
	}

}
