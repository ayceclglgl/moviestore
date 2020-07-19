import model.Movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

class MovieStoreTest {

    private final Movie harryPotter = new Movie("Harry Potter", "Rowling", 2000);
    private final Movie starWars = new Movie("Star Wars", "Schwimmer", 1999);
    private final Movie starTrek = new Movie("STAR Trek", "Schwimmer", 2002);
    private final Movie takeThat = new Movie("Take That", "Schwimmer", 2010);
    private final MovieStore movieStore = new MovieStore();

    @BeforeEach
    void setUp() {
        movieStore.add(harryPotter);
        movieStore.add(starWars);
        movieStore.add(starTrek);
        movieStore.add(takeThat);
    }

    @Test
    public void returnsNoResultsWhenNoTitlesPartiallyMatchSearch(){
        MovieStore movieStore = new MovieStore();

        List<Movie> result = movieStore.findByPartialTitle("abc");

        assertThat(result.size(), is(0));
    }

    @Test
    public void findsMoviesWhenTitlesArePartiallyMatched(){
        List<Movie> result = movieStore.findByPartialTitle("tar");

        assertThat(result.size(), is(2));
        assertThat(result, containsInAnyOrder(starTrek, starWars));
    }

    @Test
    public void returnsNoResultWhenFindMoviesByDirector(){
        List<Movie> result = movieStore.findByDirector("director name");

        assertThat(result.size(), is(0));
    }

    @Test
    public void findsMoviesWhenDirectorExactlyMatches(){
        List<Movie> result = movieStore.findByDirector("Schwimmer");

        assertThat(result.size(), is(3));
        assertThat(result, containsInAnyOrder(starTrek, starWars, takeThat));
    }

    @Test
    public void findsMoviesWhenReleaseYearIsBetweenTwoValues(){
        List<Movie> result = movieStore.findByReleaseYear(1999, 2003);

        assertThat(result.size(), is(2));
        assertThat(result, containsInAnyOrder(harryPotter, starTrek));
    }

}