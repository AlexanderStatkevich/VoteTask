package by.itacademy.jd2.votetask.service.vote;

import by.itacademy.jd2.votetask.domain.Vote;
import by.itacademy.jd2.votetask.exceptions.InvalidVoteException;

import java.util.ArrayList;
import java.util.List;

public class VoteValidator {
    public static final String PERFORMER_IS_EMPTY = "Performer is empty";
    public static final String GENRE_IS_EMPTY = "Genre is empty";
    public static final String INFO_IS_EMPTY = "Info is empty";
    public static final String WRONG_NUMBER_OF_GENRES = "Wrong number of genres";

    public void validate(Vote vote) {
        List<String > errors = new ArrayList<>();
        if (vote.getVoiceForPerformer().isEmpty()) {
            errors.add(PERFORMER_IS_EMPTY);
        }
        if (vote.getVoicesForGenres().isEmpty()) {
            errors.add(GENRE_IS_EMPTY);
        }
        if (vote.getInfo().isEmpty()) {
            errors.add(INFO_IS_EMPTY);
        }
        if (vote.getVoicesForGenres().size() < 3
                || vote.getVoicesForGenres().size() > 5) {
            errors.add(WRONG_NUMBER_OF_GENRES);
        }
        if(!errors.isEmpty()){
            throw new InvalidVoteException(errors);
        }
    }
}
