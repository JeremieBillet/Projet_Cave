package com.intiformation.projet.caveavin.service;

import java.util.List;

import com.intiformation.projet.caveavin.modele.Champagne;

public interface IChampagneService {
	
	public boolean ajouter(Champagne pChampagne);
	
	public boolean modifier(Champagne pChampagne);
	
	public boolean supprimer(Integer pIdChampagne);
	
	public Champagne afficherParId(Integer pIdChampagne);
	
	public Champagne afficherParNom(String pNomChampagne);
	
	public List<Champagne> afficherTout();
	
	public List<Champagne> afficherParType(int pIdType);
	
	public List<Champagne> afficherParAnnee(int pAnnee);
	
	public List<Champagne> afficherParCepage(int pIdCepage);
	
}//end interface
