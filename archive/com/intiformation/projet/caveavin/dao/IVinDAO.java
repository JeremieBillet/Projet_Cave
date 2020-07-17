package com.intiformation.projet.caveavin.dao;

import java.util.List;

import com.intiformation.projet.caveavin.modele.Vin;

import categories.CatCepage;
import categories.CatPays;
import categories.CatRegion;
import categories.CatType;

public interface IVinDAO extends IGeneralizable<Vin>{
	
	public List<Vin> getByPays(int idPays);
	
	public List<Vin> getByRegion(int idRegion);
	
	public List<Vin> getByType(int idType);
	
	public List<Vin> getByAnnee(Integer annee);
	
	public List<Vin> getByCepage(int idCepage);

}//end interface
