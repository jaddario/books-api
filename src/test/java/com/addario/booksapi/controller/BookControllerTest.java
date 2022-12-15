package com.addario.booksapi.controller;

import com.addario.booksapi.model.Book;
import com.addario.booksapi.model.BookDTO;
import com.addario.booksapi.repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

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
    void insert_isSuccessful() throws Exception {

        var book = BookDTO.builder()
                .id(1L)
                .title("Harry Potter")
                .author("J.K.Rowlings")
                .subject("Fantasia")
                .build();

        mockMvc.perform(post("/")
                        .content(asJsonString(book))
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Harry Potter"))
                .andExpect(jsonPath("$.author").value("J.K.Rowlings"))
                .andExpect(jsonPath("$.subject").value("Fantasia"));
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

        mockMvc.perform(get("/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Odisseia"))
                .andExpect(jsonPath("$[0].author").value("Homero"))
                .andExpect(jsonPath("$[0].subject").value("Mitologia"));

    }

    @Test
    void deleteId_isSuccessful() throws Exception {

        var book = Book.builder()
                .id(1L)
                .title("Os Lusíadas")
                .author("Camões")
                .subject("Epic Poem")
                .build();

        repository.save(book);

        mockMvc.perform(delete("/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void findById_isSuccessful() throws Exception {

        var book = Book.builder()
                .id(1L)
                .title("Odisseia")
                .author("Homero")
                .subject("Mitologia")
                .build();

        repository.save(book);

        mockMvc.perform(get("/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Odisseia"))
                .andExpect(jsonPath("$.author").value("Homero"))
                .andExpect(jsonPath("$.subject").value("Mitologia"));
    }

    private String asJsonString(BookDTO book) {
        try {
            return new ObjectMapper().writeValueAsString(book);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}