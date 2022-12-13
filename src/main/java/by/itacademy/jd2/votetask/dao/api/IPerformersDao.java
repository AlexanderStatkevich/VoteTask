package by.itacademy.jd2.votetask.dao.api;

public interface IPerformersDao<T> extends Dao<T> {
    boolean exist(Long id);
}
