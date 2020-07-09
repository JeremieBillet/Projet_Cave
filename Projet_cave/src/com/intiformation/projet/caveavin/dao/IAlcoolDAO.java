package com.intiformation.projet.caveavin.dao;

import java.util.List;

import com.intiformation.projet.caveavin.modele.Alcool;
import com.intiformation.projet.caveavin.modele.Biere;
import com.intiformation.projet.caveavin.modele.CatCepage;
import com.intiformation.projet.caveavin.modele.Champagne;
import com.intiformation.projet.caveavin.modele.Spiritueux;
import com.intiformation.projet.caveavin.modele.Vin;

public interface IAlcoolDAO extends IGeneralizable<Alcool> {
	
	public Alcool getByNom(String nom);
	
	public List<CatCepage> getAllCepageByAlcool(Integer pIdAlcool);
	
	public boolean addCepageAlcool(Integer pIdAlcool, List<CatCepage> pListeCepages);
	
	public List<Vin> getAllVin();
	
	public List<Champagne> getAllChampagne();
	
	public List<Biere> getAllBiere();
	
	public List<Spiritueux> getAllSpiritueux();

}//end interface
