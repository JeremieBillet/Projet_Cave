package com.intiformation.projet.caveavin.service;

import java.util.List;

import com.intiformation.projet.caveavin.modele.Biere;

public interface IBiereService {
	
	public boolean ajouter(Biere pBiere);
	
	public boolean modifier(Biere pBiere);
	
	public boolean supprimer(Integer pIdBiere);
	
	public Biere afficherParId(Integer pIdBiere);
	
	public Biere afficherParNom(String pNomBiere);
	
	public List<Biere> afficherTout();
	
	public List<Biere> afficherParPays(int pIdPays);
	
	public List<Biere> afficherParType(int pIdType);

}//end interface
