--INSERT INTO public.produto(descricao, preco_unitario) VALUES ('Arroz Tio João', '15.60');
--INSERT INTO public.produto(descricao, preco_unitario) VALUES ('Feijão Carioca', '9.30');
--INSERT INTO public.produto(descricao, preco_unitario) VALUES ('Erva Charrua', '11.85');
--INSERT INTO public.produto(descricao, preco_unitario) VALUES ('Açucar Cristal', '5.98');
--INSERT INTO public.produto(descricao, preco_unitario) VALUES ('Laranja Suco', '4.40');

INSERT INTO public.produto(descricao, preco_compra, preco_venda) VALUES ('Arroz', '10.50', '16.70');
INSERT INTO public.produto(descricao, preco_compra, preco_venda) VALUES ('Feijão', '20.50', '30.70');

INSERT INTO public.forma_pagamento(descricao) VALUES ('Cartão Visa');

INSERT INTO public.cliente(cpf, nome, rg) VALUES ('99999999999', 'João da Silva', '748145481581');
INSERT INTO public.cliente(cpf, nome, rg) VALUES ('88888888888', 'Maria Joana', '87878787878');

INSERT INTO public.pedido(id_cliente, id_forma_pagamento) VALUES (1, 1);
INSERT INTO public.item_pedido(quantidade, valor_item, id_pedido, id_produto) VALUES ('10', '20', 1, 1);
INSERT INTO public.item_pedido(quantidade, valor_item, id_pedido, id_produto) VALUES ('10', '20', 1, 2);


-- INSERT INTO public.usuario(data_atualizacao, data_cadastro, login, nome, senha) VALUES (timezone('utc', CURRENT_TIMESTAMP(0)),timezone('utc', CURRENT_TIMESTAMP(0)), 'brunomoura', 'Bruno Moura', '$2a$10$k8TpIS86pFfdgMSEraMtSOc9IXV95VnoqIxkIkkRz9a/PLpyJC0mm');
-- INSERT INTO public.usuario(data_atualizacao, data_cadastro, login, nome, senha) VALUES (timezone('utc', CURRENT_TIMESTAMP(0)),timezone('utc', CURRENT_TIMESTAMP(0)), 'rodrigoferreira', 'Rodrigo Ferreira', '907080');
-- INSERT INTO public.usuario(data_atualizacao, data_cadastro, login, nome, senha) VALUES (timezone('utc', CURRENT_TIMESTAMP(0)),timezone('utc', CURRENT_TIMESTAMP(0)), 'rafaelbastos', 'Rafael Bastos', '302010');
-- INSERT INTO public.usuario(data_atualizacao, data_cadastro, login, nome, senha) VALUES (timezone('utc', CURRENT_TIMESTAMP(0)),timezone('utc', CURRENT_TIMESTAMP(0)), 'danielamartins', 'Daniela Martins', '987465');

-- INSERT INTO public.telefone(numero, tipo, id_usuario) VALUES ('9999999999', 'Celular', 1);
-- INSERT INTO public.telefone(numero, tipo, id_usuario) VALUES ('5522552255', 'Casa', 4);

INSERT INTO public.role(nome_role) VALUES ('ROLE_ADMIN');
INSERT INTO public.role(nome_role) VALUES ('ROLE_CAIXA');

INSERT INTO public.usuario(login, nome, senha) VALUES ('brunomoura', 'Bruno Moura', '$2a$10$8okjbHGqFzdFAX.XtVBO6eEZWi4F7W285Yy6ZsQC9ZyecUB.gO91C'); -- 102030
INSERT INTO public.usuarios_role(usuario_id, role_id) VALUES (1, 1);

INSERT INTO public.usuario(login, nome, senha) VALUES ('danielamartins', 'Daniela Martins', '$2a$10$umA2cp3zGaSMPAGreI3PKePL9eZaDcravDUIONL./dbk1g.LQS7hW'); -- 203040
INSERT INTO public.usuarios_role(usuario_id, role_id) VALUES (2, 2);








