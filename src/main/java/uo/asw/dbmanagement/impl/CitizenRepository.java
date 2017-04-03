package uo.asw.dbmanagement.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import uo.asw.dbmanagement.model.Citizen;


@Repository
@Transactional
public class CitizenRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(Citizen c) {
		entityManager.persist(c);
	}

	public List<Citizen> findAllCitizen() {
		return entityManager.createQuery("from Citizen", Citizen.class).getResultList();
	}
}
