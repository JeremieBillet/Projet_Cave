package com.intiformation.projet.caveavin.service;

import java.util.List;

import com.intiformation.projet.caveavin.dao.BiereDAOImpl;
import com.intiformation.projet.caveavin.dao.IBiereDAO;
import com.intiformation.projet.caveavin.modele.Biere;

public class BiereServiceImpl implements IBiereService {
	
	private IBiereDAO biereDAO;
	
	public BiereServiceImpl() {
		biereDAO = new BiereDAOImpl();
	}//end ctor

	@Override
	public boolean ajouter(Biere pBiere) {
		return biereDAO.add(pBiere);
	}

	@Override
	public boolean modifier(Biere pBiere) {
		return biereDAO.update(pBiere);
	}

	@Override
	public boolean supprimer(Integer pIdBiere) {
		return biereDAO.delete(pIdBiere);
	}

	@Override
	public Biere afficherParId(Integer pIdBiere) {
		return biereDAO.getById(pIdBiere);
	}

	@Override
	public Biere afficherParNom(String pNomBiere) {
		return biereDAO.getByNom(pNomBiere);
	}

	@Override
	public List<Biere> afficherTout() {
		return biereDAO.getAll();
	}

	@Override
	public List<Biere> afficherParPays(int pIdPays) {
		return biereDAO.getByPays(pIdPays);
	}

	@Override
	public List<Biere> afficherParType(int pIdType) {
		return biereDAO.getByType(pIdType);
	}

}//end class
