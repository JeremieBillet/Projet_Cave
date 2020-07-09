# creation BDD
drop database if exists db_projet_cave;
create database db_projet_cave;
use db_projet_cave;

drop table if exists alcools, cat_pays, cat_regions, cat_types, cat_cepages, cepages_alcools;

create table cat_pays (id_pays int auto_increment primary key,
					   nom_pays varchar(50)
                       );
                       
create table cat_regions (id_region int auto_increment primary key,
						  nom_region varchar(50)
						  );
                       
create table cat_cepages (id_cepage int auto_increment primary key,
						  nom_cepage varchar(50)
						  );
                       
create table cat_types (id_type int auto_increment primary key,
					   nom_type varchar(50),
                       photo_type varchar(150)
                       );
                       
                       
create table alcools (id_alcool int auto_increment primary key,
					  class_name varchar(50),
					  designation varchar(100),
                      description varchar(500),
                      prix double,
                      quantité int,
                      selectionne boolean,
                      photo blob,
                      promo double,
                      annee int,
                      pays_id int,
					  region_id int,
                      type_id int,
                      
			foreign key (pays_id) references cat_pays(id_pays),
            foreign key (region_id) references cat_regions(id_region),
            foreign key (type_id) references cat_types(id_type)
            );
                      
                      
create table cepages_alcools (alcool_id int not null,
							  cepage_id int not null,
                       
			foreign key (alcool_id) references alcools(id_alcool) on delete cascade,
            foreign key (cepage_id) references cat_cepages(id_cepage) on delete cascade
            );


create table users (id_user int auto_increment primary key,
					user_name varchar(50) unique,
                    mot_de_passe varchar(50),
                    actived boolean);
                    
create table commandes (id_commande int auto_increment primary key,
						date_commande timestamp default current_timestamp);

                       
                       
insert into cat_pays (nom_pays) values ('France') ;  
insert into cat_regions (nom_region) values ('Médoc');
insert into cat_types ( nom_type, photo_type) values ('rouge', '');  
insert into cat_cepages( nom_cepage) values ('cabernet franc'), ('merlot');  
 
#pour ajouter un produit
insert into alcools (designation, description, prix, quantité, selectionne, photo, promo, pays_id, region_id, type_id) values ('chateau Margot', 'lorem', 150, 60, 0, '', 0, 1, 1, 1);
insert into cepages_alcools values (1,1), (1,2);

#pour recupérer le produit et la liste des cépages
select * from alcools, cat_pays, cat_regions, cat_types where id_alcool=1 and pays_id=id_pays and region_id=id_region and type_id=id_type;
select * from cat_cepages inner join cepages_alcools on cepage_id=id_cepage where alcool_id=1;

select * from alcools, cat_pays, cat_regions, cat_types, cat_cepages inner join cepages_alcools on cepage_id=id_cepage where alcool_id=1 and pays_id=id_pays and region_id=id_region and type_id=id_type;
                       
select * from cepages_alcools;