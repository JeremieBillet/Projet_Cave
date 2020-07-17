package com.intiformation.projet.caveavin.modele;

/**
 * modèle de données pour la catégorie 'Type' qui correspond à la table 'cat_types' dans la bdd
 * @author yannis
 *
 */

public class CatType {

	/*________________ PROPS _________________*/
	
	private int idType;
	private String nomType;
	private String photoType;
	
	/*________________ CTORS _________________*/
	
	public CatType() {
	}
	
	public CatType(int idType, String nomType, String photoType) {
		this.idType = idType;
		this.nomType = nomType;
		this.photoType = photoType;
	}
	
	public CatType(String nomType, String photoType) {
		this.nomType = nomType;
		this.photoType = photoType;
	}
	
	/*________________ GETTER / SETTER _________________*/
	
	public int getIdType() {
		return idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public String getNomType() {
		return nomType;
	}

	public void setNomType(String nomType) {
		this.nomType = nomType;
	}

	public String getPhotoType() {
		return photoType;
	}

	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}

	
}//end class

