package com.intiformation.projet.caveavin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * utilitaire pour se connecter à la bdd.<br/>
 * 
 * @author yannis
 *
 */
public class DBConnection {

	// infos connexion bdd
	private static final String DB_URL = "jdbc:mysql://localhost:3306/..........................";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";
	private static final String MYSQL_JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";

	// objet connexion
	private static Connection connection;
	
	/**
	 * ctor en private pour interdire l'instanciation d'un objet.
	 */
	private DBConnection() {
	}//end ctor

	/**
	 * récup d'une connexion vers la bdd
	 * 
	 * @return
	 */
	public static Connection getInstance() {

		if (connection == null) {

			try {

				// chargement du pilote jdbc de mysql
				Class.forName(MYSQL_JDBC_DRIVER_CLASS);

				// récup de la connexion
				connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("... (DBConnection) Erreur lors de la création de la connexion vers la bdd ...");
				e.printStackTrace();
			} // end catch

		} // end if
		
		return connection;

	}// end getInstance()

}// end class

