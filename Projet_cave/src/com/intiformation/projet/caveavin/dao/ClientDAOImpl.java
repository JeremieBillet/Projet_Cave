package com.intiformation.projet.caveavin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.intiformation.projet.caveavin.modele.Client;

public class ClientDAOImpl implements IClientDAO{

	@Override
	public boolean add(Client pClient) {

		PreparedStatement ps = null;
		
		try {
			ps = this.connection.prepareStatement("INSERT INTO clients (nom_client, adresse, email, tel, mot_de_passe) VALUES (?, ?, ?, ?, ?)");
			
			ps.setString(1, pClient.getNomClient());
			ps.setString(2, pClient.getAdresse());
			ps.setString(3, pClient.getEmail());
			ps.setString(4, pClient.getTelephone());
			ps.setString(5, pClient.getMotDePasse());
			
			int verifAdd = ps.executeUpdate();
			
			return (verifAdd == 1);
			
		} catch (SQLException e) {
			System.out.println("... Erreur lors de l'ajout du client - méthode add() ...");
			e.printStackTrace();
			
		} finally {
			
			try {
				if (ps != null) {
					ps.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
			
		}//end finally
		
		return false;
	}//end add() - client

	@Override
	public boolean update(Client pClient) {

		PreparedStatement ps = null;
		
		try {
			ps = this.connection.prepareStatement("UPDATE clients SET nom_client=?, adresse=?, email=?, tel=?, mot_de_passe=? WHERE id_client=?");
			
			ps.setString(1, pClient.getNomClient());
			ps.setString(2, pClient.getAdresse());
			ps.setString(3, pClient.getEmail());
			ps.setString(4, pClient.getTelephone());
			ps.setString(5, pClient.getMotDePasse());
			
			ps.setInt(6, pClient.getIdClient());
			
			int verifUpdate = ps.executeUpdate();
			
			return (verifUpdate == 1);
			
		} catch (SQLException e) {
			System.out.println("... Erreur lors de la modification du client - méthode update() ...");
			e.printStackTrace();
			
		} finally {
			
			try {
				if (ps != null) {
					ps.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
			
		}//end finally
		
		return false;
	}//end update() - client

	@Override
	public boolean delete(Integer pIdClient) {
PreparedStatement ps = null;
		
		try {
			ps = this.connection.prepareStatement("DELETE FROM clients WHERE id_client=?");

			ps.setInt(1, pIdClient);
			
			int verifDelete = ps.executeUpdate();
			
			return (verifDelete == 1);
			
		} catch (SQLException e) {
			System.out.println("... Erreur lors de la suppression du client - méthode delete() ...");
			e.printStackTrace();
			
		} finally {
			
			try {
				if (ps != null) {
					ps.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
			
		}//end finally
		
		return false;
	}//end delete() - client

	@Override
	public boolean isClientExist(String nomClient, String motDePasse) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = this.connection.prepareStatement("SELECT COUNT(id_client) FROM clients WHERE nom_client=? AND mot_de_passe=?");
			
			ps.setString(1, nomClient);
			ps.setString(2, motDePasse);
			
			rs = ps.executeQuery();
			
			rs.next();
			
			int verifClientExists = rs.getInt(1);
			
			return (verifClientExists == 1);
			
		} catch (SQLException e) {
			System.out.println("... Erreur lors de l'exécution de la méthode isClientExists() ...");
			e.printStackTrace();
		}//end catch
		
		return false;
	}//end isClientExist()

}//end class
