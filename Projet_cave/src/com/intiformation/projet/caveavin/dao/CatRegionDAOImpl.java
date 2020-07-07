package com.intiformation.projet.caveavin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.projet.caveavin.modele.CatRegion;

public class CatRegionDAOImpl implements ICatRegionDAO{


	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	/**
	 * ajouter une catégorie 'Region' à la bdd
	 */
	@Override
	public boolean add(CatRegion pCatRegion) {

		try {

			ps = this.connection.prepareStatement(" INSERT INTO catRegion (nomRegion) VALUES (?)");

			ps.setString(1, pCatRegion.getNomRegion());

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (CatRegionDAOImpl) Erreur lors de l'excéution de la méthode add() ...");
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
	 * modifier une catégorie 'Region' de la bdd
	 */
	@Override
	public boolean update(CatRegion pCatRegion) {
		
		try {

			ps = this.connection.prepareStatement("UPDATE catRegion SET nomRegion=? WHERE id_catRegion=?");

			ps.setString(1, pCatRegion.getNomRegion());
			ps.setInt(2, pCatRegion.getIdRegion());

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (CatRegionDAOImpl) Erreur lors de l'excéution de la méthode update() ...");
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
	 * supprimer une catégorie 'Region' de la bdd
	 */
	@Override
	public boolean delete(Integer pIdCatRegion) {
		
		try {

			ps = this.connection.prepareStatement("DELETE FROM catRegion WHERE id_catRegion=?");

			ps.setInt(1, pIdCatRegion);

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (CatRegionDAOImpl) Erreur lors de l'exécution de la méthode deleteById() ...");
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
	 * Afficher la liste des regions
	 */
	@Override
	public List<CatRegion> getAll() {
		
		try {

			ps = this.connection.prepareStatement("SELECT * FROM catRegion");

			rs = ps.executeQuery();

			List<CatRegion> listeCatRegionBDD = new ArrayList<>();
			CatRegion catRegion = null;

			while (rs.next()) {

				catRegion = new CatRegion(rs.getInt(1), rs.getString(2));

				listeCatRegionBDD.add(catRegion);

			} // end while

			return listeCatRegionBDD;

		} catch (SQLException e) {
			System.out.println("... (CatRegionDAOImpl) Erreur lors de l'excéution de la méthode getAll() ...");
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
	 * rechercher une region par son id
	 */
	@Override
	public CatRegion getById(Integer pIdCatRegion) {

		try {

			ps = this.connection.prepareStatement("SELECT * FROM catRegion WHERE id_catRegion=?");

			ps.setInt(1, pIdCatRegion);

			rs = ps.executeQuery();

			CatRegion catRegion = null;

			while (rs.next()) {

				catRegion = new CatRegion(rs.getInt(1), rs.getString(2));

			} // end while

			return catRegion;

		} catch (SQLException e) {
			System.out.println("... (CatRegionDAOImpl) Erreur lors de l'excéution de la méthode getById() ...");
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

