-- ************************************** product_discount

CREATE TABLE product_discount
(
 "id"                  serial NOT NULL,
 created_at          timestamp NOT NULL,
 expire_at           timestamp NOT NULL,
 discount_percentage int NOT NULL,
 active              boolean NOT NULL,
 CONSTRAINT PK_product_discount PRIMARY KEY ( "id" )
);

-- ************************************** product

CREATE TABLE product
(
 "id"               serial NOT NULL,
 discount_id      int NULL,
 name             varchar(50) NOT NULL,
 price            bigint NOT NULL,
 tax_percentage  int NOT NULL,
 quantity         bigint NOT NULL,
 description      text NOT NULL,
 photo_url_small  varchar(100) NULL,
 photo_url_medium varchar(100) NULL,
 photo_url_big    varchar(100) NULL,
 amount           varchar(10) NULL,
 weigh            numeric(18,2) NULL,
 height           numeric(18,2) NULL,
 created_at       timestamp NOT NULL,
 CONSTRAINT PK_product PRIMARY KEY ( "id" ),
 CONSTRAINT FK_15 FOREIGN KEY ( discount_id ) REFERENCES product_discount ( "id" )
);

CREATE INDEX IDX_product_discount ON product
(
 discount_id
);

-- ************************************** "permission"

CREATE TABLE "permission"
(
 "id"           serial NOT NULL,
 section_name varchar(50) NOT NULL,
 "create"       boolean NOT NULL,
 update       boolean NOT NULL,
 delete       boolean NOT NULL,
 read       boolean NOT NULL,
 CONSTRAINT PK_permission PRIMARY KEY ( "id" )
);

-- ************************************** "user"

CREATE TABLE "user"
(
 "id"           serial NOT NULL,
 first_name   varchar(50) NOT NULL,
 last_name    varchar(50) NOT NULL,
 created_at   timestamp NOT NULL,
 email        varchar(50) NOT NULL,
 phone_number varchar(50) NOT NULL,
 password     varchar(50) NOT NULL,
 CONSTRAINT PK_user PRIMARY KEY ( "id" )
);


-- ************************************** address

CREATE TABLE address
(
 "id"        serial NOT NULL,
 user_id   int NULL,
 name      varchar(50) NOT NULL,
 latitude   double precision NOT NULL,
 longitude double precision NOT NULL,
 city      varchar(50) NOT NULL,
 country   varchar(50) NOT NULL,
 CONSTRAINT PK_address PRIMARY KEY ( "id" ),
 CONSTRAINT FK_12 FOREIGN KEY ( user_id ) REFERENCES "user" ( "id" )
);

CREATE INDEX IDX_address_user ON address
(
 user_id
);

-- ************************************** category

CREATE TABLE category
(
 "id"              serial NOT NULL,
 name            varchar(50) NOT NULL,
 parent_category int NULL,
 CONSTRAINT PK_category PRIMARY KEY ( "id" ),
 CONSTRAINT FK_16 FOREIGN KEY ( parent_category ) REFERENCES category ( "id" )
);

CREATE INDEX IDX_category_parent ON category
(
 parent_category
);

-- ************************************** "order"

CREATE TABLE "order"
(
 "id"         serial NOT NULL,
 user_id    int NOT NULL,
 address_id int NOT NULL,
 created_at timestamp NOT NULL,
 statusCode int NOT NULL,
 CONSTRAINT PK_order PRIMARY KEY ( "id" ),
 CONSTRAINT FK_5 FOREIGN KEY ( user_id ) REFERENCES "user" ( "id" ),
 CONSTRAINT FK_14 FOREIGN KEY ( address_id ) REFERENCES address ( "id" )
);

CREATE INDEX IDX_order_user ON "order"
(
 user_id
);

CREATE INDEX IDX_order_address ON "order"
(
 address_id
);

-- ************************************** payment_method

