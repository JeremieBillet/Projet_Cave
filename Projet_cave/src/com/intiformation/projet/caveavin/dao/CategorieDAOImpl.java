package com.intiformation.projet.caveavin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.projet.caveavin.modele.CatCepage;
import com.intiformation.projet.caveavin.modele.CatPays;
import com.intiformation.projet.caveavin.modele.CatRegion;
import com.intiformation.projet.caveavin.modele.CatType;
import com.intiformation.projet.caveavin.modele.Categorie;

public class CategorieDAOImpl implements ICategorieDAO{

	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	
	/**
	 * ajouter un élément d'une catégorie à la bdd
	 */
	@Override
	public boolean add(Categorie pCategorie) {
		
		try {
			
			ps = this.connection.prepareStatement("INSERT INTO categories (class_name, nom, photo) VALUES (?,?,?)");
			
			ps.setString(1, pCategorie.getClass().getSimpleName());
			ps.setString(2, pCategorie.getNomObjetCat());
			
			if (pCategorie instanceof CatType) {
				
				ps.setInt(3, ((CatType)pCategorie).getIdCategorie());
				
			}else {
				
				ps.setInt(3, 0);
				
			}//end else
						
			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (CategorieDAOImpl) Erreur lors de l'exécution de la méthode add() ...");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return false;

	}//end add
	
	
	/**
	 * modifier un element d'une catégorie de la bdd
	 */
	@Override
	public boolean update(Categorie pCategorie) {

		try {
			
			ps = this.connection.prepareStatement("UPDATE categories SET class_name=?, nom=?, photo=? WHERE id_categorie=?");
			
			ps.setString(1, pCategorie.getClass().getSimpleName());
			ps.setString(2, pCategorie.getNomObjetCat());
			
			int idCategorie = pCategorie.getIdCategorie();
			
			if (pCategorie instanceof CatType) {
				
				ps.setString(3, ((CatType)pCategorie).getPhoto());
				
			}else {
				
				ps.setString(3, "");
				
			}//end else
			
			ps.setInt(4, idCategorie);
			
			int verif = ps.executeUpdate();

			return (verif == 1);


		} catch (SQLException e) {
			System.out.println("... (CategorieDAOImpl) Erreur lors de l'excéution de la méthode update() ...");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return false;
		
	}//end update
	
	
	/**
	 * supprimer un élément d'une catégorie de la bdd
	 */
	@Override
	public boolean delete(Integer pIdCategorie) {

		try {

			ps = this.connection.prepareStatement("DELETE FROM categories WHERE id_categorie=?");

			ps.setInt(1, pIdCategorie);

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (CategorieDAOImpl) Erreur lors de l'exécution de la méthode deleteById() ...");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return false;
		
	}//end delete
	
	
	/**
	 * Afficher la liste des elements des catégories (l'ensemble de la table catégorie)
	 */
	@Override
	public List<Categorie> getAll() {

		try {

			ps = this.connection.prepareStatement("SELECT * FROM categories");

			rs = ps.executeQuery();
			
			List<Categorie> listeCategorieBDD = new ArrayList<>();
			Categorie categorie = null;

			while (rs.next()) {

				categorie = new Categorie(rs.getInt(1),rs.getString(3));

				listeCategorieBDD.add(categorie);

			} // end while

			return listeCategorieBDD;

		} catch (SQLException e) {
			System.out.println("... (CategorieDAOImpl) Erreur lors de l'excéution de la méthode getAll() ...");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return null;
		
	}//end getAll
	
	
	/**
	 * rechercher un element d'une catégorie par son ID
	 */
	@Override
	public Categorie getById(Integer pIdCategorie) {

		try {

			ps = this.connection.prepareStatement("SELECT * FROM categories WHERE id_categorie=?");

			ps.setInt(1, pIdCategorie);

			rs = ps.executeQuery();

			rs.next();

			if (rs.getString(2).equals("CatPays")) {
				
				return new CatPays(rs.getInt(1), rs.getString(3)) ;
				
			}else if(rs.getString(2).equals("CatRegion")) {
			
				return new CatRegion(rs.getInt(1), rs.getString(3)) ;
				
			}else if(rs.getString(2).equals("CatCepage")) {
			
				return new CatCepage(rs.getInt(1), rs.getString(3)) ;
				
			}else if(rs.getString(2).equals("CatType")) {
			
				return new CatType(rs.getInt(1), rs.getString(3), rs.getString(4)) ;
				
			}else {
				
				return null;
				
			}
			
		} catch (SQLException e) {
			System.out.println("... (CategorieDAOImpl) Erreur lors de l'excéution de la méthode getById() ...");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return null;
		
	}//end getById


	/**
	 * affichage des éléments par le nom de la catégorie
	 */
	@Override
	public List<Categorie> getByClassName(String className) {
		
		try {

			ps = this.connection.prepareStatement("SELECT * FROM categories WHERE class_name=?");

			ps.setString(1, className);
			
			rs = ps.executeQuery();
			
			List<Categorie> listeCatBDD = new ArrayList<>();
			
			while (rs.next()) {
				
				if (className.equals("CatPays")) {
					
					listeCatBDD.add(new CatPays(rs.getInt(1),rs.getString(3)));
					
				}else if (className.equals("CatRegion")){
					
					listeCatBDD.add(new CatRegion(rs.getInt(1),rs.getString(3)));
					
				}else if (className.equals("CatCepage")){
				
					listeCatBDD.add(new CatCepage(rs.getInt(1),rs.getString(3)));
					
				}else if (className.equals("CatType")) {
					
					listeCatBDD.add(new CatType(rs.getInt(1),rs.getString(3), rs.getString(4)));
				
				}
				
			}//end while

			return listeCatBDD;
	

		} catch (SQLException e) {
			System.out.println("... (CategorieDAOImpl) Erreur lors de l'excéution de la méthode getByClassName() ...");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return null;
		
	}//end getByClassName
	
	
	/**
	 * 
	 */
	@Override
	public List<Categorie> getByClassNameAndAlcool(String nameCat, String nameAlcool) {
		try {

			ps = this.connection.prepareStatement("select distinct c.id_categorie, c.class_name, c.nom, c.photo from categories as c inner join alcools_categories on id_categorie=categorie_id inner join alcools on id_alcool=alcool_id where alcools.class_name=? and c.class_name=?");
			ps.setString(1, nameAlcool);
			ps.setString(2, nameCat);
			
			rs = ps.executeQuery();
			
			List<Categorie> listeCat = new ArrayList<>();
			
			
			while(rs.next()) {
				if (rs.getString(2).equals("CatPays")) {
					listeCat.add(new CatPays(rs.getInt(1), rs.getString(3)));
					
				}else if (rs.getString(2).equals("CatRegion")){
					listeCat.add(new CatRegion(rs.getInt(1), rs.getString(3)));
					
				}else if (rs.getString(2).equals("CatCepage")){
					listeCat.add(new CatCepage(rs.getInt(1), rs.getString(3)));
					
				}else if (rs.getString(2).equals("CatType")) {
					listeCat.add(new CatType(rs.getInt(1), rs.getString(3), rs.getString(4)));
				}
			}//end while	
			
			return listeCat;
			
		} catch (SQLException e) {
			System.out.println("... (CategorieDAOImpl) Erreur lors de l'excéution de la méthode getByClassName() ...");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return null;
	}//end getByClassNameAndAlcool


}//end class
