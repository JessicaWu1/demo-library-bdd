package net.greenbone.demolibrary.domain.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.adapter.persistence.BookRepository;
import net.greenbone.demolibrary.domain.aggregates.Book;
import net.greenbone.demolibrary.domain.services.helper.MapperDtoToEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookService {
    private final BookRepository bookRepository;

    public Book getBookById(Long id) {
        try{
            return bookRepository.findById(id).get();
        } catch (Exception e){
            log.error(e.getMessage(), e.getStackTrace());
            return null;
        }
    }

    public Book createNewBook(Book.Create book){
        try{
            Book newBook = MapperDtoToEntity.bookRequestToBook(book);
            Book createdNewBook = bookRepository.save(newBook);
            return createdNewBook;
        }catch(Exception e){
            log.error(e.getMessage(), e.getStackTrace());
            return null;
        }
    }

    public boolean updateBook(Long id, Book.Update book){
        try{
            Book toUpdate = bookRepository.findById(id).get();

            toUpdate.setAuthor(book.getAuthor());
            toUpdate.setDescription(book.getDescription());
            toUpdate.setPublisher(book.getPublisher());
            toUpdate.setQuantity(book.getQuantity());
            toUpdate.setTitle(book.getTitle());
            toUpdate.setPublishingYear(book.getPublishingYear());

            bookRepository.save(toUpdate);
            return true;
        }catch(Exception e){
            log.error(e.getMessage(), e.getStackTrace());
            return false;
        }
    }

    public Book deleteBookWithId(Long id){
        try{
            Book deletedBook = bookRepository.findById(id).get();
            bookRepository.deleteById(id);
            return deletedBook;
        }catch(Exception e){
            log.error(e.getMessage(), e.getStackTrace());
            return null;
        }
    }

}
