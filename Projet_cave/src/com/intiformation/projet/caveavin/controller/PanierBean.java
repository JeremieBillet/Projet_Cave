package com.intiformation.projet.caveavin.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import com.intiformation.projet.caveavin.dao.AlcoolDAOImpl;
import com.intiformation.projet.caveavin.dao.IAlcoolDAO;
import com.intiformation.projet.caveavin.dao.ILigneCommandeDAO;
import com.intiformation.projet.caveavin.dao.LigneCommandeDAOImpl;
import com.intiformation.projet.caveavin.modele.Alcool;
import com.intiformation.projet.caveavin.modele.LigneCommande;

@ManagedBean(name = "panierBean" )
@SessionScoped
public class PanierBean implements Serializable {

	/* __________________________props____________________________ */
	private Map<Alcool, LigneCommande> mapPanier;
	private int quantite;
	private double prixTotal;

	private ILigneCommandeDAO ligneCommandeDao;
	//private IAlcoolDAO alcoolDao;

	/* __________________________ctors____________________________ */
	public PanierBean() {
		ligneCommandeDao = new LigneCommandeDAOImpl();
	}

	/* ________________________methodes___________________________ */
	@PostConstruct
	public void init() {
		mapPanier = new HashMap<>();
		
		//alcoolDao = new AlcoolDAOImpl();
	}
	
	public void ajouterAuPanier(Alcool bottle) {
		if (!mapPanier.keySet().stream().anyMatch(i -> i.getIdAlcool() == bottle.getIdAlcool())) {
			if (ligneCommandeDao.add(new LigneCommande(bottle.getIdAlcool(), 0, 1, bottle.getPrix()))) {
				LigneCommande ligneCo = ligneCommandeDao.getLastLigneCommande();

				mapPanier.put(bottle, ligneCo);

			}
		}
	}
	
	public void updatePrixTotal() {
		prixTotal = mapPanier.values().stream().mapToDouble(p->p.getPrix()*p.getQuantite()).sum();
		System.out.println(prixTotal);
	}
	
	
	public void commander() {
		mapPanier.values().forEach(p->System.out.println(p.getQuantite()));
		
	}
	
	/* ____________________getters/setters_________________________ */

	public Map<Alcool, LigneCommande> getMapPanier() {
		return mapPanier;
	}

	public void setMapPanier(Map<Alcool, LigneCommande> mapPanier) {
		this.mapPanier = mapPanier;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}

	

	
	
}
