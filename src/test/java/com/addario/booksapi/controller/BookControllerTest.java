package com.addario.booksapi.controller;

import com.addario.booksapi.model.Book;
import com.addario.booksapi.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
class BookControllerTest {

    @Autowired
    private BookController underTest;

    @Autowired
    private BookRepository repository;

    private MockMvc mockMvc;

    @Container
    private static PostgreSQLContainer container = (PostgreSQLContainer) new PostgreSQLContainer("postgres:14.1-alpine");

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }
    @Test
    void findAll_isSuccessful() throws Exception {

        var book = Book.builder()
                .id(1L)
                .name("Odisseia")
                .author("Homero")
                .title("Odisseia")
                .subject("Mitologia")
                .build();

        repository.save(book);

        List<Book> body = this.underTest.findAll().getBody();

        assertThat(body).hasSize(1);

    }


}