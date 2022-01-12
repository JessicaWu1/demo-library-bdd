package net.greenbone.demolibrary.domain.services;

import net.greenbone.demolibrary.adapter.persistence.ApplicationUserRepository;
import net.greenbone.demolibrary.adapter.persistence.BookRepository;
import net.greenbone.demolibrary.adapter.persistence.LendBookRepository;
import net.greenbone.demolibrary.domain.aggregates.ApplicationUser;
import net.greenbone.demolibrary.domain.aggregates.Book;
import net.greenbone.demolibrary.domain.aggregates.LendBook;
import net.greenbone.demolibrary.representations.request.LendBookRequest;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LendBookServiceTest {

    @InjectMocks
    private LendBookService lendBookService;
    @Mock
    private LendBookRepository lendBookRepository;
    @Mock
    private ApplicationUserRepository applicationUserRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private LendBook lendBook;
    @Mock
    private LendBookRequest lendBookRequest;
    @Mock
    private ApplicationUser applicationUser;
    @Mock
    private Book book;

    @Test
    public void expect_lendingABook_toReturn() {
        when(bookRepository.findById(anyLong()))
                .thenReturn(Optional.of(book));
        when(applicationUserRepository.findById(anyLong()))
                .thenReturn(Optional.of(applicationUser));
        when(lendBookRepository.save(any(LendBook.class)))
                .thenReturn(lendBook);
        //

        LendBook result = lendBookService.lendingABook(lendBookRequest);

        assertThat(lendBook, Matchers.is(result));
    }
}