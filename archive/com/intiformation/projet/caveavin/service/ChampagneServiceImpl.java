package com.intiformation.projet.caveavin.service;

import java.util.List;

import com.intiformation.projet.caveavin.dao.ChampagneDAOImpl;
import com.intiformation.projet.caveavin.dao.IChampagneDAO;
import com.intiformation.projet.caveavin.modele.Champagne;

public class ChampagneServiceImpl implements IChampagneService {
	
	private IChampagneDAO champagneDAO;
	
	public ChampagneServiceImpl() {
		champagneDAO = new ChampagneDAOImpl();
	}//end ctor

	@Override
	public boolean ajouter(Champagne pChampagne) {
		return champagneDAO.add(pChampagne);
	}

	@Override
	public boolean modifier(Champagne pChampagne) {
		return champagneDAO.update(pChampagne);
	}

	@Override
	public boolean supprimer(Integer pIdChampagne) {
		return champagneDAO.delete(pIdChampagne);
	}

	@Override
	public Champagne afficherParId(Integer pIdChampagne) {
		return champagneDAO.getById(pIdChampagne);
	}

	@Override
	public Champagne afficherParNom(String pNomChampagne) {
		return champagneDAO.getByNom(pNomChampagne);
	}

	@Override
	public List<Champagne> afficherTout() {
		return champagneDAO.getAll();
	}

	@Override
	public List<Champagne> afficherParType(int pIdType) {
		return champagneDAO.getByType(pIdType);
	}

	@Override
	public List<Champagne> afficherParAnnee(int pAnnee) {
		return champagneDAO.getByAnnee(pAnnee);
	}

	@Override
	public List<Champagne> afficherParCepage(int pIdCepage) {
		return champagneDAO.getByCepage(pIdCepage);
	}
	
	

}//end class
