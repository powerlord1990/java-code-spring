package com.company.service;

import com.company.entity.Book;
import com.company.exception.BookNotFoundException;
import com.company.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        Book book = bookRepository.findById(id);
        if (book != null) {
            return book;
        } else {
            throw new BookNotFoundException("Book with id: " + id + " not found");
        }
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public void update(long id, Book bookDetails) {
        Book finedBook = findById(id);
        if (finedBook != null) {
            bookDetails.setId(id);
            bookRepository.update(bookDetails);
        } else {
            throw new BookNotFoundException("Book with id: " + id + " not found");
        }
    }

    public void deleteById(Long id) {
        if (findById(id) != null) {
            bookRepository.deleteById(id);
        } else {
            throw new BookNotFoundException("Book with id: " + id + " not found");
        }
    }
}