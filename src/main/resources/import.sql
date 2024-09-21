
	INSERT INTO public.estado (nome, sigla) VALUES
    ('Acre', 'AC'),
    ('Alagoas', 'AL'),
    ('Amapá', 'AP'),
    ('Amazonas', 'AM'),
    ('Bahia', 'BA'),
    ('Ceará', 'CE'),
    ('Distrito Federal', 'DF'),
    ('Espírito Santo', 'ES'),
    ('Goiás', 'GO'),
    ('Maranhão', 'MA'),
    ('Mato Grosso', 'MT'),
    ('Mato Grosso do Sul', 'MS'),
    ('Minas Gerais', 'MG'),
    ('Pará', 'PA'),
    ('Paraíba', 'PB'),
    ('Paraná', 'PR'),
    ('Pernambuco', 'PE'),
    ('Piauí', 'PI'),
    ('Rio de Janeiro', 'RJ'),
    ('Rio Grande do Norte', 'RN'),
    ('Rio Grande do Sul', 'RS'),
    ('Rondônia', 'RO'),
    ('Roraima', 'RR'),
    ('Santa Catarina', 'SC'),
    ('São Paulo', 'SP'),
    ('Sergipe', 'SE'),
    ('Tocantins', 'TO');


///////////////////////////////////////////////////////////////////////////////////////////////////////////////

INSERT INTO public.municipio (nome, id_estado) VALUES
-- Municípios do Acre (AC)
('Rio Branco', 1),
('Cruzeiro do Sul', 1),
-- Municípios de Alagoas (AL)
('Maceió', 2),
('Arapiraca', 2),
-- Municípios do Amapá (AP)
('Macapá', 3),
('Santana', 3),
-- Municípios do Amazonas (AM)
('Manaus', 4),
('Parintins', 4),
-- Municípios da Bahia (BA)
('Salvador', 5),
('Feira de Santana', 5),
-- Municípios do Ceará (CE)
('Fortaleza', 6),
('Caucaia', 6),
-- Municípios do Distrito Federal (DF)
('Brasília', 7),
-- Municípios do Espírito Santo (ES)
('Vitória', 8),
('Vila Velha', 8),
-- Municípios de Goiás (GO)
('Goiânia', 9),
('Anápolis', 9),
-- Municípios do Maranhão (MA)
('São Luís', 10),
('Imperatriz', 10),
-- Municípios de Mato Grosso (MT)
('Cuiabá', 11),
('Várzea Grande', 11),
-- Municípios de Mato Grosso do Sul (MS)
('Campo Grande', 12),
('Dourados', 12),
-- Municípios de Minas Gerais (MG)
('Belo Horizonte', 13),
('Uberlândia', 13),
-- Municípios do Pará (PA)
('Belém', 14),
('Ananindeua', 14),
-- Municípios da Paraíba (PB)
('João Pessoa', 15),
('Campina Grande', 15),
-- Municípios do Paraná (PR)
('Curitiba', 16),
('Londrina', 16),
-- Municípios de Pernambuco (PE)
('Recife', 17),
('Jaboatão dos Guararapes', 17),
-- Municípios do Piauí (PI)
('Teresina', 18),
('Parnaíba', 18),
-- Municípios do Rio de Janeiro (RJ)
('Rio de Janeiro', 19),
('Niterói', 19),
-- Municípios do Rio Grande do Norte (RN)
('Natal', 20),
('Mossoró', 20),
-- Municípios do Rio Grande do Sul (RS)
('Porto Alegre', 21),
('Caxias do Sul', 21),
-- Municípios de Rondônia (RO)
('Porto Velho', 22),
('Ji-Paraná', 22),
-- Municípios de Roraima (RR)
('Boa Vista', 23),
-- Municípios de Santa Catarina (SC)
('Florianópolis', 24),
('Joinville', 24),
-- Municípios de São Paulo (SP)
('São Paulo', 25),
('Campinas', 25),
-- Municípios de Sergipe (SE)
('Aracaju', 26),
('Nossa Senhora do Socorro', 26),
-- Municípios de Tocantins (TO)
('Palmas', 27),
('Araguaína', 27);


///////////////////////////////////////////////////////////////////////////////////////////////////////////////

INSERT INTO public.endereco(
	 municipio_id, bairro, cep, complemento, logradouro)
	VALUES ( 1, 'logoali', '770202222', 'perto dacula', 'nao sei o que e');

	INSERT INTO public.endereco(
    	 municipio_id, bairro, cep, complemento, logradouro)
    	VALUES ( 2, 'acula', '770202222', 'perto dacula', 'nao sei o que e');

    INSERT INTO public.endereco(
    	 municipio_id, bairro, cep, complemento, logradouro)
    	VALUES ( 3 , 'bem ali', '770202222', 'perto do trem grande', 'nao sei o que e');

    INSERT INTO public.endereco(
    	 municipio_id, bairro, cep, complemento, logradouro)
    	VALUES ( 4 , 'apontando com a boca', '770202222', 'perto do trem grande logo ali', 'nao sei o que e');


///////////////////////////////////////////////////////////////////////////////////////////////////////////////

INSERT INTO public.contato(
       	  email, telefone)
       	VALUES (  'tq@gmail.com', '23523623457');

INSERT INTO public.contato(
       	  email, telefone)
       	VALUES (  'ty@gmail.com', '4563242345');

