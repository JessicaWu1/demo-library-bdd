package net.greenbone.demolibrary.domain.aggregates;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
    private Date returnDate;

    private boolean returned;

    @Builder
    private LendBook(Long id, ApplicationUser applicationUser, Book book, Date returnDate, boolean returned) {
        this.id = id;
        this.applicationUser = applicationUser;
        this.book = book;
        this.returnDate = returnDate;
        this.returned = returned;
    }

    public static LendBook fromCreate(Book book, ApplicationUser user, Create lendBookRequest) {
        return LendBook.builder()
                .book(book)
                .applicationUser(user)
                .returnDate(lendBookRequest.getReturnDate())
                .returned(lendBookRequest.getReturned())
                .build();
    }


    public void fromUpdate(Update lendBookRequest) {
        this.returnDate = lendBookRequest.getReturnDate();
        this.returned = lendBookRequest.getReturned();
    }

    public interface Create {
        Long getBookId();

        Long getUserId();

        Date getReturnDate();
        boolean getReturned();

    }

    public interface Update {
        Long getBookId();

        Long getUserId();

        Date getReturnDate();

        boolean getReturned();
    }
}
