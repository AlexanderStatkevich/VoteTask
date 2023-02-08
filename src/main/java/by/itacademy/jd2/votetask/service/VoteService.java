package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.domain.Genre;
import by.itacademy.jd2.votetask.domain.Performer;
import by.itacademy.jd2.votetask.domain.SavedVote;
import by.itacademy.jd2.votetask.dto.VoteDto;
import by.itacademy.jd2.votetask.exceptions.InvalidVoteException;
import by.itacademy.jd2.votetask.service.api.IGenreService;
import by.itacademy.jd2.votetask.service.api.IPerformerService;
import by.itacademy.jd2.votetask.service.api.IVoteService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class VoteService implements IVoteService {
    public static final String GENRES_DUPLICATED = "Genres duplicated";
    public static final String PERFORMER_DO_NOT_EXIST = "Performer doesn't exist";
    public static final String GENRE_DO_NOT_EXIST = "Genre doesn't exist";
    public static final String GENRE_LIST_IS_EMPTY = "Genre list is empty";
    private final String PERFORMER_IS_EMPTY = "PerformerDTO is empty";
    private final String GENRE_IS_EMPTY = "GenreDTO is empty";
    private final String INFO_IS_EMPTY = "Info is empty";
    private final String WRONG_NUMBER_OF_GENRES = "Wrong number of genres";
    private final int MIN_GENRE_CHOICES = 3;
    private final int MAX_GENRE_CHOICES = 5;

    private final IVoteDao voteDao;
    private final IPerformerService performerService;
    private final IGenreService genreService;
    private final Lock lock = new ReentrantLock();


    public VoteService(IVoteDao voteDao, IPerformerService performerService, IGenreService genreService) {
        this.voteDao = voteDao;
        this.performerService = performerService;
        this.genreService = genreService;
    }

    public void addVote(VoteDto voteDto) {
        validate(voteDto);
        SavedVote savedVote = mapDtoToEntity(voteDto);
        try {
            boolean isLockAcquired = lock.tryLock(1, TimeUnit.SECONDS);
            if (isLockAcquired) {
                voteDao.create(savedVote);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private SavedVote mapDtoToEntity(VoteDto voteDto) {
        List<Long> voicesForGenres = voteDto.getVoicesForGenres();
        List<Genre> voicesForGenreEntities = voicesForGenres.stream()
                .map(Genre::new)
                .collect(Collectors.toList());

        Long voiceForPerformer = voteDto.getVoiceForPerformer();

        String about = voteDto.getAbout();
        String email = voteDto.getEmail();
        return new SavedVote(null,new Performer(voiceForPerformer),voicesForGenreEntities,about,email);
    }

    private void validate(VoteDto voteDto) {
        List<String> errors = new ArrayList<>();
        int votesForGenreListSize = voteDto.getVoicesForGenres().size();
        Long voiceForPerformer = voteDto.getVoiceForPerformer();
        List<Long> voicesForGenres = voteDto.getVoicesForGenres();
        String info = voteDto.getAbout();

        if (voiceForPerformer == null) {
            errors.add(PERFORMER_IS_EMPTY);
        }
        if (voicesForGenres.isEmpty()) {
            errors.add(GENRE_LIST_IS_EMPTY);
        }
        if (info.isEmpty()) {
            errors.add(INFO_IS_EMPTY);
        }
        if (votesForGenreListSize < MIN_GENRE_CHOICES || votesForGenreListSize > MAX_GENRE_CHOICES) {
            errors.add(WRONG_NUMBER_OF_GENRES);
        }

        Set<Long> voicesForGenresSet = new HashSet<>(voicesForGenres);

        if (voicesForGenres.size() != voicesForGenresSet.size()) {
            errors.add(GENRES_DUPLICATED);
        }

        if (!this.performerService.exist(voiceForPerformer)) {
            errors.add(PERFORMER_DO_NOT_EXIST);
        }

        for (Long genre : voicesForGenres) {
            if (genre == null) {
                errors.add(GENRE_IS_EMPTY);
            }
            if (!this.genreService.exist(genre)) {
                errors.add(GENRE_DO_NOT_EXIST);
            }
        }
        if (!errors.isEmpty()) {
            throw new InvalidVoteException(errors);
        }
    }

    @Override
    public List<SavedVote> getVotes() {
        return voteDao.readAll();
    }

}


