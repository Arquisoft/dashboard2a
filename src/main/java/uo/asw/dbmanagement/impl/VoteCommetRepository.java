package uo.asw.dbmanagement.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import uo.asw.dbmanagement.model.VoteComment;

@Repository
@Transactional
public class VoteCommetRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(VoteComment c) {
		entityManager.persist(c);
	}

	public List<VoteComment> findAllVoteComents() {
		return entityManager.createQuery("from VoteComment", VoteComment.class).getResultList();
	}

}
