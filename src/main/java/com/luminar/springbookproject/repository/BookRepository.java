package com.luminar.springbookproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luminar.springbookproject.model.Book;
import com.luminar.springbookproject.model.Category;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
	//gives the category id
	List<Book> findByCategory(Category category);

}
