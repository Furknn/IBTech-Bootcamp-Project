create sequence "Product_Id_seq";

alter sequence "Product_Id_seq" owner to sa;

create sequence "Category_Id_seq";

alter sequence "Category_Id_seq" owner to sa;

create sequence "Cart_Id_seq";

alter sequence "Cart_Id_seq" owner to sa;

create sequence "CartProduct_Id_seq";

alter sequence "CartProduct_Id_seq" owner to sa;

create sequence "User_Id_seq";

alter sequence "User_Id_seq" owner to sa;

create table if not exists category
(
    id     bigint  default nextval('"Category_Id_seq"'::regclass) not null
        constraint "Category_pkey"
            primary key
        constraint "Category_Id_key"
            unique,
    name   varchar(200),
    detail varchar default '...'::character varying               not null
);

alter table category
    owner to sa;

alter sequence "Category_Id_seq" owned by category.id;

create table if not exists product
(
    id         bigint  default nextval('"Product_Id_seq"'::regclass) not null
        constraint "Product_pkey"
            primary key
        constraint "Product_Id_key"
            unique,
    name       varchar(200)                                          not null,
    price      double precision,
    categoryid bigint
        constraint product_category_id_fk
            references category,
    imageurl   varchar,
    detail     varchar default '...'::character varying              not null
);

alter table product
    owner to sa;

alter sequence "Product_Id_seq" owned by product.id;

create table if not exists cart
(
    id           bigint default nextval('"Cart_Id_seq"'::regclass) not null
        constraint "Cart_pkey"
            primary key
        constraint "Cart_Id_key"
            unique,
    totalamount  double precision,
    customername varchar(200)
);

alter table cart
    owner to sa;

alter sequence "Cart_Id_seq" owned by cart.id;

create table if not exists cartproduct
(
    id         bigint default nextval('"CartProduct_Id_seq"'::regclass) not null
        constraint "CartProduct_pkey"
            primary key
        constraint "CartProduct_Id_key"
            unique,
    cartid     bigint
        constraint cartproduct_cart_id_fk
            references cart,
    productid  bigint
        constraint cartproduct_product_id_fk
            references product,
    quantity   integer,
    price      double precision,
    taxrate    double precision,
    lineamount double precision
);

alter table cartproduct
    owner to sa;

alter sequence "CartProduct_Id_seq" owned by cartproduct.id;

create table if not exists "user"
(
    id       bigint default nextval('"User_Id_seq"'::regclass) not null
        constraint "User_pkey"
            primary key
        constraint "User_Id_key"
            unique,
    username varchar(200),
    password varchar(200)
);

alter table "user"
    owner to sa;

alter sequence "User_Id_seq" owned by "user".id;

