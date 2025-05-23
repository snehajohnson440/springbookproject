package com.luminar.springbookproject.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.luminar.springbookproject.model.Book;
import com.luminar.springbookproject.model.HighlightedStatement;
import com.luminar.springbookproject.services.BookService;

@Controller
@CrossOrigin(origins = "*")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("viewAllBooks", bookService.getallBooks());
		return "index";
	}
	
	@GetMapping("/add")
	public String addbooks(Model model) {
		return "add";
	}
	
    @PostMapping("/books")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        bookService.saveBook(book); // Save to DB or memory
        return ResponseEntity.ok(book);  // Send back confirmation
    }
    
    @GetMapping("/click/{id}")
    public String viewHighlightedStatements(@PathVariable(value = "id") long id ,Model model) {
    	 	Book book = bookService.getBookById(id);
    	    List<HighlightedStatement> highlightedStatements = bookService.highlights(id);

    	    model.addAttribute("book", book);
    	    model.addAttribute("highlightedStatements", highlightedStatements);
    	    return "card";
    }
    @PostMapping("/addhighlights")
    public ResponseEntity<?> addStatement(@RequestBody Map<String, String> request) {
        String statement = request.get("statement");
        long bookId = Long.parseLong(request.get("bookId"));

        if (statement == null || statement.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Statement is required.");
        }

        HighlightedStatement saved = bookService.saveHighlightedStatement(statement, bookId);
        return ResponseEntity.ok(saved);
    }
    @GetMapping("/delete")
    public String viewDeletePage(Model model) {
    	model.addAttribute("viewAllBooks",bookService.getallBooks());
    	return "deletebooks";
    }
    @GetMapping("/deleteBooks/{id}")
    public String deleteBooks(@PathVariable(value="id") long id) {
    	this.bookService.deleteBook(id);
    	return "redirect:/delete";
    }
	
}
