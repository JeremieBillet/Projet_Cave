package com.intiformation.projet.caveavin.dao;

import java.sql.Connection;
import java.util.List;

import com.intiformation.projet.caveavin.util.DBConnection;

/**
 * interface g�n�rique de la couche DAO
 * @author yannis
 *
 */

public interface IGenericDAO<T> {

	/*_______________ R�cup d'une connection � la BDD ________________*/
	
	public Connection connection = DBConnection.getInstance();
	
	/*_______________ M�thodes g�n�riques � la couche DAO ________________*/
	
	public boolean add(T t);
	

	public boolean update(T t);
	

	public boolean delete(Integer id);
	

	public List<T> getAll();
	

	public T getById(Integer id);
	
	
}//end interface

