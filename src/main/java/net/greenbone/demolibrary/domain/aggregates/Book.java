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
public class Book {
    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long id;

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

    @Builder
    private Book(Long id, String title, String author, String publisher, String description, Integer publishingYear, Integer quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
        this.publishingYear = publishingYear;
        this.quantity = quantity;
    }

    public static Book fromCreate(Book.Create book){
        return Book.builder()
                .author(book.getAuthor())
                .title(book.getTitle())
                .description(book.getDescription())
                .publisher(book.getPublisher())
                .quantity(book.getQuantity())
                .publishingYear(book.getPublishingYear())
                .build();
    }

    public void fromUpdate(Book.Update book){
        this.author = book.getAuthor();
        this.description = book.getDescription();
        this.publisher = book.getPublisher();
        this.quantity = book.getQuantity();
        this.title = book.getTitle();
        this.publishingYear = book.getPublishingYear();
    }
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
