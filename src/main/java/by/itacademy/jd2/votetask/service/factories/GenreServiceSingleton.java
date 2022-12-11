package by.itacademy.jd2.votetask.service.factories;

import by.itacademy.jd2.votetask.dao.factories.GenresDaoSingleton;
import by.itacademy.jd2.votetask.service.GenreService;

public class GenreServiceSingleton {
    private volatile static GenreService instance;

    private GenreServiceSingleton() {
    }

    public static GenreService getInstance() {
        if(instance == null){
            synchronized (GenreServiceSingleton.class){
                if(instance == null){
                    instance = new GenreService(GenresDaoSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
