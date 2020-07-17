package com.intiformation.projet.caveavin.modele;

public class Biere extends Alcool {

	/*_______________________________ props _______________________________*/
	// les cat seront à redéfinir
	private CatPays pays;
	private CatType type;
	
	/*_______________________________ ctors _______________________________*/
	/**
	 * ctor vide
	 */
	public Biere() {
	}

	public Biere(int idProduit, String designation, String description, double prix, int quantite, boolean selectionne,
			byte[] photo, double promo, CatPays pays, CatType type) {
		super(idProduit, designation, description, prix, quantite, selectionne, photo, promo);

		this.pays = pays;
		this.type = type;
	}

	public Biere(String designation, String description, double prix, int quantite, byte[] photo, double promo, CatPays pays, CatType type) {
		super(designation, description, prix, quantite, photo, promo);

		this.pays = pays;
		this.type = type;
	}

	public Biere(String designation, String description, double prix, int quantite, byte[] photo, CatPays pays, CatType type) {
		super(designation, description, prix, quantite, photo);

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
