package net.greenbone.demolibrary.domain.services.helper;

import net.greenbone.demolibrary.domain.aggregates.ApplicationUser;
import net.greenbone.demolibrary.domain.aggregates.Book;
import net.greenbone.demolibrary.domain.aggregates.LendBook;
import net.greenbone.demolibrary.representations.response.BookResponse;
import net.greenbone.demolibrary.representations.response.LendBookResponse;
import net.greenbone.demolibrary.representations.response.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

public class MapperEntityToDto {

    public static LendBookResponse lendBookToLendBookResponse(LendBook lendBook){
        return LendBookResponse.builder()
                .bookId(lendBook.getBook().getId())
                .id(lendBook.getId())
                .userId(lendBook.getApplicationUser().getId())
                .returnDate(lendBook.getReturnDate())
                .build();
    }
}
