package com.cliente.projetocrm.model.vo;

public class Cliente {

	private int IDCLIENTE;
	private String NOME;
	private String CPF;
	private String EMAIL;
	private String SENHA;
	
	
	public Cliente() {
		super();
	}

	public Cliente(int IDLCIENTE, String NOME, String CPF, String EMAIL, String SENHA) {
		super();
		this.IDCLIENTE = IDCLIENTE;
		this.NOME = NOME;
		this.CPF = CPF;
		this.EMAIL = EMAIL;
		this.SENHA = SENHA;
	}
	
	public int getIdCliente() {
		return IDCLIENTE;
	}

	public void setIdCliente(int IDLCIENTE) {
		this.IDCLIENTE = IDLCIENTE;
	}

	public String getNome() {
		return NOME;
	}

	public void setNome(String NOME) {
		this.NOME = NOME;
	}

	public String getCpf() {
		return CPF;
	}

	public void setCpf(String CPF) {
		this.CPF = CPF;
	}

	public String getEmail() {
		return EMAIL;
	}

	public void setEmail(String EMAIL) {
		this.EMAIL = EMAIL;
	}

	public String getSenha() {
		return SENHA;
	}

	public void setSenha(String SENHA) {
		this.SENHA = SENHA;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + IDCLIENTE + ", nome=" + NOME + ", cpf=" + CPF + ", email=" + EMAIL + ", senha="
				+ SENHA + "]";
	}
	
	
	

}