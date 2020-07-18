package com.intiformation.projet.caveavin.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.intiformation.projet.caveavin.dao.AlcoolDAOImpl;
import com.intiformation.projet.caveavin.dao.IAlcoolDAO;
import com.intiformation.projet.caveavin.modele.Alcool;
import com.intiformation.projet.caveavin.modele.Biere;
import com.intiformation.projet.caveavin.modele.Champagne;
import com.intiformation.projet.caveavin.modele.Spiritueux;
import com.intiformation.projet.caveavin.modele.Vin;

@ManagedBean (name="singlebottleBean")
@SessionScoped
public class SingleBottlebean implements Serializable{
	
	/*_____________________props______________________*/
	private Alcool alcool;
	private Vin vin;
	private Champagne champagne;
	private Spiritueux spiritueux;
	private Biere biere;
	
	private IAlcoolDAO alcoolDao;
	
	/*_____________________ctors______________________*/
	public SingleBottlebean() {
		alcoolDao = new AlcoolDAOImpl();
	}
	
	/*___________________méthodes_____________________*/
	public String naviguerVersSingleBottle(int idBottle) {
		vin = null; champagne = null; spiritueux = null; biere = null;
		alcool = alcoolDao.getById(idBottle);
		
		if (alcool instanceof Vin) vin = (Vin) alcool;
		if (alcool instanceof Champagne) champagne = (Champagne) alcool;
		if (alcool instanceof Spiritueux) spiritueux = (Spiritueux) alcool;
		if (alcool instanceof Biere) biere = (Biere) alcool;
		
		return "show_single_bottle?faces-redirect=true";
	}
	
	
	

	/*_________________getters/setters_________________*/
	public Alcool getAlcool() {
		return alcool;
	}

	public void setAlcool(Alcool alcool) {
		this.alcool = alcool;
	}

	public Vin getVin() {
		return vin;
	}

	public void setVin(Vin vin) {
		this.vin = vin;
	}

	public Champagne getChampagne() {
		return champagne;
	}

	public void setChampagne(Champagne champagne) {
		this.champagne = champagne;
	}

	public Spiritueux getSpiritueux() {
		return spiritueux;
	}

	public void setSpiritueux(Spiritueux spiritueux) {
		this.spiritueux = spiritueux;
	}

	public Biere getBiere() {
		return biere;
	}

	public void setBiere(Biere biere) {
		this.biere = biere;
	}

	
	
}
