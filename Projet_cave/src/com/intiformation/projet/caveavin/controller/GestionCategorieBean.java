package com.intiformation.projet.caveavin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;

import org.primefaces.PrimeFaces;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.intiformation.projet.caveavin.dao.CategorieDAOImpl;
import com.intiformation.projet.caveavin.dao.ICategorieDAO;
import com.intiformation.projet.caveavin.modele.CatCepage;
import com.intiformation.projet.caveavin.modele.CatPays;
import com.intiformation.projet.caveavin.modele.CatRegion;
import com.intiformation.projet.caveavin.modele.CatType;
import com.intiformation.projet.caveavin.modele.Categorie;
import com.sun.xml.internal.txw2.Document;

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
	 * permet de récupérer la liste de tout les éléments de toutes les catégories
	 * @return
	 */
	public List<Categorie> findAllCategorieBdd(){
		
		listeCategorieBDD = categorieDAO.getAll();
			
		return listeCategorieBDD;
		
	}//end findAllCategorieBdd()
	
	
	/**
	 * permet de récupérer la liste des éléments d'une catégorie
	 * @return
	 */
	public void findAllCategorieByClassNameBdd(ActionEvent event){
		
		// 1. récup du nom de la catégorie qu'on veut afficher via l'id "Catxxx" du <p:menuitem>
		String uip = event.getComponent().getId();
		
		// 2. appel de la méthode getByClassName		
		listeCategorieBDD = categorieDAO.getByClassName(uip);
		
		// 3. Envoi de la liste des catégories concernées dans la variable 'listeCategorieBDD'
		setListeCategorieBDD(listeCategorieBDD);
		// 4. Envoi du nom de la catégorie actuellement affichée dans la variable 'CatEnCours'
		setCatEnCours(uip);
		
	}//end findAllCategorieByClassNameBdd()
	
	
	/**
	 * PERMET DE SUPPRIMER UN ELEMENT D'UNE CATEGORIE DS LA BDD.
	 * invoquée au click sur le lien 'supprimer' de 'gestion-categorie.xhtml'.
	 */
	public void supprimerCategorie(ActionEvent event) {
		
		// récup du param passé ds le composant au click sur le lien 'supprimer'
		UIParameter uip = (UIParameter) event.getComponent().findComponent("deleteID");
		int CategorieID = (int) uip.getValue();
		
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		// 3.2 suppression du pays
		if (categorieDAO.delete(CategorieID)) {
			
			//   SUPP OK
			contextJSF.addMessage(null, 
								  new FacesMessage(FacesMessage.SEVERITY_INFO, 
										  		   "suppression catégorie ", 
										  		   " - la catégorie a été supprimé avec succés"));
			
			// Renvoi de la liste mise à jour
			listeCategorieBDD = categorieDAO.getByClassName(catEnCours);			
			setListeCategorieBDD(listeCategorieBDD);
			
		} else {
			
		//   SUPP NOT OK
						contextJSF.addMessage(null, 
											  new FacesMessage(FacesMessage.SEVERITY_FATAL, 
													  		   "suppression catégorie ", 
													  		   " - la suppression de la catégorie a échoué"));
						
			// Renvoi de la liste mise à jour
			listeCategorieBDD = categorieDAO.getByClassName(catEnCours);						
			setListeCategorieBDD(listeCategorieBDD);
			
		}//end else
		
	}//end supprimerCategorie

	
	/**
	 * PERMET DAJOUTER UNE NOUVELLE CATEGORIE DANS LA BDD.
	 * invoquée au click sur le bouton 'ajouter' de 'gestion-categorie.xhtml'.
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
		
		// 2. ajout du nv livre à la bdd via la dao
		if(categorieDAO.add(cat)) {
			
			// AJOUT OK			
			// -> envoi d'un message vers la vue accueil.xhtml
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
					"Ajout Categorie :", " - La Categorie a été ajouté avec succès"));			
			listeCategorieBDD = categorieDAO.getByClassName(catEnCours);			
			setListeCategorieBDD(listeCategorieBDD);
			
			
		} else {
			
			// AJOUT NOT OK			
			// -> envoi d'un message vers la vue gestion-categorie.xhtml
			FacesMessage messageAddNotOk = new FacesMessage(FacesMessage.SEVERITY_FATAL, 
					"Ajout categorie ", " - L'ajout de la categorie a échoué");			
			listeCategorieBDD = categorieDAO.getByClassName(catEnCours);			
			setListeCategorieBDD(listeCategorieBDD);

		}//end else

	}// end ajouterCategorie()
	
	
	/**
	 * PERMET DE MODIFIER UNE CATEGORIE DANS LA BDD
	 * @param event
	 */
	
	public void onRowEdit(RowEditEvent event) {
		
		// récup de la valeur du param (l'id de l'élément à éditer)
        UIParameter uip = (UIParameter) event.getComponent().findComponent("updateID");		
		int categorieID = (int) uip.getValue();
        System.out.println(categorieID);
        
        // récup de la valeur du param (le nouveau nom de l'élément à éditer)
        UIParameter name = (UIParameter) event.getComponent().findComponent("updateNom");				
		String categorieNom = (String) name.getValue();
		System.out.println(categorieNom);
        
        FacesContext contextJSF = FacesContext.getCurrentInstance();

        // application du nouveau nom à la catégorie
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
						"Modification réusiie ", " - La catégorie a été modifiée avec succès");			
			// envoi du message 
			contextJSF.addMessage(null, messageOk);

		} else {
			
			// MODIF NOT OK			
			// -> message vers la vue
			FacesMessage messageNotOk = new FacesMessage(FacesMessage.SEVERITY_FATAL, 
						"ECHEC DE LA MODIFICATION ", " - La modification de la catégorie a échoué");						
			// envoi du message 
			contextJSF.addMessage(null, messageNotOk);

		}//end else
		
    }//end onRowEdit
     

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);             
    }//end onRowCancel


	/**
     * sauvegarder une catégorie
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
                
                // affectation du nom à  la prop urlImage du livre
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

                // création du fichier image (conteneur de l'image) 
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

                    // affectation du nouveau nom Ã  la prop urlImage de la categorie
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
