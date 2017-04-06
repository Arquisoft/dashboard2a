package uo.asw.dbmanagement.repository;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


import org.springframework.stereotype.Repository;

import uo.asw.dbmanagement.model.Citizen;

@Repository
@Transactional
public class CitizenRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	
	public List<Citizen> findAll(){
		List<Citizen> ci =  entityManager.createQuery("from Citizen", Citizen.class).getResultList();
		for (Citizen citizen : ci) {
			citizen.getSuggestions();
			citizen.getComments();
			citizen.getVoteComments();
			citizen.getVoteSuggestions();
		}
		return ci;
	}

}
