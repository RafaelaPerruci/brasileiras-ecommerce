CREATE TABLE clientes (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nome VARCHAR(100) NOT NULL,
                          cpf VARCHAR(14) UNIQUE NOT NULL,
                          email VARCHAR(100) NOT NULL,
                          telefone VARCHAR(20),
                          endereco_entrega VARCHAR(300)
);

CREATE TABLE fornecedores (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              razao_social VARCHAR(100) NOT NULL,
                              cnpj VARCHAR(18) UNIQUE NOT NULL,
                              email VARCHAR(100),
                              telefone VARCHAR(20),
                              endereco VARCHAR(300)
);

CREATE TABLE produtos (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          codigo VARCHAR(50) NOT NULL,
                          descricao VARCHAR(255) NOT NULL,
                          codigo_barras VARCHAR(13) UNIQUE NOT NULL,
                          valor_compra DECIMAL(10, 2) NOT NULL,
                          valor_venda DECIMAL(10, 2) NOT NULL,
                          estoque INT NOT NULL,
                          fornecedor_id BIGINT,
                          FOREIGN KEY (fornecedor_id) REFERENCES fornecedores(id)
);

CREATE TABLE vendas (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        numero_venda VARCHAR(100) UNIQUE NOT NULL,
                        data_venda DATE NOT NULL,
                        valor_total DECIMAL(10, 2) NOT NULL,
                        cliente_id BIGINT NOT NULL,
                        endereco_entrega VARCHAR(300),
                        metodo_pagamento VARCHAR(20),
                        FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

CREATE TABLE itens_venda (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             venda_id BIGINT NOT NULL,
                             produto_id BIGINT NOT NULL,
                             quantidade INT NOT NULL,
                             preco_unitario DECIMAL(10, 2) NOT NULL,
                             subtotal DECIMAL(10, 2) NOT NULL,
                             FOREIGN KEY (venda_id) REFERENCES vendas(id),
                             FOREIGN KEY (produto_id) REFERENCES produtos(id)
);
