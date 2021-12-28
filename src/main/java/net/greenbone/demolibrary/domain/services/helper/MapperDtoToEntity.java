package net.greenbone.demolibrary.domain.services.helper;

import net.greenbone.demolibrary.domain.aggregates.Book;
import net.greenbone.demolibrary.representations.request.BookRequest;

public class MapperDtoToEntity {
    public static Book bookRequestToBook(Book.Create book){
        return Book.builder()
                .author(book.getAuthor())
                .title(book.getTitle())
                .description(book.getDescription())
                .publisher(book.getPublisher())
                .quantity(book.getQuantity())
                .publishingYear(book.getPublishingYear())
                .build();
    }
}
