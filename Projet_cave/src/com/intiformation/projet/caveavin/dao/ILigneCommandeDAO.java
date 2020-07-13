package com.intiformation.projet.caveavin.dao;

import java.util.List;

import com.intiformation.projet.caveavin.modele.LigneCommande;

public interface ILigneCommandeDAO extends IGeneralizable<LigneCommande>{

	/**
	 * m�thode sp�cifiques � la classe LigneCommande
	 * @return
	 */
	public List<LigneCommande> getAllByIdCommande(Integer id);
	
}//end interface
