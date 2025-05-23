package com.luminar.springbookproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luminar.springbookproject.model.HighlightedStatement;

@Repository
public interface HighlightedRepository extends JpaRepository<HighlightedStatement,Long>{
	List<HighlightedStatement> findByBookId(Long bookId);
}
