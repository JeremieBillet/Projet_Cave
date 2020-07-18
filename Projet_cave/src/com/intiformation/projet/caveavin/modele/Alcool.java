package com.intiformation.projet.caveavin.modele;

public class Alcool {
	
	/*_______________________________ props _______________________________*/
	private int idAlcool;
	private String classe;
	private String designation;
	private String description;
	private double prix;
	private int quantite;
	private boolean selectionne;
	private String photo;
	private double promo;
	
	/*_______________________________ ctors _______________________________*/
	/**
	 * ctor vide
	 */
	public Alcool() {
	}

	/**
	 * ctor sans id, sans selectionne
	 * @param classe
	 * @param designation
	 * @param description
	 * @param prix
	 * @param quantite
	 * @param photo
	 * @param promo
	 */
	public Alcool(String classe, String designation, String description, double prix, int quantite, String photo,
			double promo) {
		super();
		this.classe = classe;
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.photo = photo;
		this.promo = promo;
	}

	/**
	 * ctor avec tous les attributs
	 */
	public Alcool(int idAlcool, String classe, String designation, String description, double prix, int quantite,
			boolean selectionne, String photo, double promo) {
		super();
		this.idAlcool = idAlcool;
		this.classe = classe;
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
		this.promo = promo;
	}

	/*__________________________ getters/setters __________________________*/
	public int getIdAlcool() {
		return idAlcool;
	}

	public void setIdAlcool(int idAlcool) {
		this.idAlcool = idAlcool;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public boolean isSelectionne() {
		return selectionne;
	}

	public void setSelectionne(boolean selectionne) {
		this.selectionne = selectionne;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public double getPromo() {
		return promo;
	}

	public void setPromo(double promo) {
		this.promo = promo;
	}
	
}//end class
