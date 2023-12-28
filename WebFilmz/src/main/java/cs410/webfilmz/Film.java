package cs410.webfilmz;

/* Represents a film, including both its immutable
 * intrinsic information and its mutable viewing history.
 * Used by User and Catalog, updated by User; created by Catalog.
 */
public class Film {
    private final String title;
    private final String director;
    private final String genre;
    private final int releaseYear;
    private final Rating rating;

    private int totalWatched = 0;
    private int totalLiked = 0;

    Film(String title, String director, String genre,
                 int releaseYear, Rating rating) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    // Get field values
    public String title() { return title; }
    public String director() { return director; }
    public String genre() { return genre; }
    public int releaseYear() { return releaseYear; }
    public int totalWatched() { return totalWatched; }
    public int totalLiked() { return totalLiked; }
    public Rating rating() { return rating; }

    // Update watched/liked counters, respectively
    public void incrementWatched(int toAdd) {
        totalWatched = totalWatched + toAdd;
    }
    public void incrementLiked(int toAdd) {
        totalLiked = totalLiked + toAdd;
    }

    // Remove all films that are not accepted rating
    public boolean isAppropriateFor(Rating limit){
        return rating.isAppropriateFor(limit);
    }
}
