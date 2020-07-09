package com.intiformation.projet.caveavin.modele;

import com.intiformation.projet.caveavin.modele.CatPays;
import com.intiformation.projet.caveavin.modele.CatType;

public class Spiritueux extends Alcool {
	
	/*_______________________________ props _______________________________*/
	// les cat seront à redéfinir
	private CatPays pays;
	private CatType type;
	
	/*_______________________________ ctors _______________________________*/
	/**
	 * ctor vide
	 */
	public Spiritueux() {
	}

	public Spiritueux(int idProduit, String classe, String designation, String description, double prix, int quantite, boolean selectionne,
			byte[] photo, double promo, CatPays pays, CatType type) {
		super(idProduit, classe, designation, description, prix, quantite, selectionne, photo, promo);

		this.pays = pays;
		this.type = type;
	}

	public Spiritueux(String classe, String designation, String description, double prix, int quantite, byte[] photo, double promo, CatPays pays, CatType type) {
		super(classe, designation, description, prix, quantite, photo, promo);

		this.pays = pays;
		this.type = type;
	}

	/*__________________________ getters/setters __________________________*/
	public CatPays getPays() {
		return pays;
	}

	public void setPays(CatPays pays) {
		this.pays = pays;
	}

	public CatType getType() {
		return type;
	}

	public void setType(CatType type) {
		this.type = type;
	}
	
}//end class
