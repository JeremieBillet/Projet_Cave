package com.intiformation.projet.caveavin.modele;

public class Admin {
	
	/*_____________________________ props _____________________________*/
	private int idAdmin;
	private String userName;
	private String mdp;
	private boolean actived;
	
	/*_____________________________ ctors _____________________________*/
	/**
	 * ctor vide
	 */
	public Admin() {
	}

	/**
	 * ctor sans l'id
	 * @param userName
	 * @param mdp
	 * @param actived
	 */
	public Admin(String userName, String mdp, boolean actived) {
		super();
		this.userName = userName;
		this.mdp = mdp;
		this.actived = actived;
	}

	/**
	 * ctor complet
	 * @param idAdmin
	 * @param userName
	 * @param mdp
	 * @param actived
	 */
	public Admin(int idAdmin, String userName, String mdp, boolean actived) {
		super();
		this.idAdmin = idAdmin;
		this.userName = userName;
		this.mdp = mdp;
		this.actived = actived;
	}

	/*________________________ getters/setters ________________________*/
	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}
	
}//end class
