// users, products, cart, product reviews, user reviews

-- USERS INSERT AREA --
INSERT INTO shop.users (id, username, email, address, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b000, 'Pedro', 'pd.pinho@ua.pt', 'Rua das ruas',toTimestamp(now()));

INSERT INTO shop.users (id, username, email, address, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b001, 'Ana', 'ana@email.com', 'Avenida das Flores', toTimestamp(now()));

INSERT INTO shop.users (id, username, email, address, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b002, 'Carlos', 'carlos@email.com', 'Rua da Liberdade', toTimestamp(now()));

INSERT INTO shop.users (id, username, email, address, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b003, 'Marta', 'marta@email.com', 'Praça Central', toTimestamp(now()));

INSERT INTO shop.users (id, username, email, address, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b004, 'Ricardo', 'ricardo@email.com', 'Avenida Principal', toTimestamp(now()));

INSERT INTO shop.users (id, username, email, address, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b005, 'Sofia', 'sofia@email.com', 'Rua dos Artistas', toTimestamp(now()));

INSERT INTO shop.users (id, username, email, address, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b006, 'João', 'joao@email.com', 'Alameda dos Esportes', toTimestamp(now()));

INSERT INTO shop.users (id, username, email, address, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b007, 'Lúcia', 'lucia@email.com', 'Travessa das Árvores', toTimestamp(now()));

INSERT INTO shop.users (id, username, email, address, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b008, 'Daniel', 'daniel@email.com', 'Praia da Liberdade', toTimestamp(now()));

INSERT INTO shop.users (id, username, email, address, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b009, 'Catarina', 'catarina@email.com', 'Rua das Estrelas', toTimestamp(now()));

INSERT INTO shop.users (id, username, email, address, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b010, 'Miguel', 'miguel@email.com', 'Avenida dos Sonhos', toTimestamp(now()));

INSERT INTO shop.users (id, username, email, address, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b011, 'Inês', 'ines@email.com', 'Travessa das Maravilhas', toTimestamp(now()));

INSERT INTO shop.users (id, username, email, address, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b012, 'Hugo', 'hugo@email.com', 'Alameda das Marés', toTimestamp(now()));

INSERT INTO shop.users (id, username, email, address, creation_time)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b013, 'Isabel', 'isabel@email.com', 'Praça dos Ventos', toTimestamp(now()));


-- PRODUCTS INSERT AREA --
INSERT INTO shop.products (id, namee, descriptionn, price, categories)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b000, 'Televisão XPTO', 'Uma televisão muito avançada, como podemos observar pelo nome!', 399.99, {'Eletrônicos', 'Tecnologia'});

INSERT INTO shop.products (id, namee, descriptionn, price, categories)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b001, 'Notebook ABC', 'Um notebook poderoso para suas necessidades diárias', 899.99, {'Eletrônicos', 'Tecnologia', 'Computadores'});

INSERT INTO shop.products (id, namee, descriptionn, price, categories)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b002, 'Smartphone SuperPlus', 'Um smartphone com recursos avançados e câmera de alta resolução', 599.99, {'Eletrônicos', 'Tecnologia', 'Smartphones'});

INSERT INTO shop.products (id, namee, descriptionn, price, categories)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b003, 'Câmera DSLR Profissional', 'Uma câmera DSLR para fotografia profissional', 1299.99, {'Eletrônicos', 'Fotografia'});

INSERT INTO shop.products (id, namee, descriptionn, price, categories)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b004, 'Fones Premium', 'Fones com cancelamento de ruído e alta qualidade de som', 149.99, {'Eletrônicos', 'Áudio'});

INSERT INTO shop.products (id, namee, descriptionn, price, categories)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b005, 'Consola Videogame XBOX', 'Consola de videogame com gráficos incríveis e jogos exclusivos', 499.99, {'Eletrônicos', 'Jogos'});

