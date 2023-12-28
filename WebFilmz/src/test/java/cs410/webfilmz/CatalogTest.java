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

    // Only use this catalog if the test does not modify it or the films it contains
    final Catalog catalog = getCatalog();

    // Makes a known small catalog for testing
    static Catalog getCatalog() {
        Catalog catalog = new Catalog();
        catalog.add("The Terminator", "James Cameron", "SciFi", 1984, Rating.R);
        catalog.add("The Princess Bride", "Rob Reiner", "Romance", 1987, Rating.PG);
        catalog.add("The City of Lost Children", "Jean-Pierre Jeunet", "SciFi", 1995, Rating.R);
        catalog.add("Toy Story", "John Lasseter", "Comedy", 1995, Rating.G);
        catalog.add("Titanic", "James Cameron", "Romance", 1997, Rating.PG13);
        catalog.add("Memento", "Christopher Nolan", "Thriller", 2000, Rating.R);
        catalog.add("Amelie", "Jean-Pierre Jeunet", "Romance", 2001, Rating.R);
        catalog.add("Inception", "Christopher Nolan", "SciFi", 2010, Rating.PG13);
        catalog.add("The Martian", "Ridley Scott", "SciFi", 2015, Rating.PG13);
        catalog.add("Oppenheimer", "Christopher Nolan", "Bio" ,2023, Rating.R);
        return catalog;
    }

    @Test
    public void testAddFilm() {
        Catalog catalog = new Catalog();
        Film film = catalog.add("Title", "Director", "Genre", 2023, Rating.G);
        assertEquals("Title", film.title());
        assertEquals("Director", film.director());
        assertEquals("Genre", film.genre());
        assertEquals(2023, film.releaseYear());
    }

    @Test
    public void testFindByTitle() {
        Catalog catalog = new Catalog();
        Film film = catalog.add("Title", "Director", "Genre", 2023, Rating.R);
        Film foundFilm = catalog.findByTitle("Title");
        assertEquals(film, foundFilm);
    }

    @Test
    public void testGetRecommendationsByYear() {
        Catalog catalog = new Catalog();
        catalog.add("Movie 1", "Director", "Genre1", 2021, Rating.G);
        catalog.add("Movie 2", "Director", "Genre2", 2022, Rating.R);
        catalog.add("Movie 3", "Director", "Genre3", 2023, Rating.PG13);

        Set<Film> recommendations = catalog.getRecommendationsByYear(2);
        assertEquals(2, recommendations.size());
    }

    @Test
    public void testGetRecommendationsMostWatched() {
        Catalog catalog = new Catalog();
        catalog.add("Movie 1", "Director1", "Genre", 2023, Rating.PG);
        catalog.add("Movie 2", "Director2", "Genre", 2023, Rating.PG13);
        catalog.add("Movie 3", "Director3", "Genre", 2023, Rating.R);

        Film film1 = catalog.findByTitle("Movie 1");
        film1.incrementWatched(10);

        Set<Film> recommendations = catalog.getRecommendationsMostWatched(2);
        assertEquals(2, recommendations.size());
    }

    @Test
    public void testGetRecommendationsMostLiked() {
        Catalog catalog = new Catalog();
        catalog.add("Movie 1", "Director1", "Genre", 2023, Rating.G);
        catalog.add("Movie 2", "Director2", "Genre", 2023, Rating.PG13);
        catalog.add("Movie 3", "Director3", "Genre", 2023, Rating.PG);

        Film film1 = catalog.findByTitle("Movie 1");
        film1.incrementLiked(10);

        Set<Film> recommendations = catalog.getRecommendationsMostLiked(2);
        assertEquals(2, recommendations.size());
    }

    @Test
    public void testGetRecommendationsByDirector() {
        Catalog catalog = new Catalog();
        catalog.add("Movie 1", "Director1", "Genre", 2023, Rating.PG13);
        catalog.add("Movie 2", "Director2", "Genre", 2023, Rating.PG13);
        catalog.add("Movie 3", "Director3", "Genre", 2023, Rating.G);

        User user = new User();
        user.addLiked(catalog.findByTitle("Movie 1"));

        Set<Film> recommendations = catalog.getRecommendationsByDirector(user);
        assertEquals(1, recommendations.size());
    }

    @Test
    public void testGetRecommendationsByGenre() {
        Catalog catalog = new Catalog();
        catalog.add("Movie 1", "Director1", "Genre1", 2023, Rating.G);
        catalog.add("Movie 2", "Director2", "Genre2", 2023, Rating.R);
        catalog.add("Movie 3", "Director3", "Genre3", 2023, Rating.R);

        User user = new User();
        user.addLiked(catalog.findByTitle("Movie 1"));

        Set<Film> recommendations = catalog.getRecommendationsByGenre(user);
        assertEquals(1, recommendations.size());
    }
    @Test
    void findFilmByTitle() {
        assertEquals("Memento", catalog.findByTitle("Memento").title());
        assertThrows(RuntimeException.class,
                () -> catalog.findByTitle("The Great Mouse Detective"));
    }

    @Test
    void getRecommendationsByYear() {
        assertEquals(
                Set.of(catalog.findByTitle("Oppenheimer"), catalog.findByTitle("The Martian")),
                catalog.getRecommendationsByYear(2));
    }

    @Test
    void getRecommendationsMostLiked() {
        Catalog catalog = getCatalog();
        Film amelie = catalog.findByTitle("Amelie");
        Film memento = catalog.findByTitle("Memento");
        amelie.incrementLiked(10);
        memento.incrementLiked(4);
        assertEquals(Set.of(amelie, memento),
                catalog.getRecommendationsMostLiked(2));
        assertEquals(Set.of(amelie),
                catalog.getRecommendationsMostLiked(1));
    }

    // Represents preference for a single director, no genres
    private class JustLikesOneDirector implements ILikeFilm {
        private String likedDirector;
        JustLikesOneDirector(String likedDirector) { this.likedDirector = likedDirector; }

        // Do any of the films the user liked have the given director/genre?
        public boolean isLikedDirector(String director) {
            return this.likedDirector.equals(director);
        }
        public boolean isLikedGenre(String genre) {
            return false;
        }
    }

    @Test
    void getPseudoPersonalRecommendationsByDirector() {
        String likedDirector = "James Cameron";
        assertEquals(
                Set.of(catalog.findByTitle("The Terminator"), catalog.findByTitle("Titanic")),
                catalog.getRecommendationsByDirector(
                        new CatalogTest.JustLikesOneDirector(likedDirector))
        );
    }

    @Test
    void getPseudoPersonalRecommendationsByDirector2() {
        String likedDirector = "James Cameron";
        assertEquals(
                Set.of(catalog.findByTitle("The Terminator"), catalog.findByTitle("Titanic")),
                catalog.getRecommendationsByDirector(
                        // This is called an "anonymous class expression"
                        new ILikeFilm() {
                            @Override
                            public boolean isLikedDirector(String director) {
                                return likedDirector.equals(director);
                            }

                            @Override
                            public boolean isLikedGenre(String genre) {
                                return false;
                            }
                        }
                ));
    }
}
