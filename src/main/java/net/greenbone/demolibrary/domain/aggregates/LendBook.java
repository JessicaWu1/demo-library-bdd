package net.greenbone.demolibrary.domain.aggregates;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
public class LendBook {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "No borrowing user specified. User is required.")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private ApplicationUser applicationUser;

    @NotNull(message = "No Book specified. Book is required.")
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @NotNull(message = "No returnDate, but it is required.")
    private LocalDate returnDate;

    @Builder
    private LendBook(ApplicationUser applicationUser, Book book, LocalDate returnDate) {
        this.applicationUser = applicationUser;
        this.book = book;
        this.returnDate = returnDate;
    }
}
