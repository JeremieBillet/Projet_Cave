package com.intiformation.projet.caveavin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminDAOImpl implements IAdminDAO{

	@Override
	public boolean isAdminExist(String userName, String motDePasse) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = this.connection.prepareStatement("SELECT COUNT(id_user) FROM users WHERE user_name=? AND mot_de_passe=?");
			
			ps.setString(1, userName);
			ps.setString(2, motDePasse);
			
			rs = ps.executeQuery();
			
			rs.next();
			
			int verifAdminExists = rs.getInt(1);
			
			return (verifAdminExists == 1);
			
		} catch (SQLException e) {
			System.out.println("... Erreur lors de l'exécution de la méthode isAdminExists() ...");
			e.printStackTrace();
		}//end catch
		
		return false;
	}//end isAdminExist()

}//end class
