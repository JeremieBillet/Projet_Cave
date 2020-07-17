package com.intiformation.projet.caveavin.dao;

import java.sql.Connection;

import com.intiformation.projet.caveavin.tool.DBConnection;

public interface IAdminDAO {
	
	Connection connection = (Connection) DBConnection.getInstance();
	
	public boolean isAdminExist(String userName, String motDePasse);

}//end interface
