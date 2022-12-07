package by.itacademy.jd2.votetask.service.factories;

import by.itacademy.jd2.votetask.dao.factories.VoteDaoSingleton;
import by.itacademy.jd2.votetask.service.VoteService;

public class VoteServiceSingleton {
    private volatile static VoteService instance;

    public static VoteService getInstance() {
        if(instance == null){
            synchronized (VoteServiceSingleton.class){
                if(instance == null){
                    instance = new VoteService(VoteDaoSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}

