package com.intiformation.projet.caveavin.modele;

import java.sql.Timestamp;

public class Commande {

	/*________________ PROPS _________________*/
	
	private int idCommande;
	private Timestamp dateCommande;
	private int clientId;
	
	/*________________ CTORS _________________*/
	
	public Commande(int idCommande, Timestamp dateCommande, int clientId) {
		this.idCommande = idCommande;
		this.dateCommande = dateCommande;
		this.clientId = clientId;
	}
	
	public Commande(Timestamp dateCommande, int clientId) {
		this.dateCommande = dateCommande;
		this.clientId = clientId;
	}
	
	public Commande(int idCommande, Timestamp dateCommande) {
		this.idCommande = idCommande;
		this.dateCommande = dateCommande;
	}
	
	public Commande() {
	}

	/*________________ GETTER / SETTER _________________*/
	

	public int getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}

	public Timestamp getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Timestamp dateCommande) {
		this.dateCommande = dateCommande;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
}//end class
