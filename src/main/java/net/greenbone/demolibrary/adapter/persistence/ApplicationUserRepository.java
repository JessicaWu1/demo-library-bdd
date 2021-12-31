package net.greenbone.demolibrary.adapter.persistence;

import net.greenbone.demolibrary.domain.aggregates.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
}
