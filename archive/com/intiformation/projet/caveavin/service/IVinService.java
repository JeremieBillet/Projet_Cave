package com.intiformation.projet.caveavin.service;

import java.util.List;

import com.intiformation.projet.caveavin.modele.Vin;

public interface IVinService {
	
	public boolean ajouter(Vin pVin);
	
	public boolean modifier(Vin pVin);
	
	public boolean supprimer(Integer pIdVin);
	
	public Vin afficherParId(Integer pIdVin);
	
	public Vin afficherParNom(String pNomVin);
	
	public List<Vin> afficherTout();
	
	public List<Vin> afficherParPays(int pIdPays);
	
	public List<Vin> afficherParRegion(int pIdRegion);
	
	public List<Vin> afficherParType(int pIdType);
	
	public List<Vin> afficherParAnnee(int pAnnee);
	
	public List<Vin> afficherParCepage(int pIdCepage);

}//end interface
