CREATE SEQUENCE IF NOT EXISTS public.category_id_seq
    INCREMENT 1
    START 5
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY category.id;
ALTER TABLE category ALTER COLUMN id SET DEFAULT nextval('category_id_seq'::regclass);

CREATE SEQUENCE IF NOT EXISTS public.company_category_id_seq
    INCREMENT 1
    START 4
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY company_category.id;
ALTER TABLE company_category ALTER COLUMN id SET DEFAULT nextval('company_category_id_seq'::regclass);

CREATE SEQUENCE IF NOT EXISTS public.product_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY product.id;
ALTER TABLE product ALTER COLUMN id SET DEFAULT nextval('product_id_seq'::regclass);

CREATE SEQUENCE IF NOT EXISTS public.subcategory_id_seq
    INCREMENT 1
    START 10
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY subcategory.id;
ALTER TABLE subcategory ALTER COLUMN id SET DEFAULT nextval('subcategory_id_seq'::regclass);

CREATE SEQUENCE IF NOT EXISTS public.users_id_seq
    INCREMENT 1
    START 2
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY users.id;
ALTER TABLE users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);

CREATE SEQUENCE IF NOT EXISTS public.hibernate_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;