CREATE TABLE payment_method
(
 "id"              serial NOT NULL,
 user_id         int NOT NULL,
 order_id        int NULL,
 type            varchar(50) NOT NULL,
 amount          bigint NOT NULL,
 payment_user_id varchar(50) NULL,
 payment_id      varchar(100) NULL,
 payed_at        timestamp NULL,
 expire_at       timestamp NULL,
 isConfirmed     boolean NOT NULL,
 description     text NOT NULL,
 currency        varchar(50) NOT NULL,
 CONSTRAINT PK_payment_method PRIMARY KEY ( "id" ),
 CONSTRAINT FK_8 FOREIGN KEY ( user_id ) REFERENCES "user" ( "id" ),
 CONSTRAINT FK_9 FOREIGN KEY ( order_id ) REFERENCES "order" ( "id" )
);

CREATE INDEX IDX_payment_method_user ON payment_method
(
 user_id
);

CREATE INDEX IDX_payment_method_order ON payment_method
(
 order_id
);

-- ************************************** order_item

CREATE TABLE order_item
(
 "id"             serial NOT NULL,
 product_id     int NOT NULL,
 order_id       int NOT NULL,
 purchase_price bigint NOT NULL,
 quantity       int NOT NULL,
 CONSTRAINT PK_order_item PRIMARY KEY ( "id" ),
 CONSTRAINT FK_6 FOREIGN KEY ( product_id ) REFERENCES product ( "id" ),
 CONSTRAINT FK_11_1 FOREIGN KEY ( order_id ) REFERENCES "order" ( "id" )
);

CREATE INDEX IDX_order_item_product ON order_item
(
 product_id
);

CREATE INDEX IDX_order_item_order ON order_item
(
 order_id
);

-- ************************************** product_category

CREATE TABLE product_category
(
 "id"          serial NOT NULL,
 product_id  int NOT NULL,
 category_id int NOT NULL,
 CONSTRAINT PK_product_category PRIMARY KEY ( "id" ),
 CONSTRAINT FK_1 FOREIGN KEY ( product_id ) REFERENCES product ( "id" ),
 CONSTRAINT FK_2 FOREIGN KEY ( category_id ) REFERENCES category ( "id" )
);

CREATE INDEX IDX_product_category_product ON product_category
(
 product_id
);

CREATE INDEX IDX_product_category_category ON product_category
(
 category_id
);


-- ************************************** product_inventory

CREATE TABLE product_inventory
(
 "id"           serial NOT NULL,
 product_id   int NOT NULL,
 quantity     bigint NOT NULL,
 created_at timestamp NULL,
 expire_at    timestamp NULL,
 provider     varchar(50) NOT NULL,
 price        bigint NOT NULL,
 CONSTRAINT PK_product_inventory PRIMARY KEY ( "id" ),
 CONSTRAINT FK_13 FOREIGN KEY ( product_id ) REFERENCES product ( "id" )
);

CREATE INDEX IDX_product_inventory_product ON product_inventory
(
 product_id
);

-- ************************************** role

CREATE TABLE role
(
 "id"            serial NOT NULL,
 role_name     varchar(50) NOT NULL,
 permission_id int NOT NULL,
 user_id       int NOT NULL,
 CONSTRAINT PK_role PRIMARY KEY ( "id" ),
 CONSTRAINT FK_3 FOREIGN KEY ( permission_id ) REFERENCES "permission" ( "id" ),
 CONSTRAINT FK_4 FOREIGN KEY ( user_id ) REFERENCES "user" ( "id" )
);

CREATE INDEX IDX_role_permission ON role
(
 permission_id
);

CREATE INDEX IDX_role_user ON role
(
 user_id
);

-- ************************************** cart_item

CREATE TABLE cart_item
(
 "id"         serial NOT NULL,
 user_id    int NOT NULL,
 product_id int NOT NULL,
 amount     int NOT NULL,
 CONSTRAINT PK_cart_item PRIMARY KEY ( "id" ),
 CONSTRAINT FK_10 FOREIGN KEY ( user_id ) REFERENCES "user" ( "id" ),
 CONSTRAINT FK_11 FOREIGN KEY ( product_id ) REFERENCES product ( "id" )
);

CREATE INDEX IDX_cart_item_user ON cart_item
(
 user_id
);

CREATE INDEX IDX_cart_item_product ON cart_item
(
 product_id
);
