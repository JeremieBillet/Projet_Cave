package com.intiformation.projet.caveavin.controller;

import java.io.Serializable;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.intiformation.projet.caveavin.dao.AdminDAOImpl;
import com.intiformation.projet.caveavin.dao.ClientDAOImpl;
import com.intiformation.projet.caveavin.dao.IAdminDAO;
import com.intiformation.projet.caveavin.dao.IClientDAO;

@ManagedBean(name="AuthentificationBean")
@SessionScoped
public class AuthentificationBean implements Serializable{
	
	/*_________________________ props _________________________*/
	private String identifiant;
	private String password;
	
	private IAdminDAO adminDAO;
	private IClientDAO clientDAO;
	
	private boolean admin;
	private boolean client;
	
	/*_________________________ ctors _________________________*/
	public AuthentificationBean() {
		adminDAO = new AdminDAOImpl();
		clientDAO = new ClientDAOImpl();
	}
	
	/*_______________________ methods _________________________*/
	public void connectUser() {
		
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		if (clientDAO.isClientExist(identifiant, password)) {
			
			client = true;
			
		} else if (adminDAO.isAdminExist(identifiant, password)) {
			
			admin = true;
			
			
		} else {
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Echec de connexion ", " Identifiant ou mot de passe invalide");
			contextJSF.addMessage(null, message);
			
		}//end if-else - v�rif
		
	}//end connectUser()
	
	public void deconnectUser() {
		
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		admin = false;
		client = false;
		
	}//end deconnectUser()

	/*____________________ getters/setters ____________________*/
	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isClient() {
		return client;
	}

	public void setClient(boolean client) {
		this.client = client;
	}
	
}//end servlet
