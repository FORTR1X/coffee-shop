CREATE TABLE users
(
    id           INTEGER      NOT NULL
        CONSTRAINT users_id_pkey PRIMARY KEY,
    username     VARCHAR(50)  NOT NULL
        CONSTRAINT users_username_key UNIQUE,
    password     VARCHAR(255) NOT NULL,
    email        VARCHAR(100) NOT NULL,
    phone_number VARCHAR(32)  NOT NULL,
    enabled      BOOLEAN      NOT NULL
);

CREATE TABLE authorities
(
    username  VARCHAR(50) NOT NULL,
    authority varchar(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);
CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);

CREATE TABLE oauth_client_details
(
    client_id               VARCHAR(255) NOT NULL
        CONSTRAINT oauth_client_details_client_id_pkey PRIMARY KEY,
    access_token_validity   INTEGER,
    additional_information  VARCHAR(255),
    authorities             VARCHAR(255),
    authorized_grant_types  VARCHAR(255),
    autoapprove             BOOLEAN      NOT NULL,
    client_secret           VARCHAR(255),
    refresh_token_validity  INTEGER,
    resource_ids            VARCHAR(255),
    scope                   VARCHAR(255),
    web_server_redirect_uri VARCHAR(255)
);

-- coffee-client
INSERT INTO oauth_client_details (client_id,
                                  client_secret,
                                  authorized_grant_types,
                                  scope,
                                  web_server_redirect_uri,
                                  authorities,
                                  access_token_validity,
                                  refresh_token_validity,
                                  additional_information,
                                  autoapprove)
VALUES ('coffee-client', -- client-id
        '$2a$10$BMlvMLPJK0peQyVG31NcJOEzQlGF.9rYGOSYi6hfdv/YoFq81FStS', -- hash client secret: zxc
        'password,refresh_token', -- grant types
        'admin', -- scope
        null,
        null,
        3600,
        36000,
        null,
        true);

INSERT INTO users (id, username, email, phone_number, password, enabled)
VALUES (1, 'admin', 'mrrovergame@gmail.ru', '+7 (978)-750-32-36',
        '$2a$10$oUcsyGINmYCvnsNPHa1Qme7ca764qCGjGnao3R3mBVfIxwkwNoZH.', true);
INSERT INTO authorities (username, authority)
VALUES ('admin', 'ADMIN');