package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.dto.GenreDTO;
import by.itacademy.jd2.votetask.service.api.IGenreService;

import java.util.List;
import java.util.stream.Collectors;

public class GenreService implements IGenreService {
    private final IGenresDao<GenreDTO> genresDao;

    public GenreService(IGenresDao<GenreDTO> genresDao) {
        this.genresDao = genresDao;
    }

    @Override
    public List<String> getContent() {
        return genresDao.readAll().stream()
                .map(GenreDTO::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public boolean exist(String name) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Genre title can't be empty");
        }

        return genresDao.exist(name);
    }
}
