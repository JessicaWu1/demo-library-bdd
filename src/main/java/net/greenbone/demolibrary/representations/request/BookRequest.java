package net.greenbone.demolibrary.representations.request;

import lombok.*;
import net.greenbone.demolibrary.domain.aggregates.Book;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest implements Book.Create, Book.Update{
    @NotNull(message = "Title required.")
    @NotEmpty(message = "Title is not specified.")
    private String title;

    @NotNull(message = "Author required.")
    @NotEmpty(message = "Author not specified.")
    private String author;

    @NotNull(message = "Publisher required.")
    @NotEmpty(message = "Publisher is empty.")
    private String publisher;

    @NotNull(message = "Description required.")
    @NotEmpty(message = "Description not specified.")
    private String description;

    @NotNull(message = "Published Year is required.")
    private Integer publishingYear;

    @NotNull(message = "Quantity is required.")
    private Integer quantity;
}
