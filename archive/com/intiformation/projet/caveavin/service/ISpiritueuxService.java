package com.intiformation.projet.caveavin.service;

import java.util.List;

import com.intiformation.projet.caveavin.modele.Spiritueux;

public interface ISpiritueuxService {

	public boolean ajouter(Spiritueux pSpiritueux);
	
	public boolean modifier(Spiritueux pSpiritueux);
	
	public boolean supprimer(Integer pIdSpiritueux);
	
	public Spiritueux afficherParId(Integer pIdSpiritueux);
	
	public Spiritueux afficherParNom(String pNomSpiritueux);
	
	public List<Spiritueux> afficherTout();
	
	public List<Spiritueux> afficherParPays(int pIdPays);
	
	public List<Spiritueux> afficherParType(int pIdType);
	
}//end interface
