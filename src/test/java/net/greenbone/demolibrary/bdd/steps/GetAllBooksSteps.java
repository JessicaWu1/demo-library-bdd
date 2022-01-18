package net.greenbone.demolibrary.bdd.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.greenbone.demolibrary.bdd.helper.adapter.context.UserContext;
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.BookClient;
import net.greenbone.demolibrary.representations.response.BookResponse;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RequiredArgsConstructor
@Slf4j
public class GetAllBooksSteps {

    private final UserContext userContext;
    private List<BookResponse> books;

    @When("user wants all books")
    public void userWantsAllBooks() {
        books = userContext.getFeignClient(BookClient.class).getBooks();
    }

    @When("user wants all books with the title {string}")
    public void userWantsAllBooksWithTheTitle(String title) {
        log.info("AuthorBooks: "+title);
        books = userContext.getFeignClient(BookClient.class).getBooksByTitle("Greatest Book you'll ever read");
        log.info("TitleBooks: "+books);
    }

    @When("user wants all books from one author {string}")
    public void userWantsAllBooksFromOneAuthor(String author) {
        log.info("AuthorBooks: "+author);
        books = userContext.getFeignClient(BookClient.class).getBooksByAuthor("Newcomer Author");

    }

    @Then("a List of those books is returned {int}")
    public void aListOfThoseBooksIsReturned(int listSize) {
        assertEquals(books.size(), listSize);
    }
}
