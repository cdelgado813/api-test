DROP TABLE IF EXISTS PRICES;
DROP TABLE IF EXISTS BRAND;


CREATE TABLE IF NOT EXISTS BRAND (
    brand_id INT PRIMARY KEY    -- Puedes agregar otros campos que necesites, como el nombre de la marca
);

INSERT INTO BRAND (brand_id)
VALUES (1);

CREATE TABLE IF NOT EXISTS PRICES (
    -- Definir la clave primaria compuesta (ID embebido)
    product_id INT NOT NULL,          -- Suponiendo que `PriceEntityId` tiene un campo `productId`
    brand_id INT NOT NULL,            -- Suponiendo que `PriceEntityId` tiene un campo `brandId`
    price_list INT NOT NULL,
    -- Fechas de inicio y fin del rango de precios
    start_date TIMESTAMP NOT NULL,    -- Fecha de inicio
    end_date TIMESTAMP NOT NULL,      -- Fecha de fin
    -- Otros campos
    priority INT NOT NULL,            -- Prioridad
    price FLOAT NOT NULL,             -- Precio final de venta
    curr VARCHAR(3) NOT NULL,         -- Moneda (ISO 4217 de 3 caracteres)

    -- Definir la clave primaria compuesta
    PRIMARY KEY (product_id, brand_id, price_list)
);

INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR)
VALUES (1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR');

INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR)
VALUES (1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR');

INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR)
VALUES (1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR');

INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR)
VALUES (1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR');