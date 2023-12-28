package cs410.webfilmz;

/*
 *
 * ADD YOUR User TESTS TO THIS FILE
 *
 */

import java.lang.reflect.Field;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testAddWatched() {
        User user = new User();
        Catalog catalog = new Catalog();
        Film film = catalog.add("Title", "Director", "Genre", 2023, Rating.R);
        user.addWatched(film);
        assertEquals(false, user.isLikedDirector("Director"));
        assertEquals(false, user.isLikedGenre("Genre"));
    }

    @Test
    public void testAddLiked() {
        User user = new User();
        Catalog catalog = new Catalog();
        Film film = catalog.add("Title", "Director", "Genre", 2023, Rating.R);
        user.addLiked(film);
        assertEquals(true, user.isLikedDirector("Director"));
        assertEquals(true, user.isLikedGenre("Genre"));
    }

    @Test
    public void testGetRecommendationsByDirector() {
        User user = new User();
        Catalog catalog = new Catalog();
        Film film = catalog.add("Title", "Director", "Genre", 2023, Rating.R);
        user.addLiked(film);

        Set<Film> recommendations = user.getRecommendationsByDirector(catalog);
        assertEquals(true, recommendations.contains(film));
    }

    @Test
    public void testGetRecommendationsByGenre() {
        User user = new User();
        Catalog catalog = new Catalog();
        Film film = catalog.add("Title", "Director", "Genre", 2023, Rating.R);
        user.addLiked(film);

        Set<Film> recommendations = user.getRecommendationsByGenre(catalog);
        assertEquals(true, recommendations.contains(film));
    }

    @Test
    public void testIsLikedDirector() {
        User user = new User();
        user.addLiked(new Film("Movie 1", "Director1", "Genre1", 2023, Rating.R));
        assertEquals(true, user.isLikedDirector("Director1"));
        assertEquals(false, user.isLikedDirector("Director2"));
    }

    @Test
    public void testIsLikedGenre() {
        User user = new User();
        user.addLiked(new Film("Movie 1", "Director1", "Genre1", 2023, Rating.R));
        assertEquals(true, user.isLikedGenre("Genre1"));
        assertEquals(false, user.isLikedGenre("Genre2"));
    }

    @Test
    public void testGetAllRecommendations() {
        User user = new User();
        Catalog catalog = new Catalog();
        Film film = catalog.add("Title", "Director", "Genre", 2023, Rating.PG13);
        user.addWatched(film);

        int initialGenericRecsCount = 5;
        assertEquals(5, user.getAllRecommendations(catalog, initialGenericRecsCount).size());
    }

    @Test
    void testRatings() {
        Catalog catalog = BaseCatalogTest.getCatalog();
        User bobby = new User(Rating.G);
        assertEquals(Set.of(catalog.findByTitle("Toy Story")),
                bobby.getAllRecommendations(catalog, 10).get("New Releases"));
    }

    @Test
    void testAddWatchedIdempotent() {
        Catalog catalog = BaseCatalogTest.getCatalog();
        User alice = new User();
        Film amelie = catalog.findByTitle("Amelie");
        assertEquals(0, amelie.totalWatched());
        alice.addWatched(amelie);
        alice.addWatched(amelie);
        assertEquals(1, amelie.totalWatched());
    }

    @Test
    void getRecommendationsNewReleases() {
        Catalog catalog = BaseCatalogTest.getCatalog();
        User alice = new User();
        alice.addWatched(catalog.findByTitle("The Martian"));
        assertEquals(Set.of(catalog.findByTitle("Oppenheimer"), catalog.findByTitle("Inception")),
                alice.getAllRecommendations(catalog, 3).get("New Releases"));
    }

    @Test
    void addWatchedAndLiked() {
        Catalog catalog = BaseCatalogTest.getCatalog();
        User alice = new User();
        Film amelie = catalog.findByTitle("Amelie");
        Film terminator = catalog.findByTitle("The Terminator");
        assertEquals(0, amelie.totalWatched());
        assertEquals(0, amelie.totalLiked());
        assertEquals(0, terminator.totalWatched());
        assertEquals(0, terminator.totalLiked());
        alice.addWatched(amelie);
        assertEquals(1, amelie.totalWatched());
        assertEquals(0, amelie.totalLiked());
        alice.addLiked(amelie);
        assertEquals(1, amelie.totalWatched());
        assertEquals(1, amelie.totalLiked());
        assertEquals(0, terminator.totalWatched());
        assertEquals(0, terminator.totalLiked());
    }

/*    @Test
    void testLimitRating() {
        Catalog catalog = BaseCatalogTest.getCatalog();
        User alice = new User(Rating.PG);
        Film amelie = catalog.findByTitle("Amelie");
        Film terminator = catalog.findByTitle("The Terminator");

        // Use reflection to access the private limitRating field.
        Field limitRatingField = null;
        try {
            limitRatingField = User.class.getDeclaredField("limitRating");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        limitRatingField.setAccessible(true);

        // Get the value of limitRating from the alice object.
        try {
            Rating aliceLimitRating = (Rating) limitRatingField.get(alice);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        assertEquals(true, amelie.rating().isAppropriateFor(aliceLimitRating));
        assertEquals(false, terminator.rating().isAppropriateFor(aliceLimitRating));
    }*/
}
