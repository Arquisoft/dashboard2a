package uo.asw.dbmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uo.asw.dbmanagement.model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
