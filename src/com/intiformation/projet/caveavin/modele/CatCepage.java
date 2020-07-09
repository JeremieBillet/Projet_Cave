package com.intiformation.projet.caveavin.modele;

/**
 * modèle de données pour la catégorie 'Cepage' qui correspond à la table 'cat_cepages' dans la bdd
 * @author yannis 
 *
 */

public class CatCepage {

	/*________________ PROPS _________________*/
	
	private int idCepage;
	private String nomCepage;

	/*________________ CTORS _________________*/
	
	public CatCepage() {
	}
	
	public CatCepage(int idCepage, String nomCepage) {
		this.idCepage = idCepage;
		this.nomCepage = nomCepage;
	}
	
	public CatCepage(String nomCepage) {
		this.nomCepage = nomCepage;
	}

	
	/*________________ GETTER / SETTER _________________*/
	
	public int getIdCepage() {
		return idCepage;
	}

	public void setIdCepage(int idCepage) {
		this.idCepage = idCepage;
	}

	public String getNomCepage() {
		return nomCepage;
	}

	public void setNomCepage(String nomCepage) {
		this.nomCepage = nomCepage;
	}
	
}//end class

