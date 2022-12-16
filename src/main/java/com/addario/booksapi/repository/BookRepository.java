package com.addario.booksapi.repository;

import com.addario.booksapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Modifying
    @Query("update BOOKS b set b.title = :newTitle where b.id = :bookId")
    Integer updateBookTitle(@Param("bookId") Long id, @Param("newTitle") String newTitle);
}
