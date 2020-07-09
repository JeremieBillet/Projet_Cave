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
			
			ps.setString(1, pCategorie.getClass().getName());
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
			
			ps = this.connection.prepareStatement("UPDATE categories SET class_name=? AND nom=? AND photo=? WHERE id_categorie=?");
			
			ps.setString(1, pCategorie.getClass().getName());
			ps.setString(2, pCategorie.getNomObjetCat());
			ps.setInt(4, pCategorie.getIdCategorie());
			
			if (pCategorie instanceof CatType) {
				
				ps.setInt(3, ((CatType)pCategorie).getIdCategorie());
				
			}else {
				
				ps.setInt(3, 0);
				
			}//end else
			
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

			if (rs.getString(2) == "CatPays") {
				
				return new CatPays(rs.getInt(1), rs.getString(3)) ;
				
			}else if(rs.getString(2) == "CatRegion") {
			
				return new CatRegion(rs.getInt(1), rs.getString(3)) ;
				
			}else if(rs.getString(2) == "CatCepage") {
			
				return new CatCepage(rs.getInt(1), rs.getString(3)) ;
				
			}else if(rs.getString(2) == "CatType") {
			
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
			
			rs.next();
			
			if (className == "CatPays") {
				
				List<CatPays> listeCatPaysBDD = new ArrayList<>();
				CatPays catPays = null;
				
				catPays = new CatPays(rs.getInt(1), rs.getString(3));

				listeCatPaysBDD.add(catPays);
				
			}else if (className == "CatRegion"){
				
				List<CatRegion> listeCatRegionBDD = new ArrayList<>();
				CatRegion catRegion = null;
				
				catRegion = new CatRegion(rs.getInt(1), rs.getString(3));

				listeCatRegionBDD.add(catRegion);
				
			}else if (className == "CatCepage"){
				
				List<CatCepage> listeCatCepageBDD = new ArrayList<>();
				CatCepage catCepage = null;
				
				catCepage = new CatCepage(rs.getInt(1), rs.getString(3));

				listeCatCepageBDD.add(catCepage);
				
			}else if (className == "CatType"){
				
				List<CatType> listeCatTypeBDD = new ArrayList<>();
				CatType catType = null;
				
				catType = new CatType(rs.getInt(1), rs.getString(3), rs.getString(4));

				listeCatTypeBDD.add(catType);
				
			}else {
				
				return null;
				
			}//end else		

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


}//end class
