package by.itacademy.jd2.votetask.dto;

import java.util.Objects;

public class GenreDTO {

    private final Long id;
    private final String title;

    public GenreDTO(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreDTO genreDTO = (GenreDTO) o;
        return Objects.equals(title, genreDTO.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "GenreDTO{" +
                "title='" + title + '\'' +
                '}';
    }
}
