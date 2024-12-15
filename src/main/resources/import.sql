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

INSERT INTO public.municipio (nome, idEstado) VALUES
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

INSERT INTO public.endereco(idMunicipio, bairro, cep, complemento, logradouro) VALUES
(5 , 'plano diretor sul', '77000000', 'perto da cula', '208 sul'),
(5 , 'no meio da cidade', '20010000', 'perto do tiroteio', '108 sul'),
(5 , 'bairro central', '72800000', 'perto do meio', '104 norte'),
(6, 'Centro', '12345678', 'Apt. 101', 'Rua Principal'),
(7, 'Jardim das Flores', '87654321', 'Bloco B', 'Av. das Rosas'),
(8, 'Bela Vista', '11223344', 'Casa 2', 'Travessa das Palmeiras'),
(9, 'Vila Nova', '44332211', 'Térreo', 'Rua dos Ipês'),
(10, 'Santa Maria', '55667788', 'Sala 5', 'Alameda das Acácias'),
(11, 'Lagoa Azul', '77889900', 'Fundos', 'Rua da Harmonia'),
(12, 'Vila Esperança', '99001122', 'Prédio 3', 'Rua do Sol');

///////////////////////////////////////////////////////////////////////////////////////////////////////////////

INSERT INTO public.telefone(codegoDeArea, numero) VALUES
('63', '981508736'),
('63', '981306914'),
('61', '998877665'),
('62', '987654321'),
('64', '912345678'),
('65', '934567890'),
('67', '956789012'),
('68', '998761234'),
('69', '987612345'),
('71', '997123456');

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
INSERT INTO public.usuario
(nome, email, telefone, endereco, username, senha, perfil)
VALUES 
( 'Nicole', 'nicole@unitins.br', '', '', 'nicole', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ADMIN');

INSERT INTO public.usuario
(nome, email, telefone, endereco, username, senha, perfil)
VALUES 
( 'Jeezyel', 'jeezyel@unitins.br', '', '', 'jeezyel', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ADMIN');

///////////////////////////////////////////////////////////////////////////////////////////////////////////////

INSERT INTO public.editora(nome, cnpj, telefone) VALUES
('Shueisha', '12.345.678/0001-99', '1133224455'),
('Kodansha', '23.456.789/0001-88', '1122334455'),
('Shogakukan', '34.567.890/0001-77', '1144556677'),
('Kadokawa', '45.678.901/0001-66', '1166778899'),
('Square Enix', '56.789.012/0001-55', '1188990011'),
('Hakusensha', '67.890.123/0001-44', '1122445577'),
('Futabasha', '78.901.234/0001-33', '1199887766'),
('Akita Shoten', '89.012.345/0001-22', '1133445566'),
('Media Factory', '90.123.456/0001-11', '1177889900'),
('Tokyopop', '01.234.567/0001-00', '1144778899');

///////////////////////////////////////////////////////////////////////////////////////////////////////////////

INSERT INTO public.mangaGenero(genero) VALUES
('Ação'),
('Aventura'),
('Comédia'),
('Drama'),
('Fantasia'),
('Ficção Científica'),
('Horror'),
('Mistério'),
('Romance'),
('Suspense');

///////////////////////////////////////////////////////////////////////////////////////////////////////////////

INSERT INTO public.formatoManga(formato) VALUES
('Tankobon'),
('Bunkobon'),
('Digital'),
('Volume'),
('One-shot'),
('Graphic Novel'),
('Webtoon'),
('Omnibus'),
('Deluxe Edition'),
('Light Novel');

///////////////////////////////////////////////////////////////////////////////////////////////////////////////

INSERT INTO public.idioma(idioma, sigla) VALUES
('Português', 'PT'),
('Inglês', 'EN'),
('Espanhol', 'ES'),
('Japonês', 'JA'),
('Francês', 'FR'),
('Alemão', 'DE'),
('Italiano', 'IT'),
('Chinês', 'ZH'),
('Coreano', 'KO'),
('Russo', 'RU');

///////////////////////////////////////////////////////////////////////////////////////////////////////////////

INSERT INTO public.manga(nome, nomeimagem, valor, idEditora, idFormato, idIdioma, classificacaoindicativa, estoque)
VALUES
  ('Naruto','manga.png', 35.90, 1, 1, 1, 1, 50),
  ('One Piece','manga.png', 40.00, 1, 1, 1, 1, 75),
  ('Attack on Titan','manga.png', 45.00, 2, 2, 1, 1, 30),
  ('Dragon Ball Z','manga.png', 30.00, 1, 1, 1, 1, 60),
  ('Demon Slayer','manga.png', 38.00, 2, 1, 1, 1, 40),
  ('My Hero Academia','manga.png', 42.00, 3, 2, 1, 1, 70),
  ('Death Note','manga.png', 33.50, 1, 1, 1, 1, 45),
  ('Fullmetal Alchemist','manga.png', 39.99, 3, 2, 1, 1, 55),
  ('Bleach','manga.png', 36.50, 1, 1, 1, 1, 65),
  ('Tokyo Revengers','manga.png', 41.50, 2, 1, 1, 1, 80);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////

INSERT INTO public.manga_genero (idManga, idGenero) VALUES 
(1, 1), (1, 2), -- Naruto: Ação, Aventura
(2, 1), (2, 2), -- One Piece: Ação, Aventura
(3, 1), (3, 3), (3, 5), -- Attack on Titan: Ação, Drama, Sobrenatural
(4, 1), (4, 4), (4, 6), -- Dragon Ball Z: Ação, Fantasia, Comédia
(5, 1), (5, 4), (5, 5), -- Demon Slayer: Ação, Fantasia, Sobrenatural
(6, 1), (6, 6), (6, 3), -- My Hero Academia: Ação, Comédia, Drama
(7, 3), (7, 5), (7, 7), -- Death Note: Drama, Sobrenatural, Suspense
(8, 1), (8, 8), (8, 3), -- Fullmetal Alchemist: Ação, Sci-Fi, Drama
(9, 1), (9, 4), (9, 9), -- Bleach: Ação, Fantasia, Romance
(10, 1), (10, 3), (10, 10); -- Tokyo Revengers: Ação, Drama, Histórico

///////////////////////////////////////////////////////////////////////////////////////////////////////////////