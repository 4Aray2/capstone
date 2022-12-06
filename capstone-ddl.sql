CREATE TABLE customer ( 
	id serial primary key,
	customer_name varchar(50) not null,
	email varchar(255) unique not null,
	phone_number varchar(20) unique not null,
	username varchar(50) unique not null,
	password varchar(255) not null
);

CREATE TABLE stock (
	id serial primary key,
	stock_name varchar(50) not null, 
	address varchar(50) not null,
	customer_id integer not null,
	foreign key (customer_id) references customer (id)
);

CREATE TABLE item (
    id serial primary key,
    item_name varchar(50) not null,
    description varchar(100)
);

CREATE TABLE stock_item (
    id serial primary key,
    quantity integer not null,
    item_location varchar(100) not null,
    stock_id integer not null,
    item_id integer not null,
    foreign key (stock_id) references stock (id),
    foreign key (item_id) references item (id)
);

----

CREATE TABLE role (
	id serial primary key,
	role_name varchar(50) not null,
	description varchar(100)
);

CREATE TABLE customer_role (
	customer_id integer not null,
	role_id integer not null,
	primary key (customer_id, role_id),
    foreign key (customer_id) references customer (id),
    foreign key (role_id) references role (id)
);
