package net.greenbone.demolibrary.services;

import lombok.RequiredArgsConstructor;
import net.greenbone.demolibrary.adapter.persistence.BookRepository;
import net.greenbone.demolibrary.domain.aggregates.Book;
import net.greenbone.demolibrary.services.helper.MapperDtoToEntity;

@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    public Book createNewBook(Book.Create book){
        Book newBook = MapperDtoToEntity.bookRequestToBook(book);
        return bookRepository.save(newBook);
    }

    public void updateBook(Long id, Book.Update book){
        Book toUpdate = bookRepository.findById(id).get();

        toUpdate.setAuthor(book.getAuthor());
        toUpdate.setDescription(book.getDescription());
        toUpdate.setPublisher(book.getPublisher());
        toUpdate.setQuantity(book.getQuantity());
        toUpdate.setTitle(book.getTitle());
        toUpdate.setPublishingYear(book.getPublishingYear());

        bookRepository.save(toUpdate);
    }
}
