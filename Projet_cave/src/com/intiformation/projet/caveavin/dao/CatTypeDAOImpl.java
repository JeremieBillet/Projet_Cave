package com.intiformation.projet.caveavin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.projet.caveavin.modele.CatType;

public class CatTypeDAOImpl implements ICatTypeDAO{


	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	/**
	 * ajouter une cat�gorie 'Type' � la bdd
	 */
	@Override
	public boolean add(CatType pCatType) {

		try {

			ps = this.connection.prepareStatement(" INSERT INTO cat_types (nom_type, photo_type) VALUES (?,?)");

			ps.setString(1, pCatType.getNomType());
			ps.setString(2, pCatType.getPhotoType());

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (CatTypeDAOImpl) Erreur lors de l'exc�ution de la m�thode add() ...");
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
	 * modifier une cat�gorie 'Type' de la bdd
	 */
	@Override
	public boolean update(CatType pCatType) {
		
		try {

			ps = this.connection.prepareStatement("UPDATE cat_types SET nom_type=?, photo_type=? WHERE id_type=?");

			ps.setString(1, pCatType.getNomType());
			ps.setString(2, pCatType.getPhotoType());
			ps.setInt(3, pCatType.getIdType());

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (CatTypeDAOImpl) Erreur lors de l'exc�ution de la m�thode update() ...");
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
	 * supprimer une cat�gorie 'Type' de la bdd
	 */
	@Override
	public boolean delete(Integer pIdCatType) {
		
		try {

			ps = this.connection.prepareStatement("DELETE FROM cat_types WHERE id_type=?");

			ps.setInt(1, pIdCatType);

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (CatTypeDAOImpl) Erreur lors de l'ex�cution de la m�thode deleteById() ...");
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
	 * Afficher la liste des types
	 */
	@Override
	public List<CatType> getAll() {
		
		try {

			ps = this.connection.prepareStatement("SELECT * FROM cat_types");

			rs = ps.executeQuery();

			List<CatType> listeCatTypeBDD = new ArrayList<>();
			CatType catType = null;

			while (rs.next()) {

				catType = new CatType(rs.getInt(1), rs.getString(2), rs.getString(3));

				listeCatTypeBDD.add(catType);

			} // end while

			return listeCatTypeBDD;

		} catch (SQLException e) {
			System.out.println("... (CatTypeDAOImpl) Erreur lors de l'exc�ution de la m�thode getAll() ...");
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
	 * rechercher un type par son id
	 */
	@Override
	public CatType getById(Integer pIdCatType) {

		try {

			ps = this.connection.prepareStatement("SELECT * FROM cat_types WHERE id_type=?");

			ps.setInt(1, pIdCatType);

			rs = ps.executeQuery();

			CatType catType = null;

			while (rs.next()) {

				catType = new CatType(rs.getInt(1), rs.getString(2), rs.getString(3));

			} // end while

			return catType;

		} catch (SQLException e) {
			System.out.println("... (CatTypeDAOImpl) Erreur lors de l'exc�ution de la m�thode getById() ...");
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

