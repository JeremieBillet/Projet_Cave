package com.intiformation.projet.caveavin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.projet.caveavin.modele.Commande;

public class CommandeDAOImpl implements ICommandeDAO{

	private PreparedStatement ps = null;
	private ResultSet rs = null;
		
	/**
	 * Ajouter une commande pass�e dans la liste des commandes du client
	 */
	@Override
	public boolean add(Commande pCommande) {
		
		try {

			ps = this.connection
					.prepareStatement(" INSERT INTO commandes (client_id) VALUES (?)");

			ps.setInt(1, pCommande.getClientId());

			int verif = ps.executeUpdate();

			return (verif == 1);

		} catch (SQLException e) {
			System.out.println("... (CommandeDAOImpl) Erreur lors de l'exc�ution de la m�thode add() ...");
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
	 * modifier une commande pass�e
	 */
	@Override
	public boolean update(Commande t) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * supprimer une commande pass�e
	 */
	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Retrouver une commande pass�e par l'id de la commande 
	 */
	@Override
	public Commande getById(Integer pIdCommande) {
		
		try {

			ps = this.connection.prepareStatement("SELECT * FROM commandes WHERE id_commande=?");

			ps.setInt(1, pIdCommande);

			rs = ps.executeQuery();

			rs.next();

			return new Commande(rs.getInt(1), rs.getTimestamp(2), rs.getInt(3));

		} catch (SQLException e) {
			System.out.println("... (CommandeDAOImpl) Erreur lors de l'exc�ution de la m�thode getById() ...");
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
	 * afficher la liste des commandes par l'id d'un client
	 */
	@Override
	public List<Commande> getAllByIdClient(Integer pIdClient) {

		try {
			
			ps = this.connection.prepareStatement("SELECT * FROM commandes WHERE client_id = ?");
			
			ps.setInt(1, pIdClient);
			
			rs = ps.executeQuery();
			
			List<Commande> listeCommandeBDD = new ArrayList<>();
			Commande commande = null;
			
			while(rs.next()) {
				
				commande = new Commande(rs.getInt(1), rs.getTimestamp(2), rs.getInt(3));
				
				listeCommandeBDD.add(commande);
				
			}//end while
			
			return listeCommandeBDD;
			
		} catch (SQLException e) {
			System.out.println("... (CommandeDAOImpl) Erreur lors de l'exc�ution de la m�thode getAllByIdClient() ...");
			e.printStackTrace();
			
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
		}//end finally
		
		return null;
		
	}//end getAllByIdClient


	/**
	 * r�cup de la derni�re commande pass�e par le client 
	 */
	@Override
	public Commande getLastCommand() {
		
		try {

			ps = this.connection.prepareStatement("SELECT max(id_commande) FROM commandes");
			rs = ps.executeQuery();
			rs.next();

			return getById(rs.getInt(1));

		} catch (SQLException e) {
			System.out.println("... (CommandeDAOImpl) Erreur lors de l'exc�ution de la m�thode getLastCommand() ...");
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
		
	}//end getLastCommand
	
}//end class
