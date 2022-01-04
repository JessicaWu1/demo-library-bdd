package net.greenbone.demolibrary.domain.services;

import net.greenbone.demolibrary.adapter.persistence.BookRepository;
import net.greenbone.demolibrary.domain.aggregates.Book;
import net.greenbone.demolibrary.representations.request.BookRequest;
import net.greenbone.demolibrary.representations.response.BookResponse;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(MockitoJUnitRunner.class)

public class BookServiceTest {

    @InjectMocks
    private BookService bookService;
    @Mock
    private Book book;
    @Mock
    private BookResponse bookResponse;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookRequest bookRequest;


    @Before
    public void setUp(){

    }
    @Test
    public void expect_getBookById_toReturn() {
        when(bookRepository.findById(anyLong()))
                .thenReturn(Optional.of(book));

        Book result = bookService.getBookById(1L);

        assertThat(book, Matchers.is(result));
    }

    @Test
    public void expect_createNewBook_toReturn() {
        when(bookRepository.save(any(Book.class)))
                .thenReturn(book);

        Book result = bookService.createNewBook(bookRequest);

        assertThat(book, Matchers.is(result));
    }

    @Test
    public void expect_updateBook_toReturn() {
        when(bookRepository.findById(anyLong()))
                .thenReturn(Optional.of(book));

        bookService.updateBook(1L, bookRequest);

        verify(bookRepository, times(1)).findById(anyLong());
        verify(bookRepository,times(1)).save(any(Book.class));
    }

    @Test
    public void expect_deleteBookWithId_toReturn() {

        bookService.deleteBookWithId(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }
}