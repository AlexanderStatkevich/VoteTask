package by.itacademy.jd2.votetask.dao;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.dto.GenreDTO;

import java.util.List;

public class GenresDao implements IGenresDao<GenreDTO> {
    private static List<GenreDTO> GENRES = List.of(
            new GenreDTO("GenreDTO 1"), new GenreDTO("GenreDTO 2"),
            new GenreDTO("GenreDTO 3"), new GenreDTO("GenreDTO 4"),
            new GenreDTO("GenreDTO 5"), new GenreDTO("GenreDTO 6"),
            new GenreDTO("GenreDTO 7"), new GenreDTO("GenreDTO 8"),
            new GenreDTO("GenreDTO 9"), new GenreDTO("GenreDTO 10"));

    @Override
    public void create(GenreDTO genreDTO) {
        GENRES.add(genreDTO);
    }

    @Override
    public List<GenreDTO> readAll() {
        return GENRES;
    }

    @Override
    public void delete(GenreDTO genreDTO) {
        GENRES.remove(genreDTO);
    }

    @Override
    public boolean exist(String name) {
        for (GenreDTO genreDTO : GENRES) {
            if(name.equals(genreDTO.getTitle())){
                return true;
            }
        }
        return false;
    }
}
