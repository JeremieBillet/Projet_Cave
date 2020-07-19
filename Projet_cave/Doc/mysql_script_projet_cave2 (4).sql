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
					  class_name varchar(50),
					  designation varchar(100),
                      description varchar(1000),
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
                      tel varchar (15),
                      mot_de_passe varchar(20));
                    
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
            

# -----------------------------------------------------------------------------                               
# -----------------------------------------------------------------------------
# -----------------------------------------------------------------------------
#ajout categories
insert into categories (class_name, nom) values ('CatPays', 'France') ;
insert into categories (class_name, nom) values ('CatPays', 'Belgique') ; 
insert into categories (class_name, nom) values ('CatPays', 'Italie') ; 
insert into categories (class_name, nom) values ('CatPays', 'Allemagne') ; 
   
insert into categories (class_name, nom) values ('CatRegion', 'Bordeaux') ;
insert into categories (class_name, nom) values ('CatRegion', 'Pays de la Loire') ;
insert into categories (class_name, nom) values ('CatRegion', 'Alsace') ;
insert into categories (class_name, nom) values ('CatRegion', 'Bourgogne') ;
insert into categories (class_name, nom) values ('CatRegion', 'Italie') ;

insert into categories (class_name, nom) values ('CatType', 'Rouge') ;
insert into categories (class_name, nom) values ('CatType', 'Blanc') ;
insert into categories (class_name, nom) values ('CatType', 'Rosé') ;

insert into categories (class_name, nom) values ('CatType', 'Brut') ;
insert into categories (class_name, nom) values ('CatType', 'Demi-sec') ;

insert into categories (class_name, nom) values ('CatType', 'Cognac') ;
insert into categories (class_name, nom) values ('CatType', 'Whisky') ;
insert into categories (class_name, nom) values ('CatType', 'Gin') ;

insert into categories (class_name, nom) values ('CatType', 'Blonde') ;
insert into categories (class_name, nom) values ('CatType', 'Brune') ;
insert into categories (class_name, nom) values ('CatType', 'Blanche') ;

insert into categories (class_name, nom) values ('CatCepage', 'Cabernet Franc'); 
insert into categories (class_name, nom) values ('CatCepage', 'Pinot noir'); 
insert into categories (class_name, nom) values ('CatCepage', 'Gewurztraminer'); 
insert into categories (class_name, nom) values ('CatCepage', 'Merlot'); 
insert into categories (class_name, nom) values ('CatCepage', 'Chenin'); 
insert into categories (class_name, nom) values ('CatCepage', 'Syrah'); 
insert into categories (class_name, nom) values ('CatCepage', 'Chardonnay');

insert into categories (class_name, nom) values ('CatPays', 'Ecosse') ; 
insert into categories (class_name, nom) values ('CatPays', 'Angleterre') ; 
# -----------------------------------------------------------------------------
#ajout produits vin
insert into alcools (class_name, designation, description, prix, quantité, selectionne, photo, promo) 
		values ('Vin', 'chateau Margot', 
				'Un Château Margaux signe en nous ce qu’est la puissance associée à l’élégance. La première est simplement là pour aider le corps à se mouvoir avec élégance. Le bouquet de ce Château Margaux est d’une infinie complexité, avec ses notes de rose, de pivoine, de fruits noirs, d’épices ; la bouche est fabuleuse de richesse, de suavité, de tension, la finale se révèle longue, délicieuse et rafraîchissante. Ce Château Margaux possède la vertu de faire jaillir les mots pour le décrire. Sa puissance et son amplitude se font dans la douceur, la finale reste fraîche avec une longueur infinie.', 
				640, 60, 0, '/resources/photo/margaux.png', 0);
insert into alcools_categories values (1,1), (1,5), (1,10), (1,21), (1,24);

insert into alcools (class_name, designation, description, prix, quantité, selectionne, photo, promo) 
		values ('Vin', 'Domaine Baudry-Dutour', 
				'C’est un vin aux arômes de fleurs blanches, d’agrumes, d’anis avec des notes un peu toastées. En bouche, il présente une bonne attaque, c’est un vin frais avec une bonne tension, un vin élégant et racé avec une finale un peu vanillée et grillée. Une belle cuvée qui mérite d’attendre un peu dans le verre pour vous dévoiler toute sa complexité !', 
				20, 120, 0, '/resources/photo/chinon.jpg', 0);
