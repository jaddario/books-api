package com.addario.booksapi.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BookDTO {
    private Long id;

    private String name;

    private String author;

    private String title;

    private String subject;

//    public static BookDTO from(Book entity) {
//        return BookDTO.builder()
//                      .id(entity.getId())
//                      .author(entity.getAuthor())
//                      .title(entity.getTitle())
//                      .subject(entity.getSubject())
//                      .build();
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(id, bookDTO.id) && Objects.equals(name, bookDTO.name) && Objects.equals(author,
                bookDTO.author) && Objects.equals(title, bookDTO.title) && Objects.equals(subject, bookDTO.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, title, subject);
    }
}
