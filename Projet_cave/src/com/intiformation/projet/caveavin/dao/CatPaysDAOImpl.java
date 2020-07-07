package com.intiformation.projet.caveavin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.projet.caveavin.modele.CatPays;

public class CatPaysDAOImpl implements ICatPaysDAO{


	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	/**
	 * ajouter une catégorie 'Pays' à la bdd
	 */
	@Override
	public boolean add(CatPays pCatPays) {

		try {

			ps = this.connection.prepareStatement(" INSERT INTO cat_pays (nom_pays) VALUES (?)");

			ps.setString(1, pCatPays.getNomPays());

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (CatPaysDAOImpl) Erreur lors de l'excéution de la méthode add() ...");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return false;

	}//end add()

	/**
	 * modifier une catégorie 'Pays' de la bdd
	 */
	@Override
	public boolean update(CatPays pCatPays) {
		
		try {

			ps = this.connection.prepareStatement("UPDATE cat_pays SET nom_pays=? WHERE id_pays=?");

			ps.setString(1, pCatPays.getNomPays());
			ps.setInt(2, pCatPays.getIdPays());

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (CatPaysDAOImpl) Erreur lors de l'excéution de la méthode update() ...");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return false;
		
	}// end update()
	

	/**
	 * supprimer une catégorie 'Pays' de la bdd
	 */
	@Override
	public boolean delete(Integer pIdCatPays) {
		
		try {

			ps = this.connection.prepareStatement("DELETE FROM cat_pays WHERE id_pays=?");

			ps.setInt(1, pIdCatPays);

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (CatPaysDAOImpl) Erreur lors de l'exécution de la méthode deleteById() ...");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return false;
		
	}//end delete()

	/**
	 * Afficher la liste des pays
	 */
	@Override
	public List<CatPays> getAll() {
		
		try {

			ps = this.connection.prepareStatement("SELECT * FROM cat_pays");

			rs = ps.executeQuery();

			List<CatPays> listeCatPaysBDD = new ArrayList<>();
			CatPays catPays = null;

			while (rs.next()) {

				catPays = new CatPays(rs.getInt(1), rs.getString(2));

				listeCatPaysBDD.add(catPays);

			} // end while

			return listeCatPaysBDD;

		} catch (SQLException e) {
			System.out.println("... (CatPaysDAOImpl) Erreur lors de l'excéution de la méthode getAll() ...");
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
		
	}//end getAll()

	/**
	 * rechercher un pays par son id
	 */
	@Override
	public CatPays getById(Integer pIdCatPays) {

		try {

			ps = this.connection.prepareStatement("SELECT * FROM cat_pays WHERE id_pays=?");

			ps.setInt(1, pIdCatPays);

			rs = ps.executeQuery();

			CatPays catPays = null;

			while (rs.next()) {

				catPays = new CatPays(rs.getInt(1), rs.getString(2));

			} // end while

			return catPays;

		} catch (SQLException e) {
			System.out.println("... (CatPaysDAOImpl) Erreur lors de l'excéution de la méthode getById() ...");
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
		
	}//end getById()

	
}//end class

