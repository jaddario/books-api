package com.addario.booksapi.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super(String.format("Book not found for id %d", id));
    }
}
