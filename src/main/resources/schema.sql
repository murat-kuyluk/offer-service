drop table if exists accounts;

create table offers(

  id int not null AUTO_INCREMENT,
  description varchar(200) not null,
  price numeric not null,
  currency varchar(100) not null,
  status varchar(10) not null,
  expire_time varchar(40) not null,
  create_date timestamp not null,
  modify_date timestamp,

  PRIMARY KEY ( ID )
);