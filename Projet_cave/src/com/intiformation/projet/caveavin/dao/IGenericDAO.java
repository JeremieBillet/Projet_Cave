package com.intiformation.projet.caveavin.dao;

import java.sql.Connection;
import java.util.List;

import com.intiformation.projet.caveavin.util.DBConnection;

/**
 * interface générique de la couche DAO
 * @author yannis
 *
 */

public interface IGenericDAO<T> {

	/*_______________ Récup d'une connection à la BDD ________________*/
	
	public Connection connection = DBConnection.getInstance();
	
	/*_______________ Méthodes génériques à la couche DAO ________________*/
	
	public boolean add(T t);
	

	public boolean update(T t);
	

	public boolean delete(Integer id);
	

	public List<T> getAll();
	

	public T getById(Integer id);
	
	
}//end interface

