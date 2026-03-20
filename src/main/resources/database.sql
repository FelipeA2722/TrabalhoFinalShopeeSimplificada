CREATE DATABASE IF NOT EXISTS shopee_db;
USE shopee_db;
-- Tabela de usuários
CREATE TABLE usuario (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         nome VARCHAR(100) NOT NULL,
                         email VARCHAR(100) UNIQUE NOT NULL,
                         senha VARCHAR(255) NOT NULL,
                         tipo ENUM('cliente', 'vendedor') NOT NULL,
                         data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         ativo BOOLEAN DEFAULT TRUE
);
-- Tabela de clientes (dados específicos)
CREATE TABLE cliente (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         usuario_id INT UNIQUE,
                         cpf VARCHAR(14) UNIQUE,
                         telefone VARCHAR(20),
                         endereco TEXT,
                         data_nascimento DATE,
                         FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);
-- Tabela de vendedores
CREATE TABLE vendedor (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          usuario_id INT UNIQUE,
                          cnpj VARCHAR(18) UNIQUE,
                          razao_social VARCHAR(200),
                          telefone VARCHAR(20),
                          avaliacao DECIMAL(3,2) DEFAULT 5.0,
                          FOREIGN KEY (usuario_id) REFERENCES usuario(id)

);
-- Tabela de produtos
CREATE TABLE produto (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         vendedor_id INT NOT NULL,
                         nome VARCHAR(200) NOT NULL,
                         descricao TEXT,
                         categoria VARCHAR(100),
                         preco DECIMAL(10,2) NOT NULL,
                         quantidade_estoque INT DEFAULT 0,
                         imagem_url VARCHAR(500),
                         data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         ativo BOOLEAN DEFAULT TRUE,
                         FOREIGN KEY (vendedor_id) REFERENCES vendedor(id)
);
-- Tabela de pedidos
CREATE TABLE pedido (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        cliente_id INT NOT NULL,
                        data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        status ENUM('aguardando', 'pago', 'enviado', 'entregue', 'cancelado') DEFAULT
'aguardando',
                        valor_total DECIMAL(10,2) NOT NULL,
                        metodo_pagamento VARCHAR(50),
                        endereco_entrega TEXT,
                        FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);
-- Tabela de itens do pedido
CREATE TABLE item_pedido (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             pedido_id INT NOT NULL,
                             produto_id INT NOT NULL,
                             quantidade INT NOT NULL,
                             preco_unitario DECIMAL(10,2) NOT NULL,
                             FOREIGN KEY (pedido_id) REFERENCES pedido(id),
                             FOREIGN KEY (produto_id) REFERENCES produto(id)
);
-- Tabela de logs
CREATE TABLE log_sistema (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             nivel ENUM('INFO', 'WARNING', 'ERROR') NOT NULL,
                             mensagem TEXT NOT NULL,
                             usuario_id INT,
                             FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);