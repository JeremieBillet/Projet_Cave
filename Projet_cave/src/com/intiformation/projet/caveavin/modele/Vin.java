package com.intiformation.projet.caveavin.modele;

import java.util.List;

import com.intiformation.projet.caveavin.modele.CatCepage;
import com.intiformation.projet.caveavin.modele.CatPays;
import com.intiformation.projet.caveavin.modele.CatRegion;
import com.intiformation.projet.caveavin.modele.CatType;

public class Vin extends Alcool {
	
	/*_______________________________ props _______________________________*/
	// les cat seront � red�finir
	private CatPays pays;
	private CatRegion region;
	private CatType type;
	private List<CatCepage> cepage; //ou listCepage
	
	private int annee;
	
	/*_______________________________ ctors _______________________________*/
	/**
	 * ctor vide
	 */
	public Vin() {
	}

	public Vin(int idProduit, String classe, String designation, String description, double prix, int quantite, boolean selectionne,
			String photo, double promo, CatPays pays, CatRegion region, CatType type, List<CatCepage> cepage, int annee) {
		super(idProduit, classe, designation, description, prix, quantite, selectionne, photo, promo);

		this.pays = pays;
		this.region = region;
		this.type = type;
		this.cepage = cepage;
		this.annee = annee;
	}

	public Vin(String classe, String designation, String description, double prix, int quantite, String photo, double promo, CatPays pays, CatRegion region, CatType type, List<CatCepage> cepage, int annee) {
		super(classe, designation, description, prix, quantite, photo, promo);

		this.pays = pays;
		this.region = region;
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

	public CatPays getPays() {
		return pays;
	}

	public void setPays(CatPays pays) {
		this.pays = pays;
	}

	public CatRegion getRegion() {
		return region;
	}

	public void setRegion(CatRegion region) {
		this.region = region;
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
