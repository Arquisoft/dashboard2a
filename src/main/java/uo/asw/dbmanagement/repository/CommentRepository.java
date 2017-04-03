package uo.asw.dbmanagement.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uo.asw.dbmanagement.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
}
