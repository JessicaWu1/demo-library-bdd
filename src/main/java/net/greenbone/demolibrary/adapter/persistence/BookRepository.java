package net.greenbone.demolibrary.adapter.persistence;

import net.greenbone.demolibrary.domain.aggregates.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
