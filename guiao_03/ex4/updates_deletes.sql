-- UPDATES --
UPDATE shop.user_reviews
SET reviews = reviews + ['I really like this product']
WHERE user_id = 3fc12cab-2064-4af4-9969-1e5050d6b000;

UPDATE shop.product_reviews
SET reviews = reviews + ['I really like this product']
WHERE product_id = 3fc12cab-2064-4af4-9969-1e5050d6b000;

UPDATE shop.user_reviews
SET reviews = reviews + ['The previous review will soon be deleted!']
WHERE user_id = 3fc12cab-2064-4af4-9969-1e5050d6b000;

UPDATE shop.product_reviews
SET reviews = reviews + ['The previous review will soon be deleted!']
WHERE product_id = 3fc12cab-2064-4af4-9969-1e5050d6b000;

UPDATE shop.products
SET categories = {'Eletrônicos'}
WHERE id = 3fc12cab-2064-4af4-9969-1e5050d6b000;

UPDATE shop.cart
SET products[3fc12cab-2064-4af4-9969-1e5050d6b000] = 10
WHERE user_id = 3fc12cab-2064-4af4-9969-1e5050d6b000;

UPDATE shop.users
SET email = 'Another@email.com'
WHERE id = 3fc12cab-2064-4af4-9969-1e5050d6b000 AND creation_time='2023-11-28 16:34:16.763';


-- DELETES --
DELETE products[3fc12cab-2064-4af4-9969-1e5050d6b010]
FROM shop.cart
WHERE user_id = 3fc12cab-2064-4af4-9969-1e5050d6b000;

DELETE categories['Eletrônicos']
FROM shop.products
WHERE id = 3fc12cab-2064-4af4-9969-1e5050d6b000;

DELETE products
FROM shop.cart
WHERE user_id = 3fc12cab-2064-4af4-9969-1e5050d6b000;

DELETE categories
FROM shop.products
WHERE id = 3fc12cab-2064-4af4-9969-1e5050d6b000;

DELETE reviews[0]
FROM shop.product_reviews
WHERE product_id = 3fc12cab-2064-4af4-9969-1e5050d6b000;

DELETE reviews[0]
FROM shop.user_reviews
WHERE user_id = 3fc12cab-2064-4af4-9969-1e5050d6b000;