package net.greenbone.demolibrary.representations.response;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.domain.aggregates.LendBook;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class LendBookResponse {
    private Long id;
    private Long userId;
    private Long bookId;
    private LocalDate returnDate;
    private boolean returned;

    public static LendBookResponse lendBookToLendBookResponse(LendBook lendBook){
        log.info("LendBookResponse LendBook " + lendBook);
        return LendBookResponse.builder()
                .bookId(lendBook.getBook().getId())
                .id(lendBook.getId())
                .userId(lendBook.getApplicationUser().getId())
                .returnDate(lendBook.getReturnDate())
                .build();
    }
}
