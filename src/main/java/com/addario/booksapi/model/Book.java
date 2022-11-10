package com.addario.booksapi.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@Entity(name = "BOOKS")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "SUBJECT")
    private String subject;

    public static Book from(BookDTO dto) {
        return Book.builder()
                   .id(dto.getId())
                   .name(dto.getName())
                   .author(dto.getAuthor())
                   .title(dto.getTitle())
                   .subject(dto.getSubject())
                   .build();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
               Objects.equals(name, book.name) &&
               Objects.equals(author, book.author) &&
               Objects.equals(title, book.title) &&
               Objects.equals(subject, book.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, title, subject);
    }
}
