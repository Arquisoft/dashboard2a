package uo.asw.dbmanagement.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import uo.asw.dbmanagement.model.Comment;

@Repository
@Transactional
public class CommentRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(Comment c) {
		entityManager.persist(c);
	}

	public List<Comment> findAllComents() {
		return entityManager.createQuery("from Comment", Comment.class).getResultList();
	}
}
