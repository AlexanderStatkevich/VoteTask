package by.itacademy.jd2.votetask.dao.api;

public interface IVoteDao <T> extends Dao<T>{

    boolean checkVotesForGenre(Long id);

    boolean checkVotesForPerformer(Long id);
}
