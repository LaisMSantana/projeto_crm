CREATE TABLE CLIENTE (
	IDCLIENTE SERIAL PRIMARY KEY,
	NOME VARCHAR(150),
	CPF CHAR(11) UNIQUE,
	EMAIL VARCHAR(150) UNIQUE,
	DATA_NASCIMENTO DATE
);

CREATE TABLE PRODUTO (
	IDPRODUTO SERIAL PRIMARY KEY,
	NOME VARCHAR(150),
	CODIGO VARCHAR(50),
	MARCA VARCHAR(100),
	TIPO VARCHAR(100)
);
