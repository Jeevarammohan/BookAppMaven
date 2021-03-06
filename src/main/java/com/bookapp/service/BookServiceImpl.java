package com.bookapp.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.IdNotFoundException;
import com.bookapp.model.Book;
import com.bookapp.repository.BookRepositoryImpl;
import com.bookapp.repository.IBookRepository;

public class BookServiceImpl implements IBookService {
	IBookRepository iBookRepository = new BookRepositoryImpl();

	@Override
	public Integer addBook(Book book) {
		return iBookRepository.addBook(book);
	}

	@Override
	public void updateBook(int bookId, double price) {
		iBookRepository.updateBook(bookId, price);
	}

	@Override
	public void deleteBook(int bookId) {
		iBookRepository.deleteBook(bookId);
	}

	@Override
	public List<Book> getByAuthor(String author) throws BookNotFoundException {
		List<Book> booksByAuthor=iBookRepository.findByAuthor(author);
		if(booksByAuthor.isEmpty()) {
			throw new BookNotFoundException("Author Not Found");
		}
		return booksByAuthor.stream().sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.toList());
	}

	@Override
	public List<Book> getByCategory(String category) throws BookNotFoundException {
		List<Book> booksByCategory=iBookRepository.findByCategory(category);
		if(booksByCategory.isEmpty()) {
			throw new BookNotFoundException("Category Not Found");
		}
		return booksByCategory.stream().sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.toList());
	}

	@Override
	public List<Book> getByLesserPrice(double price) throws BookNotFoundException {
		List<Book> booksByLesserPrice=iBookRepository.findByLesserPrice(price);
		if(booksByLesserPrice.isEmpty()) {
			throw new BookNotFoundException("Price Not Found");
		}
		return booksByLesserPrice.stream().sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.toList());
	}

	@Override
	public Book getById(int bookId) throws IdNotFoundException {
		Book book=iBookRepository.findById(bookId);
		return book;
	}

}
