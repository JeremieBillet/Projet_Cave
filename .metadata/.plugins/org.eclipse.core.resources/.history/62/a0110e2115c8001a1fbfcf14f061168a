package com.intiformation.projet.caveavin.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import com.intiformation.projet.caveavin.dao.CommandeDAOImpl;
import com.intiformation.projet.caveavin.dao.ICommandeDAO;
import com.intiformation.projet.caveavin.modele.Commande;

@ManagedBean(name="rechercheCommandeBean")
@SessionScoped
public class RechercheCommandeBean implements Serializable{

	/*__________ PROPS ___________ */
	private List<Commande> listeCommandeBDD;
	private Commande commande;
	
	private ICommandeDAO commandeDAO;

	/*__________ CTORS ___________ */	
	
	public RechercheCommandeBean() {
		commandeDAO = new CommandeDAOImpl();
	}
	
	/*__________ METHODES ___________ */
	
	/**
	 * permet de récupérer une commande à partir de son id
	 * @return
	 */
	public void findCommandeById(ActionEvent event){
		
		// 1. récup de l'id rentré par l'utilisateur
		UIParameter uip = (UIParameter) event.getComponent().findComponent("idCommande");
		int CommandeID = (int) uip.getValue();
		
		System.out.println(CommandeID);
		
		// 2. appel de la méthode getById()
		Commande selectedCommand = commandeDAO.getById(CommandeID);
		
		// 3. Envoi de la commande
		setCommande(selectedCommand);
		
	}//end findCommandeById()
	

	/**
	 * permet de récupérer la liste des commandes propres à un client à partir de son id
	 * @return
	 */
	public void findCommandeByClient(ActionEvent event){
		
		// 1. récup de l'id rentré par l'utilisateur
		UIParameter uip = (UIParameter) event.getComponent().findComponent("idClient");
		int ClientID = (int) uip.getValue();
		System.out.println(ClientID);
		
		// 2. récup du choix d'affichage (checkbox 1)
		UIParameter option1 = (UIParameter) event.getComponent().findComponent("all");
		System.out.println(option1);
		
		// 2. récup du choix d'affichage (checkbox 1)
		UIParameter option2 = (UIParameter) event.getComponent().findComponent("last");
		System.out.println(option2);
		
		
	}//end findCommandeByClient()
	
	
	
	public void findCommandeByC(ActionEvent event){

		// 2. appel de la méthode getById()
		//listeCommandeBDD = (List<Commande>) commandeDAO.getAllByIdClient();
		
		// 3. Envoi de la commande
		setListeCommandeBDD(listeCommandeBDD);
		
	}//end findCommandeByClient()
	
	
	/**
	 * permet de récupérer la dernière commande propre à un client à partir de son id
	 * @return
	 */
	public void findLastCommandeByClient(ActionEvent event){
		
		// 1. récup de l'id rentré par l'utilisateur
		UIParameter uip = (UIParameter) event.getComponent().findComponent("idClient");
		int ClientID = (int) uip.getValue();
		
		// 2. appel de la méthode getById()
		commande = (Commande) commandeDAO.getLastCommand(ClientID);
		
		// 3. Envoi de la commande
		setCommande(commande);
		
	}//end findLastCommandeByClient()
	

	/*__________ GETTER / SETTER  ___________ */
	public List<Commande> getListeCommandeBDD() {
		return listeCommandeBDD;
	}
	public void setListeCommandeBDD(List<Commande> listeCommandeBDD) {
		this.listeCommandeBDD = listeCommandeBDD;
	}
	public Commande getCommande() {
		return commande;
	}
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	
	
}//end class
