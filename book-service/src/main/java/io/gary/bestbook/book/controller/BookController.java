package io.gary.bestbook.book.controller;

import io.gary.bestbook.book.EntityMapper;
import io.gary.bestbook.book.model.Book;
import io.gary.bestbook.book.model.BookDto;
import io.gary.bestbook.book.model.UserDto;
import io.gary.bestbook.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
class BookController {

    @Autowired
    private EntityMapper mapper;

    @Autowired
    private BookService bookService;

    @GetMapping("/users")
    Collection<UserDto> listAllUsers() {
        return bookService.listAllUsers();
    }

    @GetMapping("/books")
    Collection<BookDto> findAllBooks() {
        return bookService.findAllBooks().stream().map(mapper::toDto).collect(toList());
    }

    @PostMapping("/books")
    @ResponseStatus(CREATED)
    BookDto createBook(@RequestBody @Valid BookDto bookDto, Principal principal) {

        Book bookData = mapper.fromDto(bookDto);
        bookData.setCreatedBy(principal.getName());
        bookData.setCreatedAt(LocalDateTime.now());
        bookData.setLastModifiedBy(principal.getName());
        bookData.setLastModifiedAt(LocalDateTime.now());

        return mapper.toDto(bookService.createBook(bookData));
    }

    @GetMapping("/books/{id}")
    BookDto getBook(@PathVariable Long id) {
        return mapper.toDto(bookService.getBook(id));
    }

    @PutMapping("/books/{id}")
    BookDto updateBook(@PathVariable Long id, @RequestBody @Valid BookDto bookDto, Principal principal) {

        Book bookData = mapper.fromDto(bookDto.withId(id));
        bookData.setLastModifiedBy(principal.getName());
        bookData.setLastModifiedAt(LocalDateTime.now());

        return mapper.toDto(bookService.updateBook(bookData));
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(NO_CONTENT)
    void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

}
