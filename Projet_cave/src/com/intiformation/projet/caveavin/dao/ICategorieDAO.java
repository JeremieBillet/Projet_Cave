package com.intiformation.projet.caveavin.dao;

import java.util.List;

import com.intiformation.projet.caveavin.modele.Categorie;

public interface ICategorieDAO extends IGeneralizable<Categorie>{

	//------- Méthodes spécifiques à la gestion des catégories 
	
	/**
	 * affichage des éléments d'une sous catégorie (pays, région, type ou cepage) 
	 * @param className : nom de la sous catégorie (CatPays, CatRegion, CatType ou CatCepage)
	 * @return
	 */
	public List<Categorie> getByClassName(String className);
	
	public List<Categorie> getAll();

	List<Categorie> getByClassNameAndAlcool(String nameCat, String nameAlcool);
	
	
}//end interface
