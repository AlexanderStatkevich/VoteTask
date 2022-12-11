package by.itacademy.jd2.votetask.dao.factories;

import by.itacademy.jd2.votetask.dao.VoteDao;
import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.dto.SavedVoteDTO;

public class VoteDaoSingleton {
    private volatile static VoteDao instance;

    private VoteDaoSingleton() {
    }

    public static IVoteDao<SavedVoteDTO> getInstance() {
        if(instance == null){
            synchronized (VoteDaoSingleton.class){
                if(instance == null){
                    instance = new VoteDao();
                }
            }
        }
        return instance;
    }
}
