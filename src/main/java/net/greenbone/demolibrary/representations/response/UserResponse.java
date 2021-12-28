package net.greenbone.demolibrary.representations.response;


import lombok.*;
import net.greenbone.demolibrary.domain.aggregates.LendBook;
import net.greenbone.demolibrary.domain.enums.Role;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private Float lateFees;
    private List<LendBookResponse> borrowedBooks;
}
