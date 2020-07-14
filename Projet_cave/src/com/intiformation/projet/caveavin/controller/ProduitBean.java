package com.intiformation.projet.caveavin.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuColumn;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.intiformation.projet.caveavin.dao.AlcoolDAOImpl;
import com.intiformation.projet.caveavin.dao.CategorieDAOImpl;
import com.intiformation.projet.caveavin.dao.IAlcoolDAO;
import com.intiformation.projet.caveavin.dao.ICategorieDAO;
import com.intiformation.projet.caveavin.modele.Biere;
import com.intiformation.projet.caveavin.modele.CatRegion;
import com.intiformation.projet.caveavin.modele.Categorie;
import com.intiformation.projet.caveavin.modele.Champagne;
import com.intiformation.projet.caveavin.modele.Spiritueux;
import com.intiformation.projet.caveavin.modele.Vin;

@ManagedBean(name = "produitBean", eager = true)
@ApplicationScoped
public class ProduitBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* __________________props__________________ */
	
	private MenuModel model;
	private String produitRecherche;
	double prixMax;
	double prixFiltre;
	
	private IAlcoolDAO alcoolDao;
	private ICategorieDAO catDao;
	
	private List<Vin> listeVin;
	private List<Champagne> listeChampagne;
	private List<Spiritueux> listeSpiriteux;
	private List<Biere> listeBiere;
	
	private List<Categorie> listeRegionVin;
	private List<Categorie> listeTypeVin;
	private List<Categorie> listePaysVin;
	private List<Categorie> listeTypeChampagne;
	private List<Categorie> listeTypeSpiritueux;
	private List<Categorie> listePaysSpiritueux;
	private List<Categorie> listeTypeBiere;
	private List<Categorie> listePaysBiere;

	/* __________________ctors__________________ */
	public ProduitBean() {
		alcoolDao = new AlcoolDAOImpl();
		catDao = new CategorieDAOImpl();		
	}

	/* _________________methodes________________ */
	@PostConstruct
	public void init() {
		
		tousLesVins();
		
		
		listeRegionVin = catDao.getByClassNameAndAlcool("catRegion", "Vin");
		listeTypeVin = catDao.getByClassNameAndAlcool("catType", "Vin");
		listePaysVin = catDao.getByClassNameAndAlcool("catPays", "Vin");
		listeTypeChampagne = catDao.getByClassNameAndAlcool("catType", "Champagne");
		listeTypeSpiritueux = catDao.getByClassNameAndAlcool("catType", "Spiritueux");
		listePaysSpiritueux = catDao.getByClassNameAndAlcool("catPays", "Spiritueux");
		listeTypeBiere = catDao.getByClassNameAndAlcool("catType", "Biere");
		listePaysBiere = catDao.getByClassNameAndAlcool("catPays", "Biere");

		
		creationModelMenu();
		
		
	}
	
	public void creationModelMenu() {
		model = new DefaultMenuModel();
		DefaultSubMenu Submenu;
		DefaultMenuItem item;

		// ------------------ submenu Vin ------------
		Submenu = new DefaultSubMenu("Vin");

		ajoutColonne( Submenu, listePaysVin, "Par Pays");
		ajoutColonne( Submenu, listeRegionVin, "Par Région");
		ajoutColonne( Submenu, listeTypeVin, "Par Type");
		
		model.getElements().add(Submenu);

		// --------------- submenu Champagne ------------
		Submenu = new DefaultSubMenu("Champagne");

		ajoutColonne( Submenu, listeTypeChampagne, "Par Type");

		model.getElements().add(Submenu);

		// --------------- submenu Spiritueux ------------
		Submenu = new DefaultSubMenu("Spiritueux");

		ajoutColonne( Submenu, listeTypeSpiritueux, "Par Type");
		ajoutColonne( Submenu, listePaysSpiritueux, "Par Pays");

		model.getElements().add(Submenu);

		// --------------- submenu Biere ------------
		Submenu = new DefaultSubMenu("Bière");

		ajoutColonne( Submenu, listeTypeBiere, "Par Type");
		ajoutColonne( Submenu, listePaysBiere, "Par Pays");

		model.getElements().add(Submenu);

		// --------------- submenu Promo ------------
		item = new DefaultMenuItem("Promo");
		model.getElements().add(item);

		
		// --------------- submenu Gestion ------------
		item = new DefaultMenuItem("GESTION");
		model.getElements().add(item);
	}
	
	public void ajoutColonne(DefaultSubMenu submenu, List<Categorie> listeCat, String label) {
		DefaultMenuColumn column;
		DefaultSubMenu columnMenu;
		DefaultMenuItem item;
		
		column = new DefaultMenuColumn();
		columnMenu = new DefaultSubMenu(label);
		columnMenu.setStyleClass("control-label");

		for (Categorie catItem : listeCat) {
			item = new DefaultMenuItem(catItem.getNomObjetCat());
			columnMenu.getElements().add(item);
		}

		column.getElements().add(columnMenu);
		submenu.getElements().add(column);
	}

	public void tousLesVins() {
		listeChampagne = null;
		listeSpiriteux = null;
		listeBiere = null;
		listeVin = alcoolDao.getAllVin();
		
		produitRecherche = "Vin";
		
		prixMax = listeVin.stream().max((v1, v2) -> Double.compare(v1.getPrix(), v2.getPrix())).get().getPrix();
		prixFiltre = prixMax;
	}
	
	public String filterVinByPays() {
		System.out.println("ok");
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		int paysId = Integer.parseInt(params.get("paysId"));
		listeVin = listeVin.stream().filter(v -> v.getPays().getIdCategorie() == paysId).collect(Collectors.toList());
		return "index.xhtml?faces-redirect=true";
	}

	/* _____________getters/setters_____________ */
	public List<Vin> getListeVin() {
		return listeVin;
	}

	public void setListeVin(List<Vin> listeVin) {
		this.listeVin = listeVin;
	}

	public MenuModel getModel() {
		return model;
	}

	public String getProduitRecherche() {
		return produitRecherche;
	}

	public void setProduitRecherche(String produitRecherche) {
		this.produitRecherche = produitRecherche;
	}

	public List<Categorie> getListeRegionVin() {
		return listeRegionVin;
	}

	public void setListeRegionVin(List<Categorie> listeRegionVin) {
		this.listeRegionVin = listeRegionVin;
	}

	public List<Categorie> getListeTypeVin() {
		return listeTypeVin;
	}

	public void setListeTypeVin(List<Categorie> listeTypeVin) {
		this.listeTypeVin = listeTypeVin;
	}

	public List<Categorie> getListePaysVin() {
		return listePaysVin;
	}

	public void setListePaysVin(List<Categorie> listePaysVin) {
		this.listePaysVin = listePaysVin;
	}

	public List<Categorie> getListeTypeChampagne() {
		return listeTypeChampagne;
	}

	public void setListeTypeChampagne(List<Categorie> listeTypeChampagne) {
		this.listeTypeChampagne = listeTypeChampagne;
	}

	public List<Categorie> getListeTypeSpiritueux() {
		return listeTypeSpiritueux;
	}

	public void setListeTypeSpiritueux(List<Categorie> listeTypeSpiritueux) {
		this.listeTypeSpiritueux = listeTypeSpiritueux;
	}

	public List<Categorie> getListePaysSpiritueux() {
		return listePaysSpiritueux;
	}

	public void setListePaysSpiritueux(List<Categorie> listePaysSpiritueux) {
		this.listePaysSpiritueux = listePaysSpiritueux;
	}

	public List<Categorie> getListeTypeBiere() {
		return listeTypeBiere;
	}

	public void setListeTypeBiere(List<Categorie> listeTypeBiere) {
		this.listeTypeBiere = listeTypeBiere;
	}

	public List<Categorie> getListePaysBiere() {
		return listePaysBiere;
	}

	public void setListePaysBiere(List<Categorie> listePaysBiere) {
		this.listePaysBiere = listePaysBiere;
	}

	public double getPrixMax() {
		return prixMax;
	}

	public void setPrixMax(double prixMax) {
		this.prixMax = prixMax;
	}
	
	
}
