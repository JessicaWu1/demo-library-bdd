package net.greenbone.demolibrary.representations.response;

import lombok.*;
import net.greenbone.demolibrary.domain.aggregates.Book;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String description;
    private Integer publishingYear;
    private Integer quantity;

    public static BookResponse toBookResponse(Book book){
        return BookResponse.builder()
                .author(book.getAuthor())
                .description(book.getDescription())
                .id(book.getId())
                .publisher(book.getPublisher())
                .publishingYear(book.getPublishingYear())
                .quantity(book.getQuantity())
                .title(book.getTitle())
                .build();
    }
}
