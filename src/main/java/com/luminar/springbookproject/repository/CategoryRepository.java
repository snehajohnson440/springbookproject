package com.luminar.springbookproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luminar.springbookproject.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
	Category findByCategoryName(String categoryName);
	
	@Query("SELECT c.categoryName FROM Category c")
	List<String> findAllCategoryNames();


}
