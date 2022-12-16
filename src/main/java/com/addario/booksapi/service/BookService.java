package com.addario.booksapi.service;

import com.addario.booksapi.exceptions.BookNotFoundException;
import com.addario.booksapi.model.Book;
import com.addario.booksapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void insert(Book book) {
        repository.save(book);
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public Book findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Transactional
    public void updateBookTitle(Long id, String newTitle) {
        Integer updatedLines = repository.updateBookTitle(id, newTitle);
        if (hasNot(updatedLines))
            throw new BookNotFoundException(id);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private static boolean hasNot(Integer updatedLines) {
        return updatedLines <= 0;
    }
}
