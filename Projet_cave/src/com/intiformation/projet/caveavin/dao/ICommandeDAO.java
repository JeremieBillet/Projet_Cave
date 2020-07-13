package com.intiformation.projet.caveavin.dao;

import java.util.List;

import com.intiformation.projet.caveavin.modele.Commande;

public interface ICommandeDAO extends IGeneralizable<Commande>{

	/**
	 * m�thode sp�cifique � la classe Commande
	 * @return
	 */
	
	public List<Commande> getAllByIdClient(Integer id) ;
	
	public Commande getLastCommand(Integer id);
	
}//end interface