insert into alcools_categories values (2,1), (2,6), (2,11), (2,25);

insert into alcools (class_name, designation, description, prix, quantité, selectionne, photo, promo) 
		values ('Vin', 'DOMAINE TRAPET', 
				'Un nectar plein de vigueur qui parvient merveilleusement à associer force et finesse. Offrant des superbes notes de fruits rouges, ce flacon est formidable en bouche, notamment grâce à une splendide acidité et à des tanins à grains fins. Le Domaine Trapet nous montre tout son savoir-faire au travers de cette réalisation. ', 
				150, 25, 0, '/resources/photo/trapet.png', 0);
insert into alcools_categories values (3,1), (3,8), (3,10), (3,21), (3,22);

insert into alcools (class_name, designation, description, prix, quantité, selectionne, photo, promo) 
		values ('Vin', 'MARCEL DEISS', 
				'Doté d’une belle robe jaune dorée qui développe des notes de fruits confits, d’œillets et d’épices, cette vendange tardive offre une magnifique structure et une agréable rondeur. En finale, des notes citronnées apportent une dernière touche de fraîcheur à ce vin puissant. Ce grand vin sera dégusté pour lui-même, et sublimera plus que jamais un foie gras poêlé !', 
				38, 40, 0, '/resources/photo/marcel.png', 0);
insert into alcools_categories values (4,1), (4,7), (4,11), (4,23);


insert into alcools (class_name, designation, description, prix, quantité, selectionne, photo, promo) 
		values ('Vin', 'Donnafugata', 
				'Ce rosé est un vin minéral qui accompagnera parfaitement les gambas flambées au whisky, potiron et crème de coco, le saumon gravlax, le carpaccio de Saint Jacques aux agrumes, un risotto de fruits de mer/chorizo.', 
				18, 35, 0, '/resources/photo/sicilia.jpg', 0);
insert into alcools_categories values (5,3), (5,9), (5,12), (5,22), (5,26);
# -----------------------------------------------------------------------------
#ajout produits champagne
insert into alcools (class_name, designation, description, prix, quantité, selectionne, photo, promo) 
		values ('Champagne', 'Mumm', 
				'Champagne d une grande fraîcheur et d une grande intensité. Des arômes de fruits et des parfums de litchis, ananas ponctué d une note de vanille. Tout ça dans un magnifique flacon au cordon incrusté dans la bouteille', 
				35, 140, 0, '/resources/photo/mumm.png', 0);
insert into alcools_categories values (6,14), (6,22), (6,27);

insert into alcools (class_name, designation, description, prix, quantité, selectionne, photo, promo) 
		values ('Champagne', 'Dom Pérignon', 
				'Le Vintage 2008 Dom Pérignon se dévoile sur un nez éclatant, complexe et lumineux où l’on distingue des notes de fleurs blanches, d’agrumes et de fruits à noyaux. Des notes soulignées par une touche légèrement mentholée. Sur le palais, c’est une cuvée immense. Le Vintage 2008 est élancé, épuré, tonique. Le fruit se déclame haut et clair, et l’acidité, signe distinctif de ce millésime, est ici remarquablement intégrée. La finale évoque les épices et s’achève une touche fumée. Déjà un immense champagne dans sa première plénitude, qui possède un potentiel de garde tout aussi immense.', 
				155, 30, 0, '/resources/photo/perignon.png', 0);
insert into alcools_categories values (7,13), (7,27);
# -----------------------------------------------------------------------------
#ajout produits spriritueux
insert into alcools (class_name, designation, description, prix, quantité, selectionne, photo, promo) 
		values ('Spiritueux', 'Langavulin', 
				'Sa robe est couleur or avec des reflets ambrés. La tourbe est très présente au nez, mais on distingue également un côté marin, iodé ainsi que des notes boisées. En bouche, on retrouve la puissance de la tourbe, mais le côté marin reste bien présent et offre un bel équilibre.', 
				65, 22, 0, '/resources/photo/.png', 0);
insert into alcools_categories values (8,28), (8,16);

