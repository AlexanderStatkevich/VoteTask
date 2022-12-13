package by.itacademy.jd2.votetask.service.factories;

import by.itacademy.jd2.votetask.service.StatisticsService;

public class StatisticsServiceSingleton {
    private volatile static StatisticsService instance;

    private StatisticsServiceSingleton() {
    }

    public static StatisticsService getInstance() {
        if(instance == null){
            synchronized (StatisticsServiceSingleton.class){
                if(instance == null){
                    instance = new StatisticsService();
                }
            }
        }
        return instance;
    }
}
