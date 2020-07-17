package com.intiformation.projet.caveavin.dao;

import java.util.List;

import com.intiformation.projet.caveavin.tool.DBConnection;
import com.mysql.jdbc.Connection;

public interface IGeneralizable<T> {
	
	Connection connection = (Connection) DBConnection.getInstance();

	public boolean add(T t);
	
	public boolean update(T t);
	
	public boolean delete(Integer id);
	
	public T getById(Integer id);
	
	public T getByNom(String nom);
	
	public List<T> getAll();
	
}//end interface
