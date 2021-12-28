package net.greenbone.demolibrary.representations.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LendBookRequest {
    @NotNull(message = "No borrowing user specified. User is required.")
    private Long userId;

    @NotNull(message = "No Book specified. Book is required.")
    private Long bookId;
}
