DROP TABLE IF EXISTS shop.users;
DROP TABLE IF EXISTS shop.products;
DROP TABLE IF EXISTS shop.cart;
DROP TABLE IF EXISTS shop.product_reviews;
DROP TABLE IF EXISTS shop.user_reviews;

CREATE KEYSPACE IF NOT EXISTS shop WITH REPLICATION = {'class': 'SimpleStrategy','replication_factor': '1'};


CREATE TABLE IF NOT EXISTS shop.users (
    id              uuid,
    username        text,
    email           text,
    address         text,
    creation_time   timestamp,

    PRIMARY KEY((id), creation_time)
);

CREATE INDEX ON shop.users (username);

CREATE TABLE IF NOT EXISTS shop.products (
    id              uuid,
    namee           text,
    descriptionn    text,
    price           decimal,
    categories      set<text>,

    PRIMARY KEY(id)
);

CREATE INDEX ON shop.products (namee);
CREATE INDEX ON shop.products (categories);


CREATE TABLE IF NOT EXISTS shop.cart (
    user_id         uuid,
    products        map<uuid, int>,

    PRIMARY KEY(user_id)
);

CREATE TABLE IF NOT EXISTS shop.product_reviews (
    product_id      uuid,
    reviews         list<text>,

    PRIMARY KEY(product_id)
);

CREATE TABLE IF NOT EXISTS shop.user_reviews (
    user_id         uuid,
    reviews         list<text>,

    PRIMARY KEY(user_id)
);