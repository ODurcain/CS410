package cs410.webfilmz;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* Represents a user (subscriber to the service), including
 * their watch/like history.
 * Responsible for maintaining watch/like history, expressing
 * film preferences, and getting personalized recommendations.
 * Refers to and updates Film.
 * Relies on Catalog to implement recommendations.
 */
public class User implements ILikeFilm {
    /* Sets of films watched and liked, respectively.
     * Uses Set to avoid recording watch/like multiple times.
     */
    private final Set<Film> watched;
    private final Set<Film> liked;
    private final Set<String> likedDirectors;
    private final Set<String> likedGenres;
    public User() {
        watched = new HashSet<>();
        liked = new HashSet<>();
        likedDirectors = new HashSet<>();
        likedGenres = new HashSet<>();
    }

    // Record that the user watched/liked the given film,
    // updating film if not already in Set.
    public void addWatched(Film film) {
        if (watched.add(film)) {
            film.incrementWatched(1);
        }
    }
    public void addLiked(Film film) {
        if (liked.add(film)) {
            film.incrementLiked(1);
            likedDirectors.add(film.director());
            likedGenres.add(film.genre());
        }
    }

    public Map<String, Set<Film>> getAllRecommendations(Catalog catalog, int initialGenericRecsCount) {
        Map<String, Set<Film>> filterRecommendations = new HashMap<>();

        Set<Film> newReleases = catalog.getRecommendationsByYear(initialGenericRecsCount);
        newReleases.removeAll(watched);
        filterRecommendations.put("New Releases", newReleases);

        Set<Film> mostWatched = catalog.getRecommendationsMostWatched(initialGenericRecsCount);
        mostWatched.removeAll(watched);
        filterRecommendations.put("Most Watched", mostWatched);

        Set<Film> mostLiked = catalog.getRecommendationsMostLiked(initialGenericRecsCount);
        mostLiked.removeAll(watched);
        filterRecommendations.put("Most Liked", mostLiked);

        Set<Film> favoriteDirectorRecs = getRecommendationsByDirector(catalog);
        favoriteDirectorRecs.removeAll(watched);
        filterRecommendations.put("Favorite Directors", favoriteDirectorRecs);

        Set<Film> favoriteGenreRecs = getRecommendationsByGenre(catalog);
        favoriteGenreRecs.removeAll(watched);
        filterRecommendations.put("Favorite Genre", favoriteGenreRecs);

        return filterRecommendations;
    }

    public Set<Film> getRecommendationsByDirector(Catalog catalog) {
        Set<Film> recommendationsByDirector = catalog.getRecommendationsByDirector(this);
        recommendationsByDirector.removeAll(watched);
        return recommendationsByDirector;
    }

    public Set<Film> getRecommendationsByGenre(Catalog catalog){
        Set<Film> recommendationsByGenre = catalog.getRecommendationsByGenre(this);
        recommendationsByGenre.removeAll(watched);
        return recommendationsByGenre;
    }

    // Do any of the films the user liked have the given
    // director/genre?
/*    public boolean isLikedDirector(String director) {
        for (Film film : liked) {
            if (film.director().equals(director)) return true;
        }
        return false;
    }
    public boolean isLikedGenre(String genre) {
        for (Film film : liked) {
            if (film.genre().equals((genre))) return true;
        }
        return false;
    }*/
/*    public Set<String> getLikedDirectors() {
        return likedDirectors;
    }

    public Set<String> getLikedGenres() {
        return likedGenres;
    }*/

    public boolean isLikedDirector(String director) {
        return likedDirectors.contains(director);
    }

    public boolean isLikedGenre(String genre) {
        return likedGenres.contains(genre);
    }
}
