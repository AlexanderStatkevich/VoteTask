package by.itacademy.jd2.votetask.data;

import by.itacademy.jd2.votetask.domain.Genre;

import java.util.List;

public class GenresList {
    private static final List<Genre> GENRES = List.of(new Genre("Genre 1"), new Genre("Genre 2"),
            new Genre("Genre 3"), new Genre("Genre 4"), new Genre("Genre 5"), new Genre("Genre 6"),
            new Genre("Genre 7"), new Genre("Genre 8"), new Genre("Genre 9"), new Genre("Genre 10"));

    public static List<Genre> getGENRES() {
        return GENRES;
    }
}
