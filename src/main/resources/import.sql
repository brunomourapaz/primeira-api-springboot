INSERT INTO public.produto(descricao, preco_unitario) VALUES ('Arroz Tio João', '15.60');
INSERT INTO public.produto(descricao, preco_unitario) VALUES ('Feijão Carioca', '9.30');
INSERT INTO public.produto(descricao, preco_unitario) VALUES ('Erva Charrua', '11.85');
INSERT INTO public.produto(descricao, preco_unitario) VALUES ('Açucar Cristal', '5.98');
INSERT INTO public.produto(descricao, preco_unitario) VALUES ('Laranja Suco', '4.40');

INSERT INTO public.usuario(data_atualizacao, data_cadastro, login, nome, senha) VALUES (timezone('utc', CURRENT_TIMESTAMP(0)),timezone('utc', CURRENT_TIMESTAMP(0)), 'brunomoura', 'Bruno Moura', '102030');
INSERT INTO public.usuario(data_atualizacao, data_cadastro, login, nome, senha) VALUES (timezone('utc', CURRENT_TIMESTAMP(0)),timezone('utc', CURRENT_TIMESTAMP(0)), 'rodrigoferreira', 'Rodrigo Ferreira', '907080');
INSERT INTO public.usuario(data_atualizacao, data_cadastro, login, nome, senha) VALUES (timezone('utc', CURRENT_TIMESTAMP(0)),timezone('utc', CURRENT_TIMESTAMP(0)), 'rafaelbastos', 'Rafael Bastos', '302010');
INSERT INTO public.usuario(data_atualizacao, data_cadastro, login, nome, senha) VALUES (timezone('utc', CURRENT_TIMESTAMP(0)),timezone('utc', CURRENT_TIMESTAMP(0)), 'danielamartins', 'Daniela Martins', '987465');

INSERT INTO public.telefone(numero, tipo, id_usuario) VALUES ('9999999999', 'Celular', 1);
INSERT INTO public.telefone(numero, tipo, id_usuario) VALUES ('5522552255', 'Casa', 4);






