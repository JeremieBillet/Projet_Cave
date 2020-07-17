package com.intiformation.projet.caveavin.dao;

import java.util.List;

import com.intiformation.projet.caveavin.modele.Champagne;

import categories.CatCepage;
import categories.CatType;

public interface IChampagneDAO extends IGeneralizable<Champagne> {
	
	public List<Champagne> getByType(int idType);
	
	public List<Champagne> getByAnnee(Integer annee);
	
	public List<Champagne> getByCepage(int idCepage);

}//end interface
