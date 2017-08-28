package io.gary.bestbook.book;

import io.gary.bestbook.book.model.Book;
import io.gary.bestbook.book.model.BookDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    BookDto toDto(Book book);

    Book fromDto(BookDto bookDto);
}
