DROP TABLE IF EXISTS public.product CASCADE;
CREATE TABLE public.product (
                                   id serial NOT NULL PRIMARY KEY,
                                   name text NOT NULL,
                                   description text NOT NULL,
                                   default_price decimal(20,2) NOT NULL,
                                   currency text NOT NULL,
                                   category_id int NOT NULL,
                                   supplier_id int NOT NULL,
                                   image text NOT NULL

);

DROP TABLE IF EXISTS public.category CASCADE;
CREATE TABLE public.category (
                               id serial NOT NULL PRIMARY KEY,
                               name text NOT NULL,
                               description text NOT NULL,
                               department text NOT NULL
);

DROP TABLE IF EXISTS public.supplier CASCADE;
CREATE TABLE public.supplier (
                             id serial NOT NULL PRIMARY KEY,
                             name text NOT NULL,
                             description text NOT NULL
);

DROP TABLE IF EXISTS public.user CASCADE;
CREATE TABLE public.user (
                               id serial NOT NULL PRIMARY KEY,
                               email text NOT NULL,
                               password text NOT NULL,
                               name text,
                               phone_number text,
                               billing_country text,
                               billing_city text,
                               billing_zipcode text,
                               billing_address text,
                               shipping_country text,
                               shipping_city text,
                               shipping_zipcode text,
                               shipping_address text
);

DROP TABLE IF EXISTS public.order CASCADE;
CREATE TABLE public.order (
                               id serial NOT NULL PRIMARY KEY,
                               status text NOT NULL,
                               date timestamp NOT NULL,
                               user_id int NOT NULL

);


DROP TABLE IF EXISTS public.line_item CASCADE;
CREATE TABLE public.line_item (
                              id serial NOT NULL PRIMARY KEY,
                              quantity int NOT NULL,
                              order_id int NOT NULL,
                              product_id int NOT NULL


);
ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES public.category(id);

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES public.supplier(id);

ALTER TABLE ONLY public.order
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.user(id);

ALTER TABLE ONLY public.line_item
    ADD CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES public.order(id);

ALTER TABLE ONLY public.line_item
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES public.product(id);