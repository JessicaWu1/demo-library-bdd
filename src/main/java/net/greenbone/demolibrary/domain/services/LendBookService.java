package net.greenbone.demolibrary.domain.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.adapter.persistence.ApplicationUserRepository;
import net.greenbone.demolibrary.adapter.persistence.BookRepository;
import net.greenbone.demolibrary.adapter.persistence.LendBookRepository;
import net.greenbone.demolibrary.domain.aggregates.ApplicationUser;
import net.greenbone.demolibrary.domain.aggregates.Book;
import net.greenbone.demolibrary.domain.aggregates.LendBook;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Slf4j
public class LendBookService {
    private final LendBookRepository lendBookRepository;
    private final ApplicationUserRepository applicationUserRepository;
    private final BookRepository bookRepository;

    public LendBook lendingABook(LendBook.Create lendBookRequest){
        log.info("Service LendBook");
        Book book = bookRepository.findById(lendBookRequest.getBookId())
                .orElseThrow(() -> new NoSuchElementException("Could not find Book with ID: " + lendBookRequest.getBookId()));
        ApplicationUser user = applicationUserRepository.findById(lendBookRequest.getUserId())
                .orElseThrow(() -> new NoSuchElementException("Could not find Book with ID: " + lendBookRequest.getUserId()));
        log.info("Service Lendbook User: "+ user);
        log.info("Service Lendbook book: "+ book);
        LendBook lendBook = LendBook.fromCreate(book, user, lendBookRequest);
        log.info("Service LendBook lendbook" + lendBook);
        LendBook createdLendBook = lendBookRepository.save(lendBook);
        user.getBorrowedBooks().add(createdLendBook);
        log.info("Service LendBook created " + createdLendBook);
        applicationUserRepository.save(user);
        return createdLendBook;
    }

    public void updateLendBookById(Long id, LendBook.Update lendBookRequest) {
        LendBook toUpdate = lendBookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Could not find LendBook with ID: " + id));

        toUpdate.fromUpdate(lendBookRequest);
        lendBookRepository.save(toUpdate);
    }

    public void deleteLendBook(Long id){
        lendBookRepository.deleteById(id);
    }
}
