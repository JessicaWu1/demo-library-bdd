package net.greenbone.demolibrary.domain.services.helper;

import net.greenbone.demolibrary.domain.aggregates.Book;
import net.greenbone.demolibrary.domain.aggregates.LendBook;
import net.greenbone.demolibrary.domain.aggregates.User;
import net.greenbone.demolibrary.representations.response.BookResponse;
import net.greenbone.demolibrary.representations.response.LendBookResponse;
import net.greenbone.demolibrary.representations.response.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

public class MapperEntityToDto {
    public static BookResponse bookToBookResponse(Book book){
        return BookResponse.builder()
                .author(book.getAuthor())
                .description(book.getDescription())
                .id(book.getId())
                .publisher(book.getPublisher())
                .publishingYear(book.getPublishingYear())
                .quantity(book.getQuantity())
                .title(book.getTitle())
                .build();
    }

    public static UserResponse userToUserResponse(User user){
        List<LendBookResponse> borrowedBooks = user.getBorrowedBooks()
                                                            .stream()
                                                            .map(MapperEntityToDto::lendBookToLendBookResponse)
                                                            .collect(Collectors.toList());
        return UserResponse.builder()
                .role(user.getRole().name())
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .lateFees(user.getLateFees())
                .password(user.getPassword())
                .borrowedBooks(borrowedBooks)
                .build();
    }

    public static LendBookResponse lendBookToLendBookResponse(LendBook lendBook){
        return LendBookResponse.builder()
                .bookId(lendBook.getBook().getId())
                .id(lendBook.getId())
                .userId(lendBook.getUser().getId())
                .returnDate(lendBook.getReturnDate())
                .build();
    }
}
