package by.itacademy.jd2.votetask.service.api;

import by.itacademy.jd2.votetask.dto.GenreDTO;

import java.util.List;

public interface IGenreService {

    List<GenreDTO> getContent();
    boolean exist(Long id);
}
