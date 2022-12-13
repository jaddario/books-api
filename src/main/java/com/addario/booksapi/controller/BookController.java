package com.addario.booksapi.controller;

import com.addario.booksapi.model.Book;
import com.addario.booksapi.model.BookDTO;
import com.addario.booksapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/books")
public class BookController {

    private final BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<Book> insert(BookDTO dto){

//        var book = Book.from(dto);
//        service.insert(book);

        return ResponseEntity.ok(new Book());
    }

    @GetMapping("/")
    public ResponseEntity<List<Book>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteById(@PathVariable Long id){

        service.deleteById(id);

        return ResponseEntity.ok(service.findById(id));
    }

}
