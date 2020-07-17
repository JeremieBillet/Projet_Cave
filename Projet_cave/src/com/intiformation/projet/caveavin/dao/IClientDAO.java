package com.intiformation.projet.caveavin.dao;

import java.sql.Connection;

import com.intiformation.projet.caveavin.modele.Client;
import com.intiformation.projet.caveavin.tool.DBConnection;

public interface IClientDAO {
	
	Connection connection = (Connection) DBConnection.getInstance();
	
	public boolean add(Client pClient);
	
	public boolean update(Client pClient);
	
	public boolean delete(Integer pIdClient);
	
	public boolean isClientExist(String nomClient, String motDePasse);

}//end interface
