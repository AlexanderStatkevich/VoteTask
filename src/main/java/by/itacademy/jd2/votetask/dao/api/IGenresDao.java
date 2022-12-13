package by.itacademy.jd2.votetask.dao.api;

public interface IGenresDao<T> extends Dao<T> {
    boolean exist(Long id);
}