INSERT INTO shop.products (id, namee, descriptionn, price, categories)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b006, 'Máquina de Café Automática', 'Uma máquina de café que prepara diferentes tipos de café automaticamente', 299.99, {'Eletrodomésticos', 'Café'});

INSERT INTO shop.products (id, namee, descriptionn, price, categories)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b007, 'Robô Aspirador', 'Um aspirador de pó inteligente que limpa sua casa automaticamente', 199.99, {'Eletrodomésticos', 'Limpeza'});

INSERT INTO shop.products (id, namee, descriptionn, price, categories)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b008, 'Mochila para Laptop', 'Uma mochila resistente para laptops, ideal para viagens', 49.99, {'Moda', 'Acessórios'});

INSERT INTO shop.products (id, namee, descriptionn, price, categories)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b009, 'Luminária LED Inteligente', 'Uma luminária com controle remoto e ajuste de intensidade', 79.99, {'Casa', 'Iluminação'});

INSERT INTO shop.products (id, namee, descriptionn, price, categories)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b010, 'Panela Elétrica Multifuncional', 'Uma panela elétrica com diversas funções para facilitar o preparo de refeições', 129.99, {'Eletrodomésticos', 'Culinária'});

INSERT INTO shop.products (id, namee, descriptionn, price, categories)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b011, 'Relógio Inteligente', 'Um relógio inteligente com monitor de saúde e notificações', 159.99, {'Eletrônicos', 'Wearables'});

INSERT INTO shop.products (id, namee, descriptionn, price, categories)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b012, 'Bicicleta Elétrica', 'Uma bicicleta elétrica para passeios sustentáveis', 799.99, {'Esportes', 'Mobilidade'});

INSERT INTO shop.products (id, namee, descriptionn, price, categories)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b013, 'Caixa de Som Bluetooth', 'Caixa de som portátil com conectividade Bluetooth', 69.99, {'Eletrônicos', 'Áudio'});


-- PRODUCT REVIEW AREA --
INSERT INTO shop.product_reviews(product_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b000, []);

INSERT INTO shop.product_reviews(product_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b001, []);

INSERT INTO shop.product_reviews(product_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b002, []);

INSERT INTO shop.product_reviews(product_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b003, []);

INSERT INTO shop.product_reviews(product_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b004, []);

INSERT INTO shop.product_reviews(product_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b005, []);

INSERT INTO shop.product_reviews(product_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b006, []);

INSERT INTO shop.product_reviews(product_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b007, []);

INSERT INTO shop.product_reviews(product_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b008, []);

INSERT INTO shop.product_reviews(product_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b009, []);

INSERT INTO shop.product_reviews(product_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b010, []);

INSERT INTO shop.product_reviews(product_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b011, []);

INSERT INTO shop.product_reviews(product_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b012, []);

INSERT INTO shop.product_reviews(product_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b013, []);


-- USER REVIEW AREA --
INSERT INTO shop.user_reviews(user_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b000, []);

INSERT INTO shop.user_reviews(user_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b001, []);

INSERT INTO shop.user_reviews(user_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b002, []);

INSERT INTO shop.user_reviews(user_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b003, []);

INSERT INTO shop.user_reviews(user_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b004, []);

INSERT INTO shop.user_reviews(user_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b005, []);

INSERT INTO shop.user_reviews(user_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b006, []);

INSERT INTO shop.user_reviews(user_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b007, []);

INSERT INTO shop.user_reviews(user_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b008, []);

INSERT INTO shop.user_reviews(user_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b009, []);

INSERT INTO shop.user_reviews(user_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b010, []);

INSERT INTO shop.user_reviews(user_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b011, []);

INSERT INTO shop.user_reviews(user_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b012, []);

INSERT INTO shop.user_reviews(user_id, reviews)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b013, []);


-- SHOPPING CART AREA --
INSERT INTO shop.cart(user_id, products)
VALUES
(3fc12cab-2064-4af4-9969-1e5050d6b000, {3fc12cab-2064-4af4-9969-1e5050d6b010: 2})