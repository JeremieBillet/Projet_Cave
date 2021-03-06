package com.intiformation.projet.caveavin.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

import com.intiformation.projet.caveavin.dao.CategorieDAOImpl;
import com.intiformation.projet.caveavin.dao.ICategorieDAO;
import com.intiformation.projet.caveavin.modele.CatCepage;
import com.intiformation.projet.caveavin.modele.CatPays;
import com.intiformation.projet.caveavin.modele.CatRegion;
import com.intiformation.projet.caveavin.modele.CatType;
import com.intiformation.projet.caveavin.modele.Categorie;

@ManagedBean(name="gestionCategorieBean")
@SessionScoped
public class GestionCategorieBean implements Serializable{

	/*__________ PROPS ___________ */
	private List<Categorie> listeCategorieBDD;
	private Categorie categorie;
	private CatType catType;
	
	private ICategorieDAO categorieDAO;
	
	private String catEnCours; 
	
	private String nomCat;
	
	
	/*__________ CTORS ___________ */	
	/**
	 * ctor vide pour l'instanciation du MB par le serveur
	 */
	public GestionCategorieBean() {		
		categorieDAO = new CategorieDAOImpl();
	}//end ctor
	
	/*__________ METHS ___________ */
	
	/**
	 * permet de r�cup�rer la liste de tout les �l�ments de toutes les cat�gories
	 * @return
	 */
	public List<Categorie> findAllCategorieBdd(){
		
		listeCategorieBDD = categorieDAO.getAll();
			
		return listeCategorieBDD;
		
	}//end findAllCategorieBdd()
	
	
	/**
	 * permet de r�cup�rer la liste des �l�ments d'une cat�gorie
	 * @return
	 */
	public void findAllCategorieByClassNameBdd(ActionEvent event){
		
		// 1. r�cup du nom de la cat�gorie qu'on veut afficher via l'id "Catxxx" du <p:menuitem>
		String uip = event.getComponent().getId();
		
		// 2. appel de la m�thode getByClassName		
		listeCategorieBDD = categorieDAO.getByClassName(uip);
		
		// 3. Envoi de la liste des cat�gories concern�es dans la variable 'listeCategorieBDD'
		setListeCategorieBDD(listeCategorieBDD);
		// 4. Envoi du nom de la cat�gorie actuellement affich�e dans la variable 'CatEnCours'
		setCatEnCours(uip);
		
	}//end findAllCategorieByClassNameBdd()
	
	
	/**
	 * PERMET DE SUPPRIMER UN ELEMENT D'UNE CATEGORIE DS LA BDD.
	 * invoqu�e au click sur le lien 'supprimer' de 'gestion-categorie.xhtml'.
	 */
	public void supprimerCategorie(ActionEvent event) {
		
		// r�cup du param pass� ds le composant au click sur le lien 'supprimer'
		UIParameter uip = (UIParameter) event.getComponent().findComponent("deleteID");
		int CategorieID = (int) uip.getValue();
		
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		// 3.2 suppression du pays
		if (categorieDAO.delete(CategorieID)) {
			
			//   SUPP OK
			contextJSF.addMessage(null, 
								  new FacesMessage(FacesMessage.SEVERITY_INFO, 
										  		   "suppression cat�gorie ", 
										  		   " - la cat�gorie a �t� supprim� avec succ�s"));
			
			// Renvoi de la liste mise � jour
			listeCategorieBDD = categorieDAO.getByClassName(catEnCours);			
			setListeCategorieBDD(listeCategorieBDD);
			
		} else {
			
		//   SUPP NOT OK
						contextJSF.addMessage(null, 
											  new FacesMessage(FacesMessage.SEVERITY_FATAL, 
													  		   "suppression cat�gorie ", 
													  		   " - la suppression de la cat�gorie a �chou�"));
						
			// Renvoi de la liste mise � jour
			listeCategorieBDD = categorieDAO.getByClassName(catEnCours);						
			setListeCategorieBDD(listeCategorieBDD);
			
		}//end else
		
	}//end supprimerCategorie

	
	/**
	 * PERMET DAJOUTER UNE NOUVELLE CATEGORIE DANS LA BDD.
	 * invoqu�e au click sur le bouton 'ajouter' de 'gestion-categorie.xhtml'.
	 */
	public void ajouterCategorie(){
	
		FacesContext context = FacesContext.getCurrentInstance();
		
		Categorie cat = null;
		
		if(catEnCours.equals("CatPays")) {
			
			cat = new CatPays(nomCat);
						
		}if(catEnCours.equals("CatRegion")) {
			
			cat = new CatRegion(nomCat);
						
		}if(catEnCours.equals("CatCepage")) {
			
			cat = new CatCepage(nomCat);
						
		}if(catEnCours.equals("CatType")) {
			
			cat = new CatType(nomCat);	
		
		}//end if
		
		// 2. ajout du nv livre � la bdd via la dao
		if(categorieDAO.add(cat)) {
			
			// AJOUT OK			
			// -> envoi d'un message vers la vue accueil.xhtml
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
					"Ajout Categorie :", " - La Categorie a �t� ajout� avec succ�s"));			
			listeCategorieBDD = categorieDAO.getByClassName(catEnCours);			
			setListeCategorieBDD(listeCategorieBDD);
			
			
		} else {
			
			// AJOUT NOT OK			
			// -> envoi d'un message vers la vue gestion-categorie.xhtml
			FacesMessage messageAddNotOk = new FacesMessage(FacesMessage.SEVERITY_FATAL, 
					"Ajout categorie ", " - L'ajout de la categorie a �chou�");			
			listeCategorieBDD = categorieDAO.getByClassName(catEnCours);			
			setListeCategorieBDD(listeCategorieBDD);

		}//end else

	}// end ajouterCategorie()
	
	
	/**
	 * PERMET DE MODIFIER UNE CATEGORIE DANS LA BDD
	 * @param event
	 */
	
	public void onRowEdit(RowEditEvent event) {
		
		// r�cup de la valeur du param (l'id de l'�l�ment � �diter)
        UIParameter uip = (UIParameter) event.getComponent().findComponent("updateID");		
		int categorieID = (int) uip.getValue();
        System.out.println(categorieID);
        
        // r�cup de la valeur du param (le nouveau nom de l'�l�ment � �diter)
        UIParameter name = (UIParameter) event.getComponent().findComponent("updateNom");				
		String categorieNom = (String) name.getValue();
		System.out.println(categorieNom);
        
        FacesContext contextJSF = FacesContext.getCurrentInstance();

        // application du nouveau nom � la cat�gorie
		Categorie catToUpdate = categorieDAO.getById(categorieID);
		catToUpdate.setNomObjetCat(categorieNom);
		
		// appel de la couche DAO pour l'update
		categorieDAO.update(catToUpdate);
		System.out.println(catToUpdate.getNomObjetCat());
		
		// Test + message
		if (categorieDAO.update(catToUpdate)) {
			
			// MODIF OK			
			// -> message vers la vue
			FacesMessage messageOk = new FacesMessage(FacesMessage.SEVERITY_INFO, 
						"Modification r�usiie ", " - La cat�gorie a �t� modifi�e avec succ�s");			
			// envoi du message 
			contextJSF.addMessage(null, messageOk);

		} else {
			
			// MODIF NOT OK			
			// -> message vers la vue
			FacesMessage messageNotOk = new FacesMessage(FacesMessage.SEVERITY_FATAL, 
						"ECHEC DE LA MODIFICATION ", " - La modification de la cat�gorie a �chou�");						
			// envoi du message 
			contextJSF.addMessage(null, messageNotOk);

		}//end else
		
    }//end onRowEdit
     

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);             
    }//end onRowCancel


	
	/*__________ G / S ___________ */
	
	public Categorie getCategorie() {
		return categorie;
	}


	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public CatType getCatType() {
		return catType;
	}

	public void setCatType(CatType catType) {
		this.catType = catType;
	}

	public List<Categorie> getListeCategorieBDD() {
		return listeCategorieBDD;
	}

	public void setListeCategorieBDD(List<Categorie> listeCategorieBDD) {
		this.listeCategorieBDD = listeCategorieBDD;
	}

	public String getCatEnCours() {
		return catEnCours;
	}

	public void setCatEnCours(String catEnCours) {
		this.catEnCours = catEnCours;
	}

	public String getNomCat() {
		return nomCat;
	}

	public void setNomCat(String nomCat) {
		this.nomCat = nomCat;
	}

	
}//end class
