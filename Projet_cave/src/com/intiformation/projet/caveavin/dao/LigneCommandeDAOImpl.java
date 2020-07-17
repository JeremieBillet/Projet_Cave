package com.intiformation.projet.caveavin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.projet.caveavin.modele.LigneCommande;

public class LigneCommandeDAOImpl implements ILigneCommandeDAO {

	private PreparedStatement ps = null;
	private ResultSet rs = null;

	/**
	 * ajout d'un produit à la ligne de commande
	 */
	@Override
	public boolean add(LigneCommande pLigneCommande) {

		try {

			ps = this.connection.prepareStatement(
					" INSERT INTO lignes_commandes (alcool_id, commande_id, quantite, prix) VALUES (?,?,?,?)");

			ps.setInt(1, pLigneCommande.getAlcoolId());
			ps.setInt(2, pLigneCommande.getCommandeId());
			ps.setInt(3, pLigneCommande.getQuantite());
			ps.setDouble(4, pLigneCommande.getPrix());

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (LigneCommandeDAOImpl) Erreur lors de l'excéution de la méthode add() ...");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return false;

	}// end add

	/**
	 * modification d'un produit issu de la ligne de commande (modif de la quantité
	 * essentiellement)
	 */
	@Override
	public boolean update(LigneCommande pLigneCommande) {

		try {

			ps = this.connection.prepareStatement("UPDATE lignes_commandes SET quantite=? WHERE id_ligne_commande=?");

			ps.setInt(1, pLigneCommande.getQuantite());
			ps.setInt(2, pLigneCommande.getIdLigneCommande());

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (LigneCommandeDAOImpl) Erreur lors de l'excéution de la méthode update() ...");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return false;

	}// end update

	/**
	 * suppression d'un produit de la ligne de commande
	 */
	@Override
	public boolean delete(Integer pIdLigneCommande) {

		try {

			ps = this.connection.prepareStatement("DELETE FROM lignes_commandes WHERE id_ligne_commande=?");

			ps.setInt(1, pIdLigneCommande);

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (LigneCommandeDAOImpl) Erreur lors de l'exécution de la méthode deleteById() ...");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally

		return false;

	}// end delete

	/**
	 * récup d'un produit à partir de id_ligne_commande (id d'insertion du produit
	 * dans la ligne de commande)
	 */
	@Override
	public LigneCommande getById(Integer pIdLigneCommande) {

		try {

			ps = this.connection.prepareStatement("SELECT * FROM lignes_commandes WHERE id_ligne_commande = ?");

			ps.setInt(1, pIdLigneCommande);

			rs = ps.executeQuery();

			rs.next();

			return new LigneCommande(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5));

		} catch (SQLException e) {
			System.out.println("... (LigneCommandeDAOImpl) Erreur lors de l'excéution de la méthode getById() ...");
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

	}// end getById

	/**
	 * récup de tous les produits issus d'une ligne de commande à partir de l'id de
	 * commande
	 */
	@Override
	public List<LigneCommande> getAllByIdCommande(Integer pIdCommande) {

		try {

			ps = this.connection.prepareStatement("SELECT * FROM lignes_commandes WHERE commande_id = ?");

			ps.setInt(1, pIdCommande);

			rs = ps.executeQuery();

			List<LigneCommande> listeLigneCommandeBDD = new ArrayList<>();
			LigneCommande ligneCommande = null;

			while (rs.next()) {

				ligneCommande = new LigneCommande(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5));

				listeLigneCommandeBDD.add(ligneCommande);

			} // end while

			return listeLigneCommandeBDD;

		} catch (SQLException e) {
			System.out.println(
					"... (LigneCommandeDAOImpl) Erreur lors de l'excéution de la méthode getAllByIdCommande() ...");
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

	}// end getAllByIdCommande

	@Override
	public LigneCommande getLastLigneCommande() {
		try {

			ps = this.connection.prepareStatement("SELECT max(id_ligne_commande) FROM lignes_commandes");
			rs = ps.executeQuery();
			rs.next();

			return getById(rs.getInt(1));

		} catch (SQLException e) {
			System.out.println("... (LigneCommandeDAOImpl) Erreur lors de l'excéution de la méthode getLastLigneCommande() ...");
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
	}

}// end class
