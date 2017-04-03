package uo.asw.dbmanagement.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uo.asw.dbmanagement.model.Suggestion;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {

}
