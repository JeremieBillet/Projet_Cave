package com.intiformation.projet.caveavin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.projet.caveavin.modele.CatCepage;

public class CatCepageDAOImpl implements ICatCepageDAO{


	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	/**
	 * ajouter une catégorie 'Cepage' à la bdd
	 */
	@Override
	public boolean add(CatCepage pCatCepage) {

		try {

			ps = this.connection.prepareStatement(" INSERT INTO cat_cepages (nom_cepage) VALUES (?)");

			ps.setString(1, pCatCepage.getNomCepage());

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (CatCepageDAOImpl) Erreur lors de l'excéution de la méthode add() ...");
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
	 * modifier une catégorie 'Cepage' de la bdd
	 */
	@Override
	public boolean update(CatCepage pCatCepage) {
		
		try {

			ps = this.connection.prepareStatement("UPDATE cat_cepages SET nom_cepage=? WHERE id_cepage=?");

			ps.setString(1, pCatCepage.getNomCepage());
			ps.setInt(2, pCatCepage.getIdCepage());

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (CatCepageDAOImpl) Erreur lors de l'excéution de la méthode update() ...");
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
	 * supprimer une catégorie 'Cepage' de la bdd
	 */
	@Override
	public boolean delete(Integer pIdCatCepage) {
		
		try {

			ps = this.connection.prepareStatement("DELETE FROM cat_cepages WHERE id_cepage=?");

			ps.setInt(1, pIdCatCepage);

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (CatCepageDAOImpl) Erreur lors de l'exécution de la méthode deleteById() ...");
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
	 * Afficher la liste des cepages
	 */
	@Override
	public List<CatCepage> getAll() {
		
		try {

			ps = this.connection.prepareStatement("SELECT * FROM cat_cepages");

			rs = ps.executeQuery();

			List<CatCepage> listeCatCepageBDD = new ArrayList<>();
			CatCepage catCepage = null;

			while (rs.next()) {

				catCepage = new CatCepage(rs.getInt(1), rs.getString(2));

				listeCatCepageBDD.add(catCepage);

			} // end while

			return listeCatCepageBDD;

		} catch (SQLException e) {
			System.out.println("... (CatCepageDAOImpl) Erreur lors de l'excéution de la méthode getAll() ...");
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
	public CatCepage getById(Integer pIdCatCepage) {

		try {

			ps = this.connection.prepareStatement("SELECT * FROM cat_cepages WHERE id_cepage=?");

			ps.setInt(1, pIdCatCepage);

			rs = ps.executeQuery();

			CatCepage catCepage = null;

			while (rs.next()) {

				catCepage = new CatCepage(rs.getInt(1), rs.getString(2));

			} // end while

			return catCepage;

		} catch (SQLException e) {
			System.out.println("... (CatCepageDAOImpl) Erreur lors de l'excéution de la méthode getById() ...");
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

