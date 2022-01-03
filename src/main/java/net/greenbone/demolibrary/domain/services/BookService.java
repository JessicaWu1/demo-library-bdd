package net.greenbone.demolibrary.domain.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.adapter.persistence.BookRepository;
import net.greenbone.demolibrary.domain.aggregates.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookService {
    private final BookRepository bookRepository;

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("could not find Book with ID: " + id));
    }

    public Book createNewBook(Book.Create book){
        try{
            Book newBook = Book.fromCreate(book);
            Book createdNewBook = bookRepository.save(newBook);
            return createdNewBook;
        }catch(Exception e){
            log.error(e.getMessage(), e.getStackTrace());
            return null;
        }
    }

    public void updateBook(Long id, Book.Update book){
        Book toUpdate = bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Could not Find Book with ID: " +id));
        toUpdate.fromUpdate(book);
        bookRepository.save(toUpdate);
    }

    public void deleteBookWithId(Long id){
        bookRepository.deleteById(id);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}
