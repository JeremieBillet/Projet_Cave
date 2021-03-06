package com.intiformation.projet.caveavin.modele;

import java.util.List;

public class Champagne extends Alcool {
	
	/*_______________________________ props _______________________________*/
	// les cat seront � red�finir
	private CatType type;
	private List<CatCepage> cepage; //ou listCepage
	
	private int annee;
	
	/*_______________________________ ctors _______________________________*/
	/**
	 * ctor vide
	 */
	public Champagne() {
	}

	public Champagne(int idProduit, String classe, String designation, String description, double prix, int quantite,
			boolean selectionne, String photo, double promo, CatType type, List<CatCepage> cepage, int annee) {
		super(idProduit, classe, designation, description, prix, quantite, selectionne, photo, promo);
		
		this.type = type;
		this.cepage = cepage;
		this.annee = annee;
	}

	public Champagne(String classe, String designation, String description, double prix, int quantite, String photo, double promo, CatType type, List<CatCepage> cepage, int annee) {
		super(classe, designation, description, prix, quantite, photo, promo);

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

	public List<CatCepage> getCepage() {
		return cepage;
	}

	public void setCepage(List<CatCepage> cepage) {
		this.cepage = cepage;
	}

}//end class
