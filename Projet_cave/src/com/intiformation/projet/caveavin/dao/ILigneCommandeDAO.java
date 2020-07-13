package com.intiformation.projet.caveavin.dao;

import java.util.List;

import com.intiformation.projet.caveavin.modele.LigneCommande;

public interface ILigneCommandeDAO extends IGeneralizable<LigneCommande>{

	/**
	 * méthode spécifiques à la classe LigneCommande
	 * @return
	 */
	public List<LigneCommande> getAllByIdCommande(Integer id);
	
}//end interface
