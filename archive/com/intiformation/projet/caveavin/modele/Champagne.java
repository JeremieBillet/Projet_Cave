package com.intiformation.projet.caveavin.modele;

public class Champagne extends Alcool {
	
	/*_______________________________ props _______________________________*/
	// les cat seront � red�finir
	private CatType type;
	private CatCepage[] cepage; //ou listCepage
	
	private int annee;
	
	/*_______________________________ ctors _______________________________*/
	/**
	 * ctor vide
	 */
	public Champagne() {
	}

	public Champagne(int idProduit, String designation, String description, double prix, int quantite,
			boolean selectionne, byte[] photo, double promo, CatType type, CatCepage[] cepage, int annee) {
		super(idProduit, designation, description, prix, quantite, selectionne, photo, promo);
		
		this.type = type;
		this.cepage = cepage;
		this.annee = annee;
	}

	public Champagne(String designation, String description, double prix, int quantite, byte[] photo, double promo, CatType type, CatCepage[] cepage, int annee) {
		super(designation, description, prix, quantite, photo, promo);

		this.type = type;
		this.cepage = cepage;
		this.annee = annee;
	}

	public Champagne(String designation, String description, double prix, int quantite, byte[] photo, CatType type, CatCepage[] cepage, int annee) {
		super(designation, description, prix, quantite, photo);

		this.type = type;
		this.cepage = cepage;
		this.annee = annee;
	}
	
	/*__________________________ getters/setters __________________________*/
	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public CatType getType() {
		return type;
	}

	public void setType(CatType type) {
		this.type = type;
	}

	public CatCepage[] getCepage() {
		return cepage;
	}

	public void setCepage(CatCepage[] cepage) {
		this.cepage = cepage;
	}

}//end class
