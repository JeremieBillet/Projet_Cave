package com.intiformation.projet.caveavin.modele;

/**
 * modèle de données pour la catégorie 'Pays'
 * @author yannis
 *
 */

public class CatPays extends Categorie{

	/*________________ PROPS _________________*/


	/*________________ CTORS _________________*/
	
	public CatPays() {
	}

	public CatPays(int idCategorie, String nomObjetCat) {
		super(idCategorie, nomObjetCat);
	}

	public CatPays(String nomObjetCat) {
		super(nomObjetCat);
	}
	
	/*________________ GETTER / SETTER _________________*/
	
}//end class