insert into alcools (class_name, designation, description, prix, quantité, selectionne, photo, promo) 
		values ('Spiritueux', 'Bombay Sapphire', 
				'La manifestation la plus audacieuse du style particulier de Bombay Sapphire. Deux autres, soigneusement botanicals sélectionnés puis distillée à une saveur plus intense. L ajout d une dimension plus riche de la recette initiale. Graines d hibiscus, seulement de l Equateur. Et bergamote soigneusement séché, le zeste d orange récolté à la main en Calabre. Pour maintenir l intégrité des 12 plantes, le processus de faible perfusion de vapeur et distillée plus. Assurer les riches arômes et des saveurs plus intenses.', 
				40, 32, 0, '/resources/photo/bombay.jpg', 0);
insert into alcools_categories values (9, 29), (9,17);

insert into alcools (class_name, designation, description, prix, quantité, selectionne, photo, promo) 
		values ('Spiritueux', 'Hennessy', 
				'Issu d un assemblage de quarante eaux-de-vie des plus prestigieux terroirs de Cognac, sa belle robe ambrée aux reflets dorés vous séduira, tout comme ses arômes délicats et très expressifs. A déguster pour le plus grand plaisir de vos papilles !', 
				60, 30, 0, '/resources/photo/hennessy.png', 0);
insert into alcools_categories values (10,1), (10,15);
# -----------------------------------------------------------------------------
#ajout produit Biere
insert into alcools (class_name, designation, description, prix, quantité, selectionne, photo, promo) 
		values ('Biere', 'Kwak', 
				'Quelque chose de dense et le maltose . Au début, un peu sucré, mais la fin est sèche et amère .', 
				5, 55, 0, '/resources/photo/kwak.jpg', 0);
insert into alcools_categories values (11,2), (11,18);

insert into alcools (class_name, designation, description, prix, quantité, selectionne, photo, promo) 
		values ('Biere', 'Mont Blanc', 
				'Cette bière blanche de la Brasserie du Mont Blanc est une bière de couleur jaune pâle tirant sur le blanc qui dégage des arômes épicés et fruités de coriandre et d agrumes. Des notes rappelant la présence du blé sont également perceptibles. On retrouve ses arômes en bouche avec des saveurs prononcées d agrumes et de coriandre.', 
				3, 62, 0, '/resources/photo/montblanc.png', 0);
insert into alcools_categories values (12,1), (12,20);

insert into alcools (class_name, designation, description, prix, quantité, selectionne, photo, promo) 
		values ('Biere', 'Paulaner', 
				'Dans le verre, cette bière allemande nous propose une étonnante robe brune aux reflets rougeâtres, surmontée d une très belle mousse colorée. Si le nez nous dévoile des arômes puissants sur la banane et le clou de girofle, on retrouve en bouche une très belle intensité sur le caramel. Le final est à la fois malté et fruité.', 
				3, 24, 0, '/resources/photo/paulaner.png', 0);
insert into alcools_categories values (13,4), (13,19);

# -----------------------------------------------------------------------------
#ajout client
insert into clients values ();
insert into clients values ();
select * from clients;
# -----------------------------------------------------------------------------
#ajout ligne commande
insert into lignes_commandes (alcool_id, commande_id, quantite) values (1,1,2);
insert into lignes_commandes (alcool_id, commande_id, quantite) values (1,2,1), (2,2,2);
insert into lignes_commandes (alcool_id, commande_id, quantite) values (3,3,5), (7,3,1);

# -----------------------------------------------------------------------------
#ajout commande
insert into commandes (client_id) values (1);
insert into commandes (client_id) values (1);
insert into commandes (client_id) values (2);

# -----------------------------------------------------------------------------
#ajout user
insert into users (user_name, mot_de_passe) values ('admin', 'admin');

# -----------------------------------------------------------------------------
# -----------------------------------------------------------------------------
# -----------------------------------------------------------------------------

#pour recupérer le produit et la liste des catégories
select * from alcools where id_alcool=11;
select * from categories inner join alcools_categories on id_categorie=categorie_id where alcool_id = 13;

#nouvelle commande
insert into commandes() values ();
insert into lignes_commandes (alcool_id, commande_id, quantite, prix) values (1, 1, 4, 600);

#panier
select designation, alcools.prix, quantite, lignes_commandes.prix  from alcools inner join lignes_commandes on id_alcool = alcool_id where commande_id=1;

select * from categories;
#SELECT * FROM categories INNER JOIN alcools_categories ON id_categorie=categorie_id WHERE alcool_id=2;