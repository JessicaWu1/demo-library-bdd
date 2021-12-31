package net.greenbone.demolibrary.adapter.persistence;

import net.greenbone.demolibrary.domain.aggregates.LendBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LendBookRepository extends JpaRepository<LendBook, Long> {
}
