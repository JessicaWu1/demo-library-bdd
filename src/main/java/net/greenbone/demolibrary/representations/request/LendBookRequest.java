package net.greenbone.demolibrary.representations.request;

import lombok.*;
import net.greenbone.demolibrary.domain.aggregates.LendBook;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class LendBookRequest implements LendBook.Create, LendBook.Update {
    @NotNull(message = "No borrowing user specified. User is required.")
    private Long userId;

    @NotNull(message = "No Book specified. Book is required.")
    private Long bookId;

    private int returnDateIn;
    private boolean returned;

    @Override
    public boolean getReturned() {
        return returned;
    }
}
