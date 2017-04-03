package uo.asw.dbmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uo.asw.dbmanagement.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
