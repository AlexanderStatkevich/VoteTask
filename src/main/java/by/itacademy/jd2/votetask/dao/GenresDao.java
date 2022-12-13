package by.itacademy.jd2.votetask.dao;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.dto.GenreDTO;

import java.util.List;

public class GenresDao implements IGenresDao<GenreDTO> {
    private List<GenreDTO> GENRES = List.of(
            new GenreDTO(1L,"Genre 1"),
            new GenreDTO(2L,"Genre 2"),
            new GenreDTO(3L,"Genre 3"),
            new GenreDTO(4L,"Genre 4"),
            new GenreDTO(5L,"Genre 5"),
            new GenreDTO(6L,"Genre 6"),
            new GenreDTO(7L,"Genre 7"),
            new GenreDTO(8L,"Genre 8"),
            new GenreDTO(9L,"Genre 9"),
            new GenreDTO(10L,"Genre 10"));

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
    public boolean exist(Long id) {
        for (GenreDTO genreDTO : GENRES) {
            if(id.equals(genreDTO.getId())){
                return true;
            }
        }
        return false;
    }
}
