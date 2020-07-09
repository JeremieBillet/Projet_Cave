package com.intiformation.projet.caveavin.modele;

/**
 * modèle de données pour la catégorie 'Pays' qui correspond à la table 'cat_pays' dans la bdd
 * @author yannis
 *
 */

public class CatPays {

	/*________________ PROPS _________________*/
	
	private int idPays;
	private String nomPays;

	/*________________ CTORS _________________*/
	
	public CatPays() {
	}
	
	public CatPays(int idPays, String nomPays) {
		this.idPays = idPays;
		this.nomPays = nomPays;
	}
	
	public CatPays(String nomPays) {
		this.nomPays = nomPays;
	}
	
	/*________________ GETTER / SETTER _________________*/
	

	public int getIdPays() {
		return idPays;
	}

	public void setIdPays(int idPays) {
		this.idPays = idPays;
	}

	public String getNomPays() {
		return nomPays;
	}

	public void setNomPays(String nomPays) {
		this.nomPays = nomPays;
	}
	
}//end class
