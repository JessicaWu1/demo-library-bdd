package net.greenbone.demolibrary.representations.response;

import lombok.*;
import net.greenbone.demolibrary.domain.aggregates.LendBook;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LendBookResponse {
    private Long id;
    private Long userId;
    private Long bookId;
    private Date returnDate;
    private boolean returned;

    public static LendBookResponse lendBookToLendBookResponse(LendBook lendBook){
        return LendBookResponse.builder()
                .bookId(lendBook.getBook().getId())
                .id(lendBook.getId())
                .userId(lendBook.getApplicationUser().getId())
                .returnDate(lendBook.getReturnDate())
                .build();
    }
}
