package com.intiformation.projet.caveavin.controller;


import java.io.Serializable;
import java.util.List;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


import org.primefaces.event.RowEditEvent;

import com.intiformation.projet.caveavin.dao.AlcoolDAOImpl;
import com.intiformation.projet.caveavin.dao.IAlcoolDAO;
import com.intiformation.projet.caveavin.modele.Alcool;
import com.intiformation.projet.caveavin.modele.Biere;
import com.intiformation.projet.caveavin.modele.CatType;
import com.intiformation.projet.caveavin.modele.Categorie;
import com.intiformation.projet.caveavin.modele.Champagne;
import com.intiformation.projet.caveavin.modele.Spiritueux;
import com.intiformation.projet.caveavin.modele.Vin;

@ManagedBean(name="gestionProduitBean")
@SessionScoped
public class GestionProduitBean implements Serializable{

	/*__________ PROPS ___________ */
	private List<Alcool> listeAlcoolBDD;
	private List<Vin> listeVinBDD;
	private List<Champagne> listeChampagneBDD;
	private List<Spiritueux> listeSpiritueuxBDD;
	private List<Biere> listeBiereBDD;
	
	private Categorie categorie;
	private CatType catType;
	
	private IAlcoolDAO alcoolDAO;;
	
	private String catEnCours; 
	
	private String nomCat;

	
	/*__________ CTORS ___________ */	
	/**
	 * ctor vide pour l'instanciation du MB par le serveur
	 */
	public GestionProduitBean() {
		alcoolDAO = new AlcoolDAOImpl();
	}//end ctor
	
	/*__________ METHS ___________ */
	
	/**
	 * permet de récupérer la liste des vins
	 * @return
	 */
	public List<Vin> findAllVin(){
		
		listeVinBDD = alcoolDAO.getAllVin();
		
		setNomCat("vin");
		
		return listeVinBDD;
		
	}//end findAllVin()
	

	/**
	 * permet de récupérer la liste des champagnes
	 * @return
	 */
	public List<Champagne> findAllChampagne(){
		
		listeChampagneBDD = alcoolDAO.getAllChampagne();
			
		setNomCat("champagne");
		
		return listeChampagneBDD;
		
	}//end findAllChampagne()
	
	/**
	 * permet de récupérer la liste des spiritueux
	 * @return
	 */
	public List<Spiritueux> findAllSpiritueux(){
		
		listeSpiritueuxBDD = alcoolDAO.getAllSpiritueux();
			
		setNomCat("spiritueux");
		
		return listeSpiritueuxBDD;
		
	}//end findAllSpiritueux
	
	/**
	 * permet de récupérer la liste des bières
	 * @return
	 */
	public List<Biere> findAllBiere(){
		
		listeBiereBDD = alcoolDAO.getAllBiere();
			
		setNomCat("biere");
		
		return listeBiereBDD;
		
	}//end findAllBiere
	
	
	/**
	 * PERMET DE SUPPRIMER UN ALCOOL DS LA BDD.
	 * invoquée au click sur le lien 'supprimer' de 'gestion-produit.xhtml'.
	 */
	public void supprimerAlcool(ActionEvent event) {
		
		// récup du param passé ds le composant au click sur le lien 'supprimer'
		UIParameter uip = (UIParameter) event.getComponent().findComponent("deleteAlcool");
		int CategorieID = (int) uip.getValue();
		
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		// suppression
		if (alcoolDAO.delete(CategorieID)) {
			
			//   SUPP OK
			contextJSF.addMessage(null, 
								  new FacesMessage(FacesMessage.SEVERITY_INFO, 
										  		   "suppression alcool ", 
										  		   " - le produit a été supprimé avec succés"));
			
			
		} else {
			
		//   SUPP NOT OK
						contextJSF.addMessage(null, 
											  new FacesMessage(FacesMessage.SEVERITY_FATAL, 
													  		   "suppression alcool ", 
													  		   " - la suppression du produit a échoué"));
						
			
		}//end else
		
	}//end supprimerAlcool

	
	/**
	 * PERMET DE MODIFIER UN PRODUIT DANS LA BDD
	 * @param event
	 */
	
	public void onRowEdit(RowEditEvent event) {
		
		// récup de la valeur du param (l'id de l'élément à éditer)
        UIParameter uip = (UIParameter) event.getComponent().findComponent("updateid");		
		int produitID = (int) uip.getValue();
        System.out.println(produitID);
		
		UIParameter designation = (UIParameter) event.getComponent().findComponent("updateDesignation");				
		String nvDesignation = (String) designation.getValue();
		
		UIParameter photo = (UIParameter) event.getComponent().findComponent("updatePhoto");				
		String nvPhoto = (String) photo.getValue();
		
		UIParameter description = (UIParameter) event.getComponent().findComponent("updateDescription");				
		String nvDescription = (String) description.getValue();
				
		UIParameter classe = (UIParameter) event.getComponent().findComponent("updateClasse");				
		String nvClasse = (String) classe.getValue();
		
		UIParameter prix = (UIParameter) event.getComponent().findComponent("updatePrix");				
		double nvPrix = (double) prix.getValue();
		
		UIParameter promo = (UIParameter) event.getComponent().findComponent("updatePromo");				
		double nvPromo = (double) promo.getValue();
		
		UIParameter quantite = (UIParameter) event.getComponent().findComponent("updateQuantite");				
		int nvQuantite = (int) quantite.getValue();
		
        
        FacesContext contextJSF = FacesContext.getCurrentInstance();

        // application du nouveau nom à la catégorie
		Alcool prodToUpdate = alcoolDAO.getById(produitID);
		prodToUpdate.setDesignation(nvDesignation);
		prodToUpdate.setPhoto(nvPhoto);
		prodToUpdate.setDescription(nvDescription);
		prodToUpdate.setClasse(nvClasse);
		prodToUpdate.setPrix(nvPrix);
		prodToUpdate.setPromo(nvPromo);
		prodToUpdate.setQuantite(nvQuantite);
		
		// appel de la couche DAO pour l'update
		alcoolDAO.update(prodToUpdate);
		System.out.println(prodToUpdate.getQuantite());
		
		// Test + message
		if (alcoolDAO.update(prodToUpdate)) {
			
			// MODIF OK			
			// -> message vers la vue
			FacesMessage messageOk = new FacesMessage(FacesMessage.SEVERITY_INFO, 
						"Modification réussie ", " - Le produit a été modifié avec succès");			
			// envoi du message 
			contextJSF.addMessage(null, messageOk);

		} else {
			
			// MODIF NOT OK			
			// -> message vers la vue
			FacesMessage messageNotOk = new FacesMessage(FacesMessage.SEVERITY_FATAL, 
						"ECHEC DE LA MODIFICATION ", " - La modification du produit a échoué");						
			// envoi du message 
			contextJSF.addMessage(null, messageNotOk);

		}//end else
		
    }//end onRowEdit
     

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);             
    }//end onRowCancel



    
    
	
	/**
	 * PERMET DAJOUTER UN NOUVEAU PRODUIT DANS LA BDD.
	 * invoquée au click sur le bouton 'ajouter' de 'gestion-produit.xhtml'.
	 */
	public void ajouterAlcool(){


	}// end ajouterAlcool()



	
	/*__________ G / S ___________ */
	


	public String getNomCat() {
		return nomCat;
	}

	public void setNomCat(String nomCat) {
		this.nomCat = nomCat;
	}


	
	
}//end class
