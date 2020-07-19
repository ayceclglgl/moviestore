import model.Movie;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MovieStore {
    List<Movie> movies = new LinkedList<>();

    public List<Movie> findByPartialTitle(final String partialTitle) {
        return find(movie -> movie.getTitle().toUpperCase().contains(partialTitle.toUpperCase()));
    }

    public void add(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> findByDirector(String directorName) {
        return find(movie -> movie.getDirector().equals(directorName));
    }

    public List<Movie> findByReleaseYear(int from, int to) {
        return find(movie -> (movie.getReleaseYear() > from && movie.getReleaseYear() < to));
    }

    private List<Movie> find(Predicate<Movie> predicate){
        return movies.stream().filter(predicate).collect(Collectors.toList());
    }
}
