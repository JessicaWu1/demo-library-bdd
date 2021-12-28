package net.greenbone.demolibrary.representations.response;

import lombok.*;

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
}
