package io.gary.bestbook.book.service;

import io.gary.bestbook.book.client.AuthServiceClient;
import io.gary.bestbook.book.errors.BookNotFoundException;
import io.gary.bestbook.book.model.Book;
import io.gary.bestbook.book.model.UserDto;
import io.gary.bestbook.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthServiceClient authServiceClient;

    @Transactional(readOnly = true)
    public Collection<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public Book createBook(Book bookData) {
        return bookRepository.save(bookData);
    }

    @Transactional(readOnly = true)
    public Book getBook(Long bookId) {
        return findByBookIdOrThrow(bookId);
    }

    @Transactional
    public Book updateBook(Book bookData) {
        Book existingBook = findByBookIdOrThrow(bookData.getId());
        bookData.setCreatedAt(existingBook.getCreatedAt());
        bookData.setCreatedBy(existingBook.getCreatedBy());
        return bookRepository.save(bookData);
    }

    @Transactional
    public Book deleteBook(Long bookId) {
        Book book = findByBookIdOrThrow(bookId);
        bookRepository.delete(bookId);
        return book;
    }

    public Collection<UserDto> listAllUsers() {
        return authServiceClient.listAllUsers();
    }

    private Book findByBookIdOrThrow(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
    }
}
