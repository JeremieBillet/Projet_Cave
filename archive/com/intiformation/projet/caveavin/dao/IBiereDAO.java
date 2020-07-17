package com.intiformation.projet.caveavin.dao;

import java.util.List;

import com.intiformation.projet.caveavin.modele.Biere;

import categories.CatPays;
import categories.CatType;

public interface IBiereDAO extends IGeneralizable<Biere>{
	
	public List<Biere> getByPays(int idPays);
	
	public List<Biere> getByType(int idType);

}//end interface
