package net.greenbone.demolibrary.domain.aggregates;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class LendBook {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "No borrowing user specified. User is required.")
    @ManyToOne
    //@JoinColumn(name = "user_id")
    @Setter
    private ApplicationUser applicationUser;

    @NotNull(message = "No Book specified. Book is required.")
    @ManyToOne
    //@JoinColumn(name = "book_id")
    @Setter
    private Book book;

    @Setter
    @NotNull(message = "No returnDate, but it is required.")
    private LocalDate returnDate;


}
