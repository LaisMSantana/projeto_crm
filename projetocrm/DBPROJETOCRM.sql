CREATE TABLE CLIENTE (
	IDCLIENTE SERIAL PRIMARY KEY,
	NOME VARCHAR(150),
	CPF CHAR(11) UNIQUE,
	EMAIL VARCHAR(150) UNIQUE,
	DATA_NASCIMENTO DATE
);

CREATE TABLE MARCA (
	IDMARCA SERIAL PRIMARY KEY,
	MARCA VARCHAR(150)
);

CREATE TABLE CATEGORIA (
	IDCATEGORIA SERIAL PRIMARY KEY,
	CATEGORIA VARCHAR(150)
);

CREATE TABLE VENDA (
	IDVENDA SERIAL PRIMARY KEY,
	DATA_VENDA DATE,
	VALOR FLOAT,
	FORMA_DE_PAGAMENTO VARCHAR(150),
	PARCELAS INTEGER,
	IDCLIENTE INTEGER,
	FOREIGN KEY(IDCLIENTE) REFERENCES CLIENTE(IDCLIENTE)
);

CREATE TABLE ITEMVENDA (
	IDITEMVENDA SERIAL PRIMARY KEY,
	IDVENDA INTEGER,
	IDMARCA INTEGER,
	IDCATEGORIA INTEGER,
	QUANTIDADE INTEGER,
	VALOR FLOAT,
	FOREIGN KEY(IDVENDA) REFERENCES VENDA(IDVENDA),
	FOREIGN KEY(IDMARCA) REFERENCES MARCA(IDMARCA),
	FOREIGN KEY(IDCATEGORIA) REFERENCES CATEGORIA(IDCATEGORIA)
);

select * from ITEMVENDA WHERE IDVENDA = 3


