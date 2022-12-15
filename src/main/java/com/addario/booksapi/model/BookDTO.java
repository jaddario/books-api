package com.addario.booksapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@AllArgsConstructor
public class BookDTO {
    private Long id;

    private String title;

    private String author;

    private String subject;

    public static BookDTO from(Book entity) {
        return BookDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .subject(entity.getSubject())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(id, bookDTO.id) &&
                Objects.equals(author, bookDTO.author) &&
                Objects.equals(title, bookDTO.title) &&
                Objects.equals(subject, bookDTO.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, subject);
    }
}
