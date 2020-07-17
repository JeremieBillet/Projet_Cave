package com.intiformation.projet.caveavin.service;

import java.util.List;

import com.intiformation.projet.caveavin.dao.ISpiritueuxDAO;
import com.intiformation.projet.caveavin.dao.SpiritueuxDAOImpl;
import com.intiformation.projet.caveavin.modele.Spiritueux;

public class SpiritueuxServiceImpl implements ISpiritueuxService {

	private ISpiritueuxDAO spiritueuxDAO;
	
	public SpiritueuxServiceImpl() {
		spiritueuxDAO = new SpiritueuxDAOImpl();
	}//end ctor
	
	@Override
	public boolean ajouter(Spiritueux pSpiritueux) {
		return spiritueuxDAO.add(pSpiritueux);
	}

	@Override
	public boolean modifier(Spiritueux pSpiritueux) {
		return spiritueuxDAO.update(pSpiritueux);
	}

	@Override
	public boolean supprimer(Integer pIdSpiritueux) {
		return spiritueuxDAO.delete(pIdSpiritueux);
	}

	@Override
	public Spiritueux afficherParId(Integer pIdSpiritueux) {
		return spiritueuxDAO.getById(pIdSpiritueux);
	}

	@Override
	public Spiritueux afficherParNom(String pNomSpiritueux) {
		return spiritueuxDAO.getByNom(pNomSpiritueux);
	}

	@Override
	public List<Spiritueux> afficherTout() {
		return spiritueuxDAO.getAll();
	}

	@Override
	public List<Spiritueux> afficherParPays(int pIdPays) {
		return spiritueuxDAO.getByPays(pIdPays);
	}

	@Override
	public List<Spiritueux> afficherParType(int pIdType) {
		return spiritueuxDAO.getByType(pIdType);
	}

}//end class
