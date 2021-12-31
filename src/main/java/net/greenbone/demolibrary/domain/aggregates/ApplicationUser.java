package net.greenbone.demolibrary.domain.aggregates;

import lombok.*;
import net.greenbone.demolibrary.domain.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class ApplicationUser {
    @Id
    @GeneratedValue
    @Setter
    private Long id;

    @NotEmpty(message = "Name is not specified")
    @NotNull(message = "Nam e is required.")
    private String name;

    @NotEmpty(message = "E-Mail Address not specified")
    @NotNull(message = "E-Mail address required.")
    @Email
    private String email;

    @NotEmpty(message = "password not specified")
    @NotNull(message = "Password required.")
    private String password;

    @NotNull(message = "User role required.")
    private Role role;

    @NotNull(message = "Late Fees instance required")
    private Float lateFees;

    @OneToMany(mappedBy = "applicationUser", cascade = CascadeType.ALL)
    private List<LendBook> borrowedBooks;

    @Builder
    private ApplicationUser(String name, String email, String password, Role role, Float lateFees, List<LendBook> borrowedBooks) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.lateFees = (lateFees != null) ? lateFees : 0.0f;
        this.borrowedBooks = (borrowedBooks != null) ? borrowedBooks : new ArrayList<>();
    }

    public static ApplicationUser fromCreate(ApplicationUser.Create user, List<LendBook> borrowedBooks) {
        return ApplicationUser.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .role(Role.valueOf(user.getRole()))
                .borrowedBooks(borrowedBooks)
                .lateFees(user.getLateFees())
                .name(user.getName())
                .build();
    }

    public static ApplicationUser fromCreateWithoutBorrowedBooks(ApplicationUser.Create user) {
        return ApplicationUser.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .role(Role.valueOf(user.getRole()))
                .lateFees(user.getLateFees())
                .name(user.getName())
                .build();
    }

    public void fromUpdate(ApplicationUser.Update user, List<LendBook> borrowedBooks){
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.lateFees = user.getLateFees();
        this.borrowedBooks = borrowedBooks;
        this.role = Role.valueOf(user.getRole());
    }

    public interface Create {
        String getEmail();

        Float getLateFees();

        String getPassword();

        List<Long> getBorrowedBooks();

        String getName();

        String getRole();
    }

    public interface Update {
        String getEmail();

        Float getLateFees();

        String getPassword();

        List<Long> getBorrowedBooks();

        String getName();

        String getRole();
    }
}
