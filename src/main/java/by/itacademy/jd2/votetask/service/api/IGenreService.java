package by.itacademy.jd2.votetask.service.api;

import java.util.List;

public interface IGenreService {

    List<String> getContent();
    boolean exist(String name);
}
