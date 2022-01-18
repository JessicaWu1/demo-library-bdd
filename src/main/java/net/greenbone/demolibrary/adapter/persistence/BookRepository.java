package net.greenbone.demolibrary.adapter.persistence;

import net.greenbone.demolibrary.domain.aggregates.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAuthor(String author);
    List<Book> findAllByTitleContaining(String title);
}
