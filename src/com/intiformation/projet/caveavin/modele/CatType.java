package com.intiformation.projet.caveavin.modele;

/**
 * modèle de données pour la catégorie 'Type'
 * @author yannis
 *
 */

public class CatType extends Categorie{

	/*________________ PROPS _________________*/
	
	private String photo;
	
	/*________________ CTORS _________________*/
	
	public CatType() {
	}

	public CatType(int idCategorie, String nomObjetCat, String photo) {
		super(idCategorie, nomObjetCat);
		this.photo = photo;
	}
	
	public CatType(String nomObjetCat) {
		super(nomObjetCat);
	}

	/*________________ GETTER / SETTER _________________*/

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}//end class

