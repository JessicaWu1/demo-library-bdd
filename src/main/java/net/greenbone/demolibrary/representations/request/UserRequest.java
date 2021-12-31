package net.greenbone.demolibrary.representations.request;

import lombok.*;
import net.greenbone.demolibrary.domain.aggregates.ApplicationUser;
import net.greenbone.demolibrary.domain.aggregates.LendBook;
import net.greenbone.demolibrary.domain.enums.Role;
import org.springframework.context.ApplicationListener;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest implements ApplicationUser.Create, ApplicationUser.Update {
    @NotEmpty(message = "Name is not specified")
    @NotNull(message = "Nam e is required.")
    private String name;

    @NotEmpty(message = "E-Mail Address not specified")
    @NotNull(message = "E-Mail address required.")
    private String email;

    @NotEmpty(message = "password not specified")
    @NotNull(message = "Password required.")
    private String password;

    @NotNull(message = "User role required.")
    private String role;

    private Float lateFees;
    private List<LendBook> borrowedBooks;

}
