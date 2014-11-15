CREATE TABLE categoria (
  cod_categoria int(11) NOT NULL AUTO_INCREMENT,
  descricao varchar(50) DEFAULT NULL,
  PRIMARY KEY (cod_categoria)
);

CREATE TABLE cliente (
  cod_cliente int(11) NOT NULL AUTO_INCREMENT,
  nome varchar(100) DEFAULT NULL,
  telefone varchar(50) DEFAULT NULL,
  celular varchar(50) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  PRIMARY KEY (cod_cliente)
);

CREATE TABLE endereco (
  cod_cliente int(11) NOT NULL,
  rua varchar(100) DEFAULT NULL,
  numero int(11) DEFAULT NULL,
  bairro varchar(100) DEFAULT NULL,
  cidade varchar(100) DEFAULT NULL,
  estado char(2) DEFAULT NULL,
  cep varchar(10) DEFAULT NULL,
  complemento text,
  PRIMARY KEY (cod_cliente),
  CONSTRAINT fk_endereco_cod_cliente 
			FOREIGN KEY (cod_cliente) 
			REFERENCES cliente (cod_cliente) 
			ON DELETE NO ACTION 
			ON UPDATE CASCADE
);

CREATE TABLE filme (
  cod_filme int(11) NOT NULL AUTO_INCREMENT,
  cod_categoria int(11) NOT NULL,
  descricao varchar(50) DEFAULT NULL,
  ano date DEFAULT NULL,
  PRIMARY KEY (cod_filme),
  KEY fk_filme_cod_categoria (cod_categoria),
  CONSTRAINT fk_filme_cod_categoria 
			FOREIGN KEY (cod_categoria) 
			REFERENCES categoria (cod_categoria) 
			ON DELETE NO ACTION 
			ON UPDATE CASCADE
);

CREATE TABLE locacao (
  cod_locacao int(11) NOT NULL AUTO_INCREMENT,
  cod_cliente int(11) NOT NULL,
  cod_midia int(11) NOT NULL,
  data_emprestimo date DEFAULT NULL,
  hora_emprestimo time DEFAULT NULL,
  data_devolucao date DEFAULT NULL,
  obs text,
  PRIMARY KEY (cod_locacao,cod_cliente,cod_midia),
  KEY fk_locacao_cod_cliente (cod_cliente),
  KEY fk_locacao_cod_midia (cod_midia),
  CONSTRAINT fk_locacao_cod_cliente 
			FOREIGN KEY (cod_cliente) 
			REFERENCES cliente (cod_cliente) 
			ON DELETE NO ACTION 
			ON UPDATE CASCADE,
  CONSTRAINT fk_locacao_cod_midia 
			FOREIGN KEY (cod_midia) 
			REFERENCES midia (cod_midia) 
			ON DELETE NO ACTION 
			ON UPDATE CASCADE
);

CREATE TABLE midia (
  cod_midia int(11) NOT NULL AUTO_INCREMENT,
  cod_filme int(11) NOT NULL,
  inutilizada char(1) DEFAULT NULL,
  PRIMARY KEY (cod_midia),
  KEY fk_midia_cod_filme (cod_filme),
  CONSTRAINT fk_midia_cod_filme 
			FOREIGN KEY (cod_filme) 
			REFERENCES filme (cod_filme) 
			ON DELETE NO ACTION 
			ON UPDATE CASCADE
);