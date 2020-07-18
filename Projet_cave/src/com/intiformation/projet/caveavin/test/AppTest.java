package com.intiformation.projet.caveavin.test;


import java.util.ArrayList;
import java.util.List;

import com.intiformation.projet.caveavin.dao.AlcoolDAOImpl;
import com.intiformation.projet.caveavin.dao.CategorieDAOImpl;
import com.intiformation.projet.caveavin.dao.IAlcoolDAO;
import com.intiformation.projet.caveavin.dao.ICategorieDAO;
import com.intiformation.projet.caveavin.modele.Alcool;
import com.intiformation.projet.caveavin.modele.CatCepage;
import com.intiformation.projet.caveavin.modele.CatPays;
import com.intiformation.projet.caveavin.modele.CatRegion;
import com.intiformation.projet.caveavin.modele.CatType;
import com.intiformation.projet.caveavin.modele.Categorie;
import com.intiformation.projet.caveavin.modele.Vin;

public class AppTest {
	
	public static void main(String[] args) {
		
		IAlcoolDAO alcoolDao = new AlcoolDAOImpl();
		ICategorieDAO catDao = new CategorieDAOImpl();
		
		/*
		CatPays pays = new CatPays(6,"Italie");
		catDao.add(pays);
		
		CatRegion region = new CatRegion(7,"Italie");
		catDao.add(region);
		
		CatType type = new CatType(8,"Prosceco","");
		catDao.add(type);
		
		CatCepage cepage1 = new CatCepage(9,"cepage1");
		catDao.add(cepage1);
		
		CatCepage cepage2 = new CatCepage(10,"cepage2");
		catDao.add(cepage2);
		
		List<CatCepage> listeCepage = new ArrayList<>();
		listeCepage.add(cepage1);
		listeCepage.add(cepage2);
		
		Vin ajoutVin = new Vin("Vin", "chateau la tour", "1er grand cru classé", 500., 20, " ", 0,
								pays, region, type, listeCepage, 2018);
		
		alcoolDao.add(ajoutVin);
		
		Vin vin = (Vin) alcoolDao.getById(2);
		
		System.out.println(vin.getDesignation()+" "+vin.getRegion().getNomObjetCat()+" "+vin.getType().getNomObjetCat());
		
		
		List<Vin> listeVin = alcoolDao.getAllVin();
		listeVin.forEach(System.out::println);
		
		Categorie cat = catDao.getById(1);
		if (cat instanceof CatPays) {
			System.out.println("pays");
		}
		System.out.println(cat.getNomObjetCat());
		*/
		
		Vin vin = (Vin) alcoolDao.getById(2);
		System.out.println(vin.getDesignation()+" "+vin.getRegion().getNomObjetCat()+" "+vin.getType().getNomObjetCat());
		
	}

}
