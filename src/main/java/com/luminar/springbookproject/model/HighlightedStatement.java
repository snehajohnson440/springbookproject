package com.luminar.springbookproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="highlighted_statements")
public class HighlightedStatement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name="id")
	private long id;
	
	@Column(name="highlighted_statement")
	private String statement;
	
	@ManyToOne
	@JoinColumn(name = "book_id" , nullable = false)
	private Book book;
	
    public HighlightedStatement() {}

    public HighlightedStatement(String statement, Book book) {
        this.statement = statement;
        this.book = book;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}


}
