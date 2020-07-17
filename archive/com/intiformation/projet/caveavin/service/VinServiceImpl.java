package com.intiformation.projet.caveavin.service;

import java.util.List;

import com.intiformation.projet.caveavin.dao.IVinDAO;
import com.intiformation.projet.caveavin.dao.VinDAOImpl;
import com.intiformation.projet.caveavin.modele.Vin;

public class VinServiceImpl implements IVinService {

	private IVinDAO vinDAO;
	
	public VinServiceImpl() {
		vinDAO = new VinDAOImpl();
	}//end ctor
	
	@Override
	public boolean ajouter(Vin pVin) {
		return vinDAO.add(pVin);
	}

	@Override
	public boolean modifier(Vin pVin) {
		return vinDAO.update(pVin);
	}

	@Override
	public boolean supprimer(Integer pIdVin) {
		return vinDAO.delete(pIdVin);
	}

	@Override
	public Vin afficherParId(Integer pIdVin) {
		return vinDAO.getById(pIdVin);
	}

	@Override
	public Vin afficherParNom(String pNomVin) {
		return vinDAO.getByNom(pNomVin);
	}

	@Override
	public List<Vin> afficherTout() {
		return vinDAO.getAll();
	}

	@Override
	public List<Vin> afficherParPays(int pIdPays) {
		return vinDAO.getByPays(pIdPays);
	}

	@Override
	public List<Vin> afficherParRegion(int pIdRegion) {
		return vinDAO.getByRegion(pIdRegion);
	}

	@Override
	public List<Vin> afficherParType(int pIdType) {
		return vinDAO.getByType(pIdType);
	}

	@Override
	public List<Vin> afficherParAnnee(int pAnnee) {
		return vinDAO.getByAnnee(pAnnee);
	}

	@Override
	public List<Vin> afficherParCepage(int pIdCepage) {
		return vinDAO.getByCepage(pIdCepage);
	}

}//end class
