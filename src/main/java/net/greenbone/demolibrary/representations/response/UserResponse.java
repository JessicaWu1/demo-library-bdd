package net.greenbone.demolibrary.representations.response;


import lombok.*;
import net.greenbone.demolibrary.domain.aggregates.ApplicationUser;
import net.greenbone.demolibrary.domain.aggregates.Book;
import net.greenbone.demolibrary.domain.aggregates.LendBook;
import net.greenbone.demolibrary.domain.enums.Role;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<Long> borrowedBooks;

    public static UserResponse applicationUserToUserResponse(ApplicationUser applicationUser) {
        List<Long> borrowedBooks = applicationUser.getBorrowedBooks()
                .stream()
                .map(LendBook::getId)
                .collect(Collectors.toList());
        return UserResponse.builder()
                .id(applicationUser.getId())
                .name(applicationUser.getName())
                .borrowedBooks(borrowedBooks)
                .password(applicationUser.getPassword())
                .lateFees(applicationUser.getLateFees())
                .email(applicationUser.getEmail())
                .role(applicationUser.getRole().name())
                .build();
    }
}
