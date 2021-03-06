CREATE DATABASE goit.homework8;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS producers(
	id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
	name VARCHAR(1000) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS products(
	id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
	name VARCHAR(1000) NOT NULL,
	price NUMERIC NOT NULL,
	producer_id UUID,
	FOREIGN KEY (producer_id) REFERENCES producers
);

CREATE TABLE IF NOT EXISTS users(
	id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
	email VARCHAR(1000) NOT NULL UNIQUE,
	password VARCHAR(1000) NOT NULL,
	first_name VARCHAR(1000) NOT NULL,
	last_name VARCHAR(1000) NOT NULL,
	user_role VARCHAR(1000) NOT NULL
);
