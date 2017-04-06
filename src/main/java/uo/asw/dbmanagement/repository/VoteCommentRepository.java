package uo.asw.dbmanagement.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


import org.springframework.stereotype.Repository;

import uo.asw.dbmanagement.model.VoteComment;

@Repository
@Transactional
public class VoteCommentRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	private String query = "insert into VoteComment(citizen_id, comment_id, vote) values (?1,?2,?3)";

	public List<VoteComment> findAll() {
		return entityManager.createQuery("from VoteComment", VoteComment.class).getResultList();
	}

	public void save(VoteComment v){
		entityManager.createNativeQuery(query)
				.setParameter(1, v.getCitizen().getId()).setParameter(2, v.getComment().getId())
				.setParameter(3, v.getVote().toString()).executeUpdate();
	}

	public VoteComment findByCitizenIdAndCommentId(Long citizen_id, Long comment_id) {
		List<VoteComment> vc = entityManager
				.createQuery("from VoteComment c where c.citizen.id = ?1 and c.comment.id = ?2", VoteComment.class)
				.setParameter(1, citizen_id).setParameter(2, comment_id).getResultList();
		return vc.isEmpty() ? null : vc.get(0);
	}

}
