package com.intiformation.projet.caveavin.modele;

public class LigneCommande {

	/*________________ PROPS _________________*/
	
	private int idLigneCommande;
	private int alcoolId;
	private int commandeId;
	private int quantite;
	private int prix;
	
	/*________________ CTORS _________________*/
	
	public LigneCommande(int idLigneCommande, int alcoolId, int commandeId, int quantite, int prix) {
		this.idLigneCommande = idLigneCommande;
		this.alcoolId = alcoolId;
		this.commandeId = commandeId;
		this.quantite = quantite;
		this.prix = prix;
	}
	public LigneCommande(int alcoolId, int commandeId, int quantite, int prix) {
		this.alcoolId = alcoolId;
		this.commandeId = commandeId;
		this.quantite = quantite;
		this.prix = prix;
	}
	public LigneCommande() {
	}	

	/*________________ GETTER / SETTER _________________*/
	
	public int getIdLigneCommande() {
		return idLigneCommande;
	}
	public void setIdLigneCommande(int idLigneCommande) {
		this.idLigneCommande = idLigneCommande;
	}
	public int getAlcoolId() {
		return alcoolId;
	}
	public void setAlcoolId(int alcoolId) {
		this.alcoolId = alcoolId;
	}
	public int getCommandeId() {
		return commandeId;
	}
	public void setCommandeId(int commandeId) {
		this.commandeId = commandeId;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	
}//end class
