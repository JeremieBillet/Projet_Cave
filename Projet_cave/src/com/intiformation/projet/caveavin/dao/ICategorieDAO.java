package com.intiformation.projet.caveavin.dao;

import java.util.List;

import com.intiformation.projet.caveavin.modele.Categorie;

public interface ICategorieDAO extends IGeneralizable<Categorie>{

	//------- M�thodes sp�cifiques � la gestion des cat�gories 
	
	/**
	 * affichage des �l�ments d'une sous cat�gorie (pays, r�gion, type ou cepage) 
	 * @param className : nom de la sous cat�gorie (CatPays, CatRegion, CatType ou CatCepage)
	 * @return
	 */
	public List<Categorie> getByClassName(String className);
	
	public List<Categorie> getAll();

	List<Categorie> getByClassNameAndAlcool(String nameCat, String nameAlcool);
	
	
}//end interface
