package uo.asw.dbmanagement.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import uo.asw.dbmanagement.GetParticipant;
import uo.asw.dbmanagement.model.Citizen;

@Repository
@Transactional
public class GetParticipantImpl implements GetParticipant {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Citizen getParticipant(String login, String password) {
		@SuppressWarnings("unchecked")
		List<Citizen> citizen = entityManager.createQuery("from Citizen where userName = ?1 and password = ?2")
				.setParameter(1, login).setParameter(2, password).getResultList();
		if (citizen.isEmpty())
			return null;
		return citizen.get(0);
	}

}
