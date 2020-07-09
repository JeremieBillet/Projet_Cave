# creation BDD
drop database if exists db_projet_cave;
create database db_projet_cave;
use db_projet_cave;

drop table if exists alcools_categories, lignes_commandes, alcools, categories, users, commandes ;


create table categories (id_categorie int auto_increment primary key,
						 class_name varchar(50),
                         nom varchar(50),
                         photo varchar (150));



create table alcools (id_alcool int auto_increment primary key,
					  class_name varchar(80),
					  designation varchar(100),
                      description varchar(500),
                      prix double,
                      quantité int,
                      selectionne boolean,
                      photo varchar(150),
                      promo double,
                      annee int);
               
               
create table alcools_categories (alcool_id int not null,
								 categorie_id int not null,

			foreign key (alcool_id) references alcools(id_alcool) on delete cascade,
            foreign key (categorie_id) references categories(id_categorie) on delete cascade);
 
                 


create table users (id_user int auto_increment primary key,
					user_name varchar(50) unique,
                    mot_de_passe varchar(50),
                    actived boolean);
                    

create table clients (id_client  int auto_increment primary key,
					  nom_client varchar(50),
                      adresse varchar(100),
                      email varchar(50),
                      tel varchar (15));
                    
create table commandes (id_commande int auto_increment primary key,
						date_commande timestamp default current_timestamp,
                        client_id int,
                        
			foreign key (client_id) references clients(id_client));
                        


create table lignes_commandes ( id_ligne_commande int auto_increment primary key,
								alcool_id int not null,
                                commande_id int not null,
                                quantite int,
                                prix int,
                                
			foreign key (alcool_id) references alcools(id_alcool) on delete cascade,
            foreign key (commande_id) references commandes(id_commande) on delete cascade);
            

                                

# ---------------------------------------------------
                       
insert into categories (class_name, nom) values ('CatPays', 'France') ;  
insert into categories (class_name, nom) values ('CatRegion', 'Medoc') ;
insert into categories (class_name, nom) values ('CatTypes', 'Rouge') ;
insert into categories (class_name, nom) values ('CatCepage', 'Cabernet Franc'), ('CatCepage', 'Merlot') ; 

#pour ajouter un produit
insert into alcools (designation, description, prix, quantité, selectionne, photo, promo) values ('chateau Margot', 'lorem', 150, 60, 0, '', 0);
insert into alcools_categories values (1,1), (1,2), (1,3), (1,4), (1,5);

#pour recupérer le produit et la liste des catégories
select * from alcools where id_alcool=1;
select * from categories inner join alcools_categories on id_categorie=categorie_id where alcool_id=2;

#nouvelle commande
insert into commandes() values ();
insert into lignes_commandes (alcool_id, commande_id, quantite, prix) values (1, 1, 4, 600);

#panier
select designation, alcools.prix, quantite, lignes_commandes.prix  from alcools inner join lignes_commandes on id_alcool = alcool_id where commande_id=1;

select * from alcools;
select * from categories;
SELECT * FROM categories INNER JOIN alcools_categories ON id_categorie=categorie_id WHERE alcool_id=2;