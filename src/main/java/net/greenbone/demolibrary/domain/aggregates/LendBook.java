package net.greenbone.demolibrary.domain.aggregates;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
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
    private ApplicationUser applicationUser;

    @NotNull(message = "No Book specified. Book is required.")
    @OneToOne
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
        Date currentTime = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentTime);
        calendar.add(Calendar.DATE, 14);
        Date returnDate = calendar.getTime();
        return LendBook.builder()
                .book(book)
                .applicationUser(user)
                .returnDate(returnDate)
                .returned(lendBookRequest.getReturned())
                .build();
    }


    public void fromUpdate(Update lendBookRequest) {
        this.returnDate = setReturnDateForUpdate(lendBookRequest.getReturnDateIn());
        this.returned = lendBookRequest.getReturned();
    }

    private Date setReturnDateForUpdate(int datesInTheFuture) {
        Date currentTime = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentTime);
        calendar.add(Calendar.DATE, datesInTheFuture);
        return calendar.getTime();
    }

    public interface Create {
        Long getBookId();

        Long getUserId();

        int getReturnDateIn();
        boolean getReturned();

    }

    public interface Update {
        Long getBookId();

        Long getUserId();

        int getReturnDateIn();

        boolean getReturned();
    }
}
