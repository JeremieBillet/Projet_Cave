package com.intiformation.projet.caveavin.modele;

public class Categorie {

	/*________________ PROPS _________________*/
	
	private int idCategorie;
	private String nomObjetCat;
	
	/*________________ CTORS _________________*/
	
	public Categorie() {
	}

	public Categorie(int idCategorie, String nomObjetCat) {
		super();
		this.idCategorie = idCategorie;
		this.nomObjetCat = nomObjetCat;
	}

	public Categorie(String nomObjetCat) {
		super();
		this.nomObjetCat = nomObjetCat;
	}

	/*________________ GETTER / SETTER _________________*/
	
	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getNomObjetCat() {
		return nomObjetCat;
	}

	public void setNomObjetCat(String nomObjetCat) {
		this.nomObjetCat = nomObjetCat;
	}

	
}//end class
