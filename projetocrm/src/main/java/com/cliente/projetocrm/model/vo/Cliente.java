package com.cliente.projetocrm.model.vo;

import java.util.Date;

public class Cliente {

	private int idCliente;
	private String nome;
	private String cpf;
	private String email;
	private String dataDeNascimento;

	public Cliente() {
		super();
	}

	public Cliente(int idCliente, String nome, String cpf, String email, String dataDeNascimento) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.dataDeNascimento = dataDeNascimento;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email
				+ ", dataDeNascimento=" + dataDeNascimento + "]";
	}

}