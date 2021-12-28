package net.greenbone.demolibrary.domain.aggregates;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    @NotNull(message = "Title required.")
    @NotEmpty(message = "Title is not specified.")
    @Setter
    private String title;

    @NotNull(message = "Author required.")
    @NotEmpty(message = "Author not specified.")
    @Setter
    private String author;

    @NotNull(message = "Publisher required.")
    @NotEmpty(message = "Publisher is empty.")
    @Setter
    private String publisher;

    @NotNull(message = "Description required.")
    @NotEmpty(message = "Description not specified.")
    @Setter
    private String description;

    @NotNull(message = "Published Year is required.")
    @Setter
    private Integer publishingYear;

    @NotNull(message = "Quantity is required.")
    @Setter
    private Integer quantity;



    public interface Create {

        String getAuthor();

        String getTitle();

        String getDescription();

        String getPublisher();

        Integer getQuantity();

        Integer getPublishingYear();
    }

    public interface Update {
        String getAuthor();

        String getTitle();

        String getDescription();

        String getPublisher();

        Integer getQuantity();

        Integer getPublishingYear();
    }
}
