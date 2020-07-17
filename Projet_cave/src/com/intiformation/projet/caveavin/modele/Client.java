package com.intiformation.projet.caveavin.modele;

public class Client {
	
	/*_____________________________ props _____________________________*/
	private int idClient;
	private String nomClient;
	private String adresse;
	private String email;
	private String telephone;
	private String motDePasse;
	
	/*_____________________________ ctors _____________________________*/
	/**
	 * ctor vide
	 */
	public Client() {
	}

	/**
	 * ctor sans l'id
	 * @param nomClient
	 * @param adresse
	 * @param email
	 * @param telephone
	 * @param motDePasse
	 */
	public Client(String nomClient, String adresse, String email, String telephone, String motDePasse) {
		super();
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.email = email;
		this.telephone = telephone;
		this.motDePasse = motDePasse;
	}

	/**
	 * ctor complet
	 * @param idClient
	 * @param nomClient
	 * @param adresse
	 * @param email
	 * @param telephone
	 * @param motDePasse
	 */
	public Client(int idClient, String nomClient, String adresse, String email, String telephone, String motDePasse) {
		super();
		this.idClient = idClient;
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.email = email;
		this.telephone = telephone;
		this.motDePasse = motDePasse;
	}

	/*________________________ getters/setters ________________________*/
	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
}//end class
