package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.dto.GenreDTO;
import by.itacademy.jd2.votetask.service.api.IGenreService;

import java.util.List;

public class GenreService implements IGenreService {
    private final IGenresDao<GenreDTO> genresDao;

    public GenreService(IGenresDao<GenreDTO> genresDao) {
        this.genresDao = genresDao;
    }

    @Override
    public List<GenreDTO>  getContent() {
        return genresDao.readAll();
    }

    @Override
    public boolean exist(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Genre title can't be empty");
        }
        return genresDao.exist(id);
    }
}
