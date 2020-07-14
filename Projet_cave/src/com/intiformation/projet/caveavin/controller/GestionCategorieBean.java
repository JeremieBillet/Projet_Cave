package com.intiformation.projet.caveavin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;

import com.intiformation.projet.caveavin.dao.CategorieDAOImpl;
import com.intiformation.projet.caveavin.dao.ICategorieDAO;
import com.intiformation.projet.caveavin.modele.CatType;
import com.intiformation.projet.caveavin.modele.Categorie;

@ManagedBean(name="gestionCategorieBean")
@SessionScoped
public class GestionCategorieBean implements Serializable{

	/*__________ PROPS ___________ */
	private List<Categorie> listeCategorieBDD;
	private Categorie categorie;
	private CatType catType;
	
	// file upload de l'API servlet
    private Part uploadedFile;
	
	private ICategorieDAO categorieDAO;
	
	/*__________ CTORS ___________ */	
	/**
	 * ctor vide pour l'instanciation du MB par le serveur
	 */
	public GestionCategorieBean() {		
		categorieDAO = new CategorieDAOImpl();
	}//end ctor
	
	/*__________ METHS ___________ */
	
	/**
	 * permet de r�cup�rer la liste de tous les �l�ments appartenant � une cat�gorie ds la bdd via la dao
	 * la m�thode est utilis�e par le composant <h:datatable> de 'accueil-user.xhtml'
	 * @return
	 */
	public List<Categorie> findAllCategorieBdd(){
		
		listeCategorieBDD = categorieDAO.getAll();
			
		return listeCategorieBDD;
		
	}//end findAllCategorieBdd()
	
	
	/**
	 * permet de r�cup�rer la liste de tous les �l�ments appartenant � une cat�gorie ds la bdd via la dao
	 * la m�thode est utilis�e par le composant <h:datatable> de 'accueil-user.xhtml'
	 * @return
	 */
	public void findAllCategorieByClassNameBdd(ActionEvent event){
		
		// 1. r�cup du param pass� ds le composant au click sur un lien dans la page accueil-gestion
		String uip = event.getComponent().getId();
		
		// 2. r�cup de la valeur du param�tre
		//String NomClass = (String) uip.getValue();
		
		// 3. appel de la m�thode getByClassName		
		listeCategorieBDD = categorieDAO.getByClassName(uip);
			
		setListeCategorieBDD(listeCategorieBDD);
		
	}//end findAllCategorieByClassNameBdd()
	
	
	/**
	 * PERMET DE SUPPRIMER UN ELEMENT D'UNE CATEGORIE DS LA BDD.
	 * invoqu�e au click sur le lien 'supprimer' de 'accueil-user.xhtml'.
	 */
	public void supprimerCategorie(ActionEvent event) {
		
		// 1. r�cup du param pass� ds le composant au click sur le lien 'supprimer'
		UIParameter uip = (UIParameter) event.getComponent().findComponent("deleteID");
		
		// 2. r�cup de la valeur du param�tre
		int CategorieID = (int) uip.getValue();
		
		// 3. suppression de l'�l�ment ds la bdd via l'id
		
		// 3.1 r�cup du contexte de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		// 3.2 suppression du pays
		if (categorieDAO.delete(CategorieID)) {
			
			//   SUPP OK
			
			// => envoi d'un message vers la vue via le context de JSF
			contextJSF.addMessage(null, 
								  new FacesMessage(FacesMessage.SEVERITY_INFO, 
										  		   "suppression cat�gorie ", 
										  		   " - la cat�gorie a �t� supprim� avec succ�s"));
			
			//=> redirection vers accueil-user.xhtml (r�f : les cl�s d'outcom ds faces-config.xml)
			
			
		} else {
			
		//   SUPP NOT OK
			
			// => envoi d'un message vers la vue via le context de JSF
						contextJSF.addMessage(null, 
											  new FacesMessage(FacesMessage.SEVERITY_FATAL, 
													  		   "suppression cat�gorie ", 
													  		   " - la suppression de la cat�gorie a �chou�"));
						
			//=> redirection vers accueil-user.xhtml (r�f : les cl�s d'outcom ds faces-config.xml)

		}//end else
		
	}//end supprimerCategorie
	
	
	/**
	 * PERMET D'EDITER UNE CATEGORIE A MODIFIER DS LA BDD.
	 * invoqu�e au click sur le lien 'editer' de 'accueil-user.xhtml'.
	 */
	public void selectionnerCategorie(ActionEvent event) {
		
		// 1. r�cup du param pass� au composant au click sur le lien 'editer'
		UIParameter uip = (UIParameter) event.getComponent().findComponent("updateID");
		
		// 2. r�cup de la valeur du param (l'id de l'�l�ment � �diter)
		int categorieID = (int) uip.getValue();
		
		// 3. r�cup de la r�gion � �diter via l'id ds la bdd
		Categorie CategorieAEditer = categorieDAO.getById(categorieID);
		
		// 4. affectation de l'�l�ment � editer � la variable 'Categorie' du MB
		setCategorie(CategorieAEditer);
		
		// 5. redirection vers la page d'�dition 'editer-categorie.xhtml' (r�f : la cl� d'outcom 'editbook' du faces-config.xml)
		
	}//end selectionnerCategorie()
	
	
	/**
	 * PERMET D'INITIALISER UN NOUVEL OBJET CATEGORIE VIDE.
	 * invoqu�e au click sur le bouton 'ajouter une nouvelle cat�gorie' de 'accueil-user.xhtml'.
	 */
	public void initialiserCategorie(ActionEvent event) {
		
		// 1. instanciation d'un nouveau objet de type 'Categorie'
		Categorie categorieAdd = new Categorie();
		
		// 2. affectation du pays � la prop de 'categorie' du MB
		// ---> cet objet va r�ceptionner les infos de la cat�gorie envoy�es � partir du formulaire d'ajout 'ajouter-categorie.xhtml'
		setCategorie(categorieAdd);
		
	}//end initialiserCategorie
	
	
	/**
     * sauvegarder une cat�gorie
     * @param event
     */
    public void saveCategorie(ActionEvent event) {

        //-------------------------------------------
        // cas : ajout 
        //-------------------------------------------
        if (catType.getIdCategorie() == 0) {

            try {
                // traitement du fileUpload : recup du nom de l'image
                String fileName = uploadedFile.getSubmittedFileName();
                
                // affectation du nom � la prop urlImage du livre
                catType.setPhoto(fileName);
                
                // ajout du livre dans la bdd
                categorieDAO.add(catType);

                //----------------------------------------------
                // ajout de la photo dans le dossier images
                //-----------------------------------------------
                
                // recup du contenu de l'image
                InputStream imageContent = uploadedFile.getInputStream();

                // recup de la valeur du param d'initialisation context-param de web.xml
                FacesContext fContext = FacesContext.getCurrentInstance();
                String pathTmp = fContext.getExternalContext().getInitParameter("file-upload");
                
                String filePath = fContext.getExternalContext().getRealPath(pathTmp);

                // cr�ation du fichier image (conteneur de l'image) 
                File targetFile = new File(filePath, fileName);

                // instanciation du flux de sortie vers le fichier image
                OutputStream outStream = new FileOutputStream(targetFile);
                byte[] buf = new byte[1024];
                int len;

                while ((len = imageContent.read(buf)) > 0) {
                    outStream.write(buf, 0, len);
                }
                
                outStream.close();

            } catch (IOException ex) {
                Logger.getLogger(GestionCategorieBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //-------------------------------------------
        // cas : modif 
        //-------------------------------------------
        if (catType.getIdCategorie() != 0) {

            if (uploadedFile != null) {

                String fileNameToUpdate = uploadedFile.getSubmittedFileName();

                if (!"".equals(fileNameToUpdate) && fileNameToUpdate != null) {

                    // affectation du nouveau nom à la prop urlImage de la categorie
                    catType.setPhoto(fileNameToUpdate);
                }
            }

            categorieDAO.update(catType);
        }

    }//end saveBook()
	
	
	
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

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public List<Categorie> getListeCategorieBDD() {
		return listeCategorieBDD;
	}

	public void setListeCategorieBDD(List<Categorie> listeCategorieBDD) {
		this.listeCategorieBDD = listeCategorieBDD;
	}
	
	
}//end class
