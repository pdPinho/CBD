-- Get all products in cart of given user
SELECT * FROM shop.cart WHERE user_id=3fc12cab-2064-4af4-9969-1e5050d6b000;

-- Get 3 products
SELECT * FROM shop.products LIMIT 3;

-- Get product with name X
SELECT * FROM shop.products WHERE namee='Televisão XPTO';

-- Get products which have Audio in their categories
SELECT * FROM shop.products WHERE categories CONTAINS 'Áudio';

-- For some reason this query doesn't work... (Get all users where name starts with P and order it by creation time DESC)
-- SELECT * FROM shop.users WHERE username LIKE 'P%' ORDER BY creation_time DESC;

-- Get the average price of products from our shop
SELECT AVG(price) FROM shop.products;

-- Check which users were created between X and Y
SELECT * FROM shop.users WHERE creation_time > '2023-11-28 16:34:17.045' AND creation_time < '2023-11-28 16:34:17.764' ALLOW FILTERING;

-- Check how many users have registered themselves in our platform
SELECT COUNT(*) FROM shop.users;

-- The following query works but since I'm using docker, it requires me to modify the docker-entrypoint.sh
-- CREATE OR REPLACE FUNCTION shop.lsizeof(data set<text>)
-- CALLED ON NULL INPUT RETURNS int
-- LANGUAGE java AS 'return data.size();';

-- Check how many categories a given product has
-- SELECT shop.lsizeof(categories) FROM shop.products WHERE namee='Mochila para Laptop';