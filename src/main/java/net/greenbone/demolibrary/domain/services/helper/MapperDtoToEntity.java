package net.greenbone.demolibrary.domain.services.helper;

import net.greenbone.demolibrary.domain.aggregates.ApplicationUser;
import net.greenbone.demolibrary.domain.aggregates.Book;
import net.greenbone.demolibrary.domain.enums.Role;
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

    public static ApplicationUser userRequestToUser(ApplicationUser.Create user) {
        return ApplicationUser.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .role(Role.valueOf(user.getRole()))
                .borrowedBooks(user.getBorrowedBooks())
                .lateFees(user.getLateFees())
                .name(user.getName())
                .build();
    }
}
