package net.greenbone.demolibrary.domain.aggregates;

import lombok.*;
import net.greenbone.demolibrary.domain.enums.Role;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class ApplicationUser {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Name is not specified")
    @NotNull(message = "Nam e is required.")
    @Setter
    private String name;

    @NotEmpty(message = "E-Mail Address not specified")
    @NotNull(message = "E-Mail address required.")
    @Setter
    private String email;

    @NotEmpty(message = "password not specified")
    @NotNull(message = "Password required.")
    @Setter
    private String password;

    @NotNull(message = "User role required.")
    @Setter
    private Role role;

    @NotNull(message = "Late Fees instance required")
    @Setter
    private Float lateFees;

    @OneToMany(mappedBy = "book")
    @Setter
    private List<LendBook> borrowedBooks;

    @Builder
    public ApplicationUser(String name, String email, String password, Role role, Float lateFees, List<LendBook> borrowedBooks) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.lateFees = (lateFees != null) ? lateFees : 0.0f;
        this.borrowedBooks = (borrowedBooks != null) ? borrowedBooks : new ArrayList<>();
    }
}
