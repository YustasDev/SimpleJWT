create table if not exists Taco_Order (
    id integer primary key AUTO_INCREMENT,
    delivery_Name varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City varchar(50) not null,
    delivery_State varchar(2) not null,
    delivery_Zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null);

create table if not exists Taco (
    id integer primary key AUTO_INCREMENT,
    nameTaco varchar(50) not null,
    taco_order integer not null,
    taco_order_key integer not null,
    created_at timestamp not null);

create table if not exists Ingredient_Ref (
    id integer primary key AUTO_INCREMENT,
    ingredient varchar(4) not null,
    taco integer not null,
    taco_key integer not null);

create table if not exists Ingredient (
    id varchar(4) primary key not null,
    nameIngredient varchar(25) not null,
    typeIngredient varchar(10) not null);

alter table Taco add foreign key (taco_order) references Taco_Order(id);
alter table Ingredient_Ref add foreign key (ingredient) references Ingredient(id);