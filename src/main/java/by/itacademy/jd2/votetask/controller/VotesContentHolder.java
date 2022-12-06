package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.dao.GenresDao;
import by.itacademy.jd2.votetask.dao.IGenresDao;
import by.itacademy.jd2.votetask.dao.IPerformersDao;
import by.itacademy.jd2.votetask.dao.PerformersDao;
import by.itacademy.jd2.votetask.domain.About;
import by.itacademy.jd2.votetask.domain.Genre;
import by.itacademy.jd2.votetask.domain.Performer;
import by.itacademy.jd2.votetask.domain.Vote;
import by.itacademy.jd2.votetask.util.SortMapUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VotesContentHolder {

    private static VotesContentHolder INSTANCE;

    private VotesContentHolder() {
        initPerformerVotes();
        initGenreVotes();
    }

    private final Map<String, Integer> votesForPerformers = new HashMap<>();

    private final Map<String, Integer> votesForGenres = new HashMap<>();

    private final List<About> voteTexts = new ArrayList<>();

    private final IPerformersDao<Performer> performersDao = new PerformersDao();

    private final IGenresDao<Genre> genresDao = new GenresDao();

    public static VotesContentHolder getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new VotesContentHolder();
        }
        return INSTANCE;
    }

    public void performerVoteIncrement(Vote vote) {
        String performer = vote.getVoiceForPerformer();
        Integer currentVotes = votesForPerformers.get(performer);
        if (currentVotes != null) {
            votesForPerformers.put(performer, currentVotes + 1);
        }
    }

    public void genreVoteIncrement(Vote vote) {
        List<String> genres = vote.getVoicesForGenres();
        for (String genre : genres) {
            Integer currentVotes = votesForGenres.get(genre);
            if (currentVotes != null) {
                votesForGenres.put(genre, currentVotes + 1);
            }
        }
    }

    public void addVoteInfo(Vote vote) {
        String info = vote.getInfo();
        if (info != null) {
            LocalDateTime localDateTime = LocalDateTime.now();
            About about = new About(info, localDateTime);
            voteTexts.add(about);
        }
    }

    private void initPerformerVotes() {
        List<Performer> performers = performersDao.readAll();
        for (Performer performer : performers) {
            votesForPerformers.put(performer.getNickName(), 0);
        }
    }

    private void initGenreVotes() {
        List<Genre> genres = genresDao.readAll();
        for (Genre genre : genres) {
            votesForGenres.put(genre.getTitle(), 0);
        }
    }

    public Map<String, Integer> getSortedPerformerVotes() {
        return SortMapUtil.sortMap(votesForPerformers);
    }

    public Map<String, Integer> getSortedGenreVotes() {
        return SortMapUtil.sortMap(votesForGenres);
    }

    public List<About> getSortedVoteInfos() {
        Collections.sort(voteTexts);
        return voteTexts;
    }


}
