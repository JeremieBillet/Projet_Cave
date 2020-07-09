package com.intiformation.projet.caveavin.modele;

/**
 * modèle de données pour la catégorie 'Region' qui correspond à la table 'cat_regions' dans la bdd
 * @author yannis
 *
 */

public class CatRegion {

	/*________________ PROPS _________________*/
	
	private int idRegion;
	private String nomRegion;
		
	/*________________ CTORS _________________*/
	
	public CatRegion() {
	}

	public CatRegion(int idRegion, String nomRegion) {
		this.idRegion = idRegion;
		this.nomRegion = nomRegion;
	}

	public CatRegion(String nomRegion) {
		this.nomRegion = nomRegion;
	}

	/*________________ GETTER / SETTER _________________*/
	

	public int getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}

	public String getNomRegion() {
		return nomRegion;
	}

	public void setNomRegion(String nomRegion) {
		this.nomRegion = nomRegion;
	}
	
	
}//end class
