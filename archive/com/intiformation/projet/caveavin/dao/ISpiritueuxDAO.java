package com.intiformation.projet.caveavin.dao;

import java.util.List;

import com.intiformation.projet.caveavin.modele.Spiritueux;

import categories.CatPays;
import categories.CatType;

public interface ISpiritueuxDAO extends IGeneralizable<Spiritueux> {
	
	public List<Spiritueux> getByPays(int idPays);
	
	public List<Spiritueux> getByType(int idType);

}//end interface
