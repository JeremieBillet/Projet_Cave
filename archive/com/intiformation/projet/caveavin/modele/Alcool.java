package com.intiformation.projet.caveavin.modele;

public class Alcool {
	
	/*_______________________________ props _______________________________*/
	private int idProduit;
	private String designation;
	private String description;
	private double prix;
	private int quantite;
	private boolean selectionne;
	private byte[] photo;
	private double promo;
	
	/*_______________________________ ctors _______________________________*/
	/**
	 * ctor vide
	 */
	public Alcool() {
	}

	/**
	 * ctor sans id, sans selectionne et sans promo
	 * @param designation
	 * @param description
	 * @param prix
	 * @param quantite
	 * @param photo
	 */
	public Alcool(String designation, String description, double prix, int quantite, byte[] photo) {
		super();
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.photo = photo;
	}

	/**
	 * ctor sans id, sans selectionne
	 * @param designation
	 * @param description
	 * @param prix
	 * @param quantite
	 * @param photo
	 * @param promo
	 */
	public Alcool(String designation, String description, double prix, int quantite, byte[] photo, double promo) {
		super();
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.photo = photo;
		this.promo = promo;
	}

	/**
	 * ctor avec tous les attributs
	 * @param idProduit
	 * @param designation
	 * @param description
	 * @param prix
	 * @param quantite
	 * @param selectionne
	 * @param photo
	 * @param promo
	 */
	public Alcool(int idProduit, String designation, String description, double prix, int quantite, boolean selectionne,
			byte[] photo, double promo) {
		super();
		this.idProduit = idProduit;
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
		this.promo = promo;
	}
	
	/*__________________________ getters/setters __________________________*/
	public int getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
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

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public double getPromo() {
		return promo;
	}

	public void setPromo(double promo) {
		this.promo = promo;
	}
	

}//end class
