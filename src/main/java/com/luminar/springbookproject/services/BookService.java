package com.luminar.springbookproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luminar.springbookproject.model.Book;
import com.luminar.springbookproject.model.Category;
import com.luminar.springbookproject.model.HighlightedStatement;
import com.luminar.springbookproject.repository.BookRepository;
import com.luminar.springbookproject.repository.CategoryRepository;
import com.luminar.springbookproject.repository.HighlightedRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private HighlightedRepository highlightedRepository;
	
	public List<Book> getallBooks() {
		return bookRepository.findAll();
	}
	
	public void saveCategory(Category category) {
		this.categoryRepository.save(category);
	}
	
	public List<Book> getBooksByCategory(Long categoryId) {
	    Category category = categoryRepository.findById(categoryId)
	        .orElseThrow(() -> new RuntimeException("Category not found"));
	    return bookRepository.findByCategory(category);
	}
	/*
	public void saveBook(Book book) {
	    Category category = book.getCategory();

	    if (category.getId() != null) {
	        // Use existing category
	        category = categoryRepository.findById(category.getId())
	            .orElseThrow(() -> new RuntimeException("Category not found"));
	    } else {
	        // Add new category or reuse one by name
	        Category existing = categoryRepository.findByCategoryName(category.getCategoryName());
	        if (existing != null) {
	            category = existing;de
	        } else {
	            category = categoryRepository.save(new Category(category.getCategoryName()));
	        }
	    }

	    book.setCategory(category);
	    bookRepository.save(book);
	}
	*/

	    public Book saveBook(Book book) {
	        // Look for existing category by name
	        Category category = categoryRepository.findByCategoryName(book.getCategory().getCategoryName());
	        if (category == null) {
	            // Save new category if not found
	            category = categoryRepository.save(book.getCategory());
	        }
	        // Set managed category back to book
	        book.setCategory(category);

	        // Save book
	        return bookRepository.save(book);
	    }
	
	public List<String> getAllCategories(){
		return this.categoryRepository.findAllCategoryNames();
	}
	
	public void deleteBook(long id) {
		bookRepository.deleteById(id);
	}
	
	public List<HighlightedStatement> highlights(long id){
		return highlightedRepository.findByBookId(id);
	}
	
	public Book getBookById(long id) {
		return bookRepository.getById(id);
	}
	public HighlightedStatement saveHighlightedStatement(String statement, long bookId) {
	    Book book = bookRepository.findById(bookId)
	            .orElseThrow(() -> new RuntimeException("Book not found"));
	    
	    HighlightedStatement hs = new HighlightedStatement(statement, book);
	    return highlightedRepository.save(hs);
	}

	
}
