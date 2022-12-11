package by.itacademy.jd2.votetask.dao.factories;

import by.itacademy.jd2.votetask.dao.PerformersDao;
import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.dto.PerformerDTO;

public class PerformersDaoSingleton {
    private volatile static PerformersDao instance;

    private PerformersDaoSingleton() {
    }

    public static IPerformersDao<PerformerDTO> getInstance() {
        if(instance == null){
            synchronized (PerformersDaoSingleton.class){
                if(instance == null){
                    instance = new PerformersDao();
                }
            }
        }
        return instance;
    }
}
