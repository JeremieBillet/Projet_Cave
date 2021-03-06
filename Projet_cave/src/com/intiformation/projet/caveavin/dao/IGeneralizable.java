package com.intiformation.projet.caveavin.dao;

import java.sql.Connection;
import java.util.List;

import com.intiformation.projet.caveavin.tool.DBConnection;

public interface IGeneralizable<T> {
	
	Connection connection = (Connection) DBConnection.getInstance();
	
	public boolean add(T t);
	
	public boolean update(T t);
	
	public boolean delete(Integer id);
	
	public T getById(Integer id);
	
	

}//end interface
