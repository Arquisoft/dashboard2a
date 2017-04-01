package uo.asw.dbmanagement.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import uo.asw.dbmanagement.GetUser;
import uo.asw.dbmanagement.model.User;

@Repository
@Transactional
public class GetUserImpl implements GetUser {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User getUser(String login, String password) {
		@SuppressWarnings("unchecked")
		List<User> user = entityManager.createQuery("from User u where u.login = ?1 and u.password = ?2")
				.setParameter(1, login).setParameter(2, password).getResultList();
		if (user.isEmpty())
			return null;
		return user.get(0);
	}

}
