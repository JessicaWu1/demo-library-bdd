package net.greenbone.demolibrary.bdd.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import net.greenbone.demolibrary.bdd.helper.adapter.context.UserContext;
import net.greenbone.demolibrary.bdd.helper.adapter.http.client.BookClient;
import net.greenbone.demolibrary.representations.response.BookResponse;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RequiredArgsConstructor
public class GetAllBooksSteps {

    private final UserContext userContext;
    private List<BookResponse> books;

    @When("user searches for all books from a specific author")
    public void userSearchesForAllBooksFromASpecificAuthor() {
        books = userContext.getFeignClient(BookClient.class).getBooks();
    }

    @Then("a List of those books is returned")
    public void aListOfThoseBooksIsReturned() {
        assertEquals(books.size(), 1);
    }
}
