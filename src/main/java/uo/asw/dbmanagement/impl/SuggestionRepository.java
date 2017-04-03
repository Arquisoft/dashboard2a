package uo.asw.dbmanagement.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import uo.asw.dbmanagement.model.Suggestion;

@Repository
@Transactional
public class SuggestionRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(Suggestion c) {
		entityManager.persist(c);
	}

	public List<Suggestion> findAllSuggestion() {
		return entityManager.createQuery("from Suggestion", Suggestion.class).getResultList();
	}
}
