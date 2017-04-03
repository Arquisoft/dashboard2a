package uo.asw.dbmanagement.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import uo.asw.dbmanagement.model.VoteSuggestion;

@Repository
@Transactional
public class VoteSuggestionRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(VoteSuggestion c) {
		entityManager.persist(c);
	}

	public List<VoteSuggestion> findAllVoteSuggestion() {
		return entityManager.createQuery("from VoteSuggestion", VoteSuggestion.class).getResultList();
	}

}
