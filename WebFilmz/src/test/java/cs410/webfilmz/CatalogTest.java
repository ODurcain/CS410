package cs410.webfilmz;

/*
 *
 * ADD YOUR Catalog TESTS TO THIS FILE
 *
 */

import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CatalogTest {

    @Test
    public void testAddFilm() {
        Catalog catalog = new Catalog();
        Film film = catalog.add("Title", "Director", "Genre", 2023);
        assertEquals("Title", film.title());
        assertEquals("Director", film.director());
        assertEquals("Genre", film.genre());
        assertEquals(2023, film.releaseYear());
    }

    @Test
    public void testFindByTitle() {
        Catalog catalog = new Catalog();
        Film film = catalog.add("Title", "Director", "Genre", 2023);
        Film foundFilm = catalog.findByTitle("Title");
        assertEquals(film, foundFilm);
    }

    @Test
    public void testGetRecommendationsByYear() {
        Catalog catalog = new Catalog();
        catalog.add("Movie 1", "Director", "Genre1", 2021);
        catalog.add("Movie 2", "Director", "Genre2", 2022);
        catalog.add("Movie 3", "Director", "Genre3", 2023);

        Set<Film> recommendations = catalog.getRecommendationsByYear(2);
        assertEquals(2, recommendations.size());
    }

    @Test
    public void testGetRecommendationsMostWatched() {
        Catalog catalog = new Catalog();
        catalog.add("Movie 1", "Director1", "Genre", 2023);
        catalog.add("Movie 2", "Director2", "Genre", 2023);
        catalog.add("Movie 3", "Director3", "Genre", 2023);

        Film film1 = catalog.findByTitle("Movie 1");
        film1.incrementWatched(10);

        Set<Film> recommendations = catalog.getRecommendationsMostWatched(2);
        assertEquals(2, recommendations.size());
    }

    @Test
    public void testGetRecommendationsMostLiked() {
        Catalog catalog = new Catalog();
        catalog.add("Movie 1", "Director1", "Genre", 2023);
        catalog.add("Movie 2", "Director2", "Genre", 2023);
        catalog.add("Movie 3", "Director3", "Genre", 2023);

        Film film1 = catalog.findByTitle("Movie 1");
        film1.incrementLiked(10);

        Set<Film> recommendations = catalog.getRecommendationsMostLiked(2);
        assertEquals(2, recommendations.size());
    }

    @Test
    public void testGetRecommendationsByDirector() {
        Catalog catalog = new Catalog();
        catalog.add("Movie 1", "Director1", "Genre", 2023);
        catalog.add("Movie 2", "Director2", "Genre", 2023);
        catalog.add("Movie 3", "Director3", "Genre", 2023);

        User user = new User();
        user.addLiked(catalog.findByTitle("Movie 1"));

        Set<Film> recommendations = catalog.getRecommendationsByDirector(user);
        assertEquals(1, recommendations.size());
    }

    @Test
    public void testGetRecommendationsByGenre() {
        Catalog catalog = new Catalog();
        catalog.add("Movie 1", "Director1", "Genre1", 2023);
        catalog.add("Movie 2", "Director2", "Genre2", 2023);
        catalog.add("Movie 3", "Director3", "Genre3", 2023);

        User user = new User();
        user.addLiked(catalog.findByTitle("Movie 1"));

        Set<Film> recommendations = catalog.getRecommendationsByGenre(user);
        assertEquals(1, recommendations.size());
    }
}
