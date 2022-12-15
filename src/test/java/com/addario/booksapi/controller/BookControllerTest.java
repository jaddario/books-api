package com.addario.booksapi.controller;

import com.addario.booksapi.model.Book;
import com.addario.booksapi.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private BookController underTest;

    @Autowired
    private BookRepository repository;

    @Autowired
    public MockMvc mockMvc;

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
                .title("Odisseia")
                .author("Homero")
                .subject("Mitologia")
                .build();

        repository.save(book);

        List<Book> body = this.underTest.findAll().getBody();

        mockMvc.perform(get("/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Odisseia"))
                .andExpect(jsonPath("$[0].author").value("Homero"))
                .andExpect(jsonPath("$[0].subject").value("Mitologia"));

        assertThat(body).hasSize(1);

    }


}