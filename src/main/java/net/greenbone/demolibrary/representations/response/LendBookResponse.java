package net.greenbone.demolibrary.representations.response;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LendBookResponse {
    private Long id;
    private Long userId;
    private Long bookId;
    private LocalDate returnDate;
}
