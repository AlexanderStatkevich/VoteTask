package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.dto.GenreDTO;
import by.itacademy.jd2.votetask.dto.PerformerDTO;
import by.itacademy.jd2.votetask.dto.SavedVoteDTO;
import by.itacademy.jd2.votetask.dto.VoteDto;
import by.itacademy.jd2.votetask.exceptions.InvalidVoteException;
import by.itacademy.jd2.votetask.service.api.IGenreService;
import by.itacademy.jd2.votetask.service.api.IPerformerService;
import by.itacademy.jd2.votetask.service.api.IVoteService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VoteService implements IVoteService {

    private final IVoteDao<SavedVoteDTO> voteDao;
    private final IPerformerService performerService;
    private final IGenreService genreService;
    private final Lock lock = new ReentrantLock();

    public VoteService(IVoteDao<SavedVoteDTO> voteDao, IPerformerService performerService, IGenreService genreService) {
        this.voteDao = voteDao;
        this.performerService = performerService;
        this.genreService = genreService;
    }

    public void addVote(VoteDto voteDto) {
        validate(voteDto);
        SavedVoteDTO savedVoteDTO = new SavedVoteDTO(voteDto);
        try {
            boolean isLockAcquired = lock.tryLock(1, TimeUnit.SECONDS);
            if (isLockAcquired) {
                voteDao.create(savedVoteDTO);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void validate(VoteDto voteDto){

        int votesForGenreListSize = voteDto.getVoicesForGenres().size();
        String voiceForPerformer = voteDto.getVoiceForPerformer();
        List<String> voicesForGenres = voteDto.getVoicesForGenres();
        String info = voteDto.getInfo();

        if (voiceForPerformer.isEmpty()) {
            errors.add(PERFORMER_IS_EMPTY);
        }
        if (voicesForGenres.isEmpty()) {
            errors.add(GENRE_IS_EMPTY);
        }
        if (info.isEmpty()) {
            errors.add(INFO_IS_EMPTY);
        }
        if (votesForGenreListSize < MIN_GENRE_CHOICES || votesForGenreListSize > MAX_GENRE_CHOICES) {
            errors.add(WRONG_NUMBER_OF_GENRES);
        }
        boolean validPerformer = performerDTOList.stream()
                .map(PerformerDTO::getNickName)
                .anyMatch(nick -> nick.equals(voiceForPerformer));
        if (!validPerformer) {
            errors.add(PERFORMER_DO_NOT_EXIST);
        }
        for (String voiceForGenre : voicesForGenres) {
            boolean validGenre = genresList.stream()
                    .map(GenreDTO::getTitle)
                    .anyMatch(nick -> nick.equals(voiceForGenre));
            if (!validGenre) {
                errors.add(GENRE_DO_NOT_EXIST);
            }
        }
        if (!errors.isEmpty()) {
            throw new InvalidVoteException(errors);
        }



        String artist = vote.getArtist();

        if(artist == null || artist.isBlank()){
            throw new IllegalArgumentException("Артист не передан");
        }

        if(!this.performerService.exist(artist)){
            throw new IllegalArgumentException("Артиста " + artist + " не существует");
        }

        String[] genres = vote.getGenres();

        if(genres == null){
            throw new IllegalArgumentException("Жанры не переданы");
        }

        if(genres.length < 3 || genres.length > 5){
            throw new IllegalArgumentException("Проблема с количеством жанров, их должно быть от 3 до 5");
        }

        for (String genre : genres) {
            if(genre == null || genre.isBlank()){
                throw new IllegalArgumentException("Жанр не передан");
            }

            if(!this.genreService.exist(genre)){
                throw new IllegalArgumentException("Жанра " + genre + " не существует");
            }
        }

        Set<String> names = new HashSet<>();
        names.addAll(Arrays.asList(genres));

        if(genres.length != names.size()){
            throw new IllegalArgumentException("Жанры содержат дубли");
        }

        String about = vote.getAbout();

        if(about == null || about.isBlank()){
            throw new IllegalArgumentException("О себе не передан");
        }
    }

}


