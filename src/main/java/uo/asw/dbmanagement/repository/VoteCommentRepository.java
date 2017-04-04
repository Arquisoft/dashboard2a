package uo.asw.dbmanagement.repository;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import uo.asw.dbmanagement.model.VoteComment;


@Repository
public interface VoteCommentRepository extends JpaRepository<VoteComment, Long>{

}