INSERT INTO public.contato(
       	  email, telefone)
       	VALUES (  'tck@gmail.com', '2345266234');

INSERT INTO public.contato(
       	  email, telefone)
       	VALUES (  'qck@gmail.com', '23452347724');



///////////////////////////////////////////////////////////////////////////////////////////////////////////////

INSERT INTO public.usuario(
	datanacimento, id_contato, id_enderecoprincipal, cpf, nome, login, senha, perfis, nomeimagem)
	VALUES ('2001/10/01', 2, 2, '23567234','pp', 'pp', 'TRwn0XU29Gwl2sagG00bvjrNJvLuYo+dbOBJ7R3xFpU4m/FAUc5q8OoGbVNwPF7F5713RaYkN4qyufNCDHm/mA==', '{ADMIN}' , 'usuario.png'),
	('2001/10/01', 1, 1, '2345624353','qq', 'qq', 'TRwn0XU29Gwl2sagG00bvjrNJvLuYo+dbOBJ7R3xFpU4m/FAUc5q8OoGbVNwPF7F5713RaYkN4qyufNCDHm/mA==', '{ADMIN}', 'usuario.png'),
	('2001/10/01', 3, 3, '43587345635','tt', 'tt', 'TRwn0XU29Gwl2sagG00bvjrNJvLuYo+dbOBJ7R3xFpU4m/FAUc5q8OoGbVNwPF7F5713RaYkN4qyufNCDHm/mA==', '{ADMIN}', 'usuario.png'),
	('2001/10/01', 4, 4, '7896789676','aa', 'aa', 'TRwn0XU29Gwl2sagG00bvjrNJvLuYo+dbOBJ7R3xFpU4m/FAUc5q8OoGbVNwPF7F5713RaYkN4qyufNCDHm/mA==', '{ADMIN}', 'usuario.png');


///////////////////////////////////////////////////////////////////////////////////////////////////////////////


INSERT INTO public.contato(
       	  email, telefone)
       	VALUES (  'tq@gmail.com', '23523623457');

INSERT INTO public.contato(
       	  email, telefone)
       	VALUES (  'ty@gmail.com', '4563242345');

INSERT INTO public.contato(
       	  email, telefone)
       	VALUES (  'tck@gmail.com', '2345266234');

INSERT INTO public.contato(
       	  email, telefone)
       	VALUES (  'qck@gmail.com', '23452347724');



///////////////////////////////////////////////////////////////////////////////////////////////////////////////
INSERT INTO public.autor(
	 id_usuario, nomeartistico)
	VALUES ( 1, 'danona'),
			( 2, 'dolarama'),
			( 3, 'dorama'),
			( 3, 'dropando');

///////////////////////////////////////////////////////////////////////////////////////////////////////////////

INSERT INTO public.cor(
	 corrgb, descricao)
	VALUES ( '(255,255,255)', 'cor principal'),
			( '(255,0,255)', 'cor Secundária'),
			( '(0,255,255)', 'Cor terciária'),
			( '(255,255,0)', 'Cor quartenária'),
			( '(0,0,0)', 'Bleck');

///////////////////////////////////////////////////////////////////////////////////////////////////////////////

INSERT INTO public.editora(
	 id_endereco, cnpj, nome, telefone)
	VALUES ( 3, '36543465', 'dondoca investimento', '6312431241'),
			( 3, '345634532', 'roni coliman investimento', '6313451241'),
			( 3, '234562345', 'dondoca banks', '6312477741'),
			( 3, '345234663', 'roni coliman banks', '6312778841');

///////////////////////////////////////////////////////////////////////////////////////////////////////////////

INSERT INTO public.livro(
	anopublicacao, numpaginas, valor,  id_autor, id_editora, categorialivro, nomeimagem, descricao, titulo)
	VALUES ( 2011, 99, 50.50, 1, 1, 'ROMANCE','imagemLivro.png' ,'apaixnado na lua', 'a luz da lua'),
			( 2021, 79, 5.50, 2, 2, 'FICCAO', 'imagemLivro.png' ,'a gameplay', 'a gameplay Assassina'),
			( 2017, 83, 45.50, 3, 3, 'TERROR', 'imagemLivro.png' ,'jovens em um acampamento', 'Json'),
			( 2022, 80, 2.50, 4, 4, 'AUTO_AJUDA', 'imagemLivro.png' ,'como mudar a sua vida', 'a maior mentira');

///////////////////////////////////////////////////////////////////////////////////////////////////////////////

INSERT INTO public.marca(
	 descricao, nome)
	VALUES ( 'fornecedor gringo', 'Light life'),
			( 'fornecedor nacional', 'll luminaria'),
			( 'fornecedor nacional', 'luz'),
			( 'fornecedor gringo', 'caloteiro');

///////////////////////////////////////////////////////////////////////////////////////////////////////////////

INSERT INTO public.luminaria(
 	valor,  id_cor, id_marca, descricao, estilo, tipodefontedeluz, nomeimagem)
 	VALUES ( 167.00, 1, 1 , 'luminaria de teclado', 'Light bar', 'led' , 'luminaria.png'),
 			( 107.00, 3, 2 ,'luminaria da Pixel', 'luminaria', 'led' , 'luminaria.png'),
 			( 17.00, 4, 3 ,'luminaria de torre', 'torre', 'led' , 'luminaria.png'),
 			( 168.00, 5, 4 ,'luminaria de lava', 'torre', 'led' , 'luminaria.png');
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

