package com.intiformation.projet.caveavin.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * utilitaire pour r�cup�rer une connection vers la bdd
 * @author pifle
 *
 */
public class DBConnection {
	
	// infos de connexion � la bdd
	private static final String DB_URL = "jdbc:mysql://localhost:3306/db_projet_cave";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";
	private static final String JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	
	// insance de la connexion
	private static Connection connection = null;
	
	/**
	 * ctor en private pour intercire l'instanciation d'un objet de la classe
	 */
	private DBConnection() {
		
	}
	
	// m�thode pour r�cup�rer la connexion � la bdd
	/**
	 * permet de r�cup�rer une connexion vers la bdd
	 * @return
	 */
	public static Connection getInstance() {
		
		if (connection == null) {
			
			try {
				// 1. chargment du pilote jdbc de MySQL (sauvegarde du pilote dans le DriverManager
				Class.forName(JDBC_DRIVER_CLASS);
				
				// 2. r�cup de la connexion via le DriverManager
				connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("... (DBConnection) Erreur lors de la r�cup�ration de la connection vers la bdd ...");
				e.printStackTrace();
			}// end catch
			
			
		}//end if
		
		return connection;
		
	}//end getInstance()

}
