package uo.asw.dbmanagement.repository;




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

	private String query = "insert into VoteSuggestion(citizen_id, suggestion_id, vote) values (?1,?2,?3)";
	
	public List<VoteSuggestion> findAll() {
		return entityManager.createQuery("from VoteSuggestion", VoteSuggestion.class).getResultList();
	}

	public void save(VoteSuggestion v) {
		entityManager.createNativeQuery(query)
				.setParameter(1, v.getCitizen().getId()).setParameter(2, v.getSuggestion().getId())
				.setParameter(3, v.getVote().toString()).executeUpdate();
	}

	public VoteSuggestion findByCitizenIdAndSuggestionId(Long citizen_id, Long suggestion_id) {
		List<VoteSuggestion> vc = entityManager
				.createQuery("from VoteSuggestion c where c.citizen.id = ?1 and c.suggestion.id = ?2", VoteSuggestion.class)
				.setParameter(1, citizen_id).setParameter(2, suggestion_id).getResultList();
		return vc.isEmpty() ? null : vc.get(0);
	}
}
