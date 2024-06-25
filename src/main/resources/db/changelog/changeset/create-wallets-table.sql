--liquibase formatted sql

--changeset liquibase-docs:sql-1
CREATE TABLE wallets(
    id SERIAL PRIMARY KEY,
    walletname text UNIQUE NOT NULL,
    walletbalance numeric(10,2)
);

--changeset liquibase-docs:sql-2
INSERT INTO wallets (walletname, walletbalance) VALUES ('03ef2941-62de-4e20-b736-cb25b0258117', 10000);

--changeset liquibase-docs:sql-3
INSERT INTO wallets (walletname, walletbalance) VALUES ('fabc94ee-d275-4212-8eb2-2809d0dbeabb', 15000);

--changeset liquibase-docs:sql-4
INSERT INTO wallets (walletname, walletbalance) VALUES ('76efd200-799d-4c52-8e48-3e3b7b744059', 20000.15);

--changeset liquibase-docs:sql-5
INSERT INTO wallets (walletname, walletbalance) VALUES ('82edbf6f-4b48-4f93-89ec-bc33558a53c8', 25000.03);

--changeset liquibase-docs:sql-6
INSERT INTO wallets (walletname, walletbalance) VALUES ('684ad249-2afb-41bf-a675-611e6d99dd56', 100000);
