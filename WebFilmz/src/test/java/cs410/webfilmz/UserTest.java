package cs410.webfilmz;

/*
 *
 * ADD YOUR User TESTS TO THIS FILE
 *
 */

import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testAddWatched() {
        User user = new User();
        Catalog catalog = new Catalog();
        Film film = catalog.add("Title", "Director", "Genre", 2023);
        user.addWatched(film);
        assertEquals(false, user.isLikedDirector("Director"));
        assertEquals(false, user.isLikedGenre("Genre"));
    }

    @Test
    public void testAddLiked() {
        User user = new User();
        Catalog catalog = new Catalog();
        Film film = catalog.add("Title", "Director", "Genre", 2023);
        user.addLiked(film);
        assertEquals(true, user.isLikedDirector("Director"));
        assertEquals(true, user.isLikedGenre("Genre"));
    }

    @Test
    public void testGetRecommendationsByDirector() {
        User user = new User();
        Catalog catalog = new Catalog();
        Film film = catalog.add("Title", "Director", "Genre", 2023);
        user.addLiked(film);

        Set<Film> recommendations = user.getRecommendationsByDirector(catalog);
        assertEquals(true, recommendations.contains(film));
    }

    @Test
    public void testGetRecommendationsByGenre() {
        User user = new User();
        Catalog catalog = new Catalog();
        Film film = catalog.add("Title", "Director", "Genre", 2023);
        user.addLiked(film);

        Set<Film> recommendations = user.getRecommendationsByGenre(catalog);
        assertEquals(true, recommendations.contains(film));
    }

    @Test
    public void testIsLikedDirector() {
        User user = new User();
        user.addLiked(new Film("Movie 1", "Director1", "Genre1", 2023));
        assertEquals(true, user.isLikedDirector("Director1"));
        assertEquals(false, user.isLikedDirector("Director2"));
    }

    @Test
    public void testIsLikedGenre() {
        User user = new User();
        user.addLiked(new Film("Movie 1", "Director1", "Genre1", 2023));
        assertEquals(true, user.isLikedGenre("Genre1"));
        assertEquals(false, user.isLikedGenre("Genre2"));
    }

    @Test
    public void testGetAllRecommendations() {
        User user = new User();
        Catalog catalog = new Catalog();
        Film film = catalog.add("Title", "Director", "Genre", 2023);
        user.addWatched(film);

        int initialGenericRecsCount = 5;
        assertEquals(5, user.getAllRecommendations(catalog, initialGenericRecsCount).size());
    }

}
