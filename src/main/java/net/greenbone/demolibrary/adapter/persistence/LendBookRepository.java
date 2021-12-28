package net.greenbone.demolibrary.adapter.persistence;

import net.greenbone.demolibrary.domain.aggregates.LendBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LendBookRepository extends JpaRepository<LendBook, Long> {
}
