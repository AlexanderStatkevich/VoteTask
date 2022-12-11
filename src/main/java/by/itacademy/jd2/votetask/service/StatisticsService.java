package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.dao.api.IGenresDao;
import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.dao.factories.GenresDaoSingleton;
import by.itacademy.jd2.votetask.dao.factories.PerformersDaoSingleton;
import by.itacademy.jd2.votetask.dao.factories.VoteDaoSingleton;
import by.itacademy.jd2.votetask.dto.GenreDTO;
import by.itacademy.jd2.votetask.dto.PerformerDTO;
import by.itacademy.jd2.votetask.dto.SavedVoteDTO;
import by.itacademy.jd2.votetask.dto.VoteDto;
import by.itacademy.jd2.votetask.dto.VoteResultDto;
import by.itacademy.jd2.votetask.service.api.IStatisticsService;
import by.itacademy.jd2.votetask.util.SortMapUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StatisticsService implements IStatisticsService {

    private final String PERFORMER_DO_NOT_EXIST = "PerformerDTO do not exist";
    private final String GENRE_DO_NOT_EXIST = "GenreDTO do not exist";
    private final String PERFORMER_IS_EMPTY = "PerformerDTO is empty";
    private final String GENRE_IS_EMPTY = "GenreDTO is empty";
    private final String INFO_IS_EMPTY = "Info is empty";
    private final String WRONG_NUMBER_OF_GENRES = "Wrong number of genres";
    private final int MIN_GENRE_CHOICES = 3;
    private final int MAX_GENRE_CHOICES = 5;
    private final IPerformersDao<PerformerDTO> performersDao = PerformersDaoSingleton.getInstance();
    private final IGenresDao<GenreDTO> genresDao = GenresDaoSingleton.getInstance();
    private final List<PerformerDTO> performerDTOList = performersDao.readAll();

    private final List<GenreDTO> genresList = genresDao.readAll();
    private final List<String> errors = new ArrayList<>();

    private final IVoteDao<SavedVoteDTO> voteDao = VoteDaoSingleton.getInstance();

    public VoteResultDto getVoteResult() {
        List<SavedVoteDTO> voteDtos = voteDao.readAll();
        Map<String, Long> sortedPerformerVotes = getSortedPerformerVotes(voteDtos);
        Map<String, Long> sortedGenreVotes = getSortedGenreVotes(voteDtos);
        Map<LocalDateTime, String> sortedVoteInfos = getSortedVoteInfos(voteDtos);
        return new VoteResultDto(sortedPerformerVotes, sortedGenreVotes, sortedVoteInfos);
    }


    private Map<String, Long> getSortedPerformerVotes(List<SavedVoteDTO> voteDtos) {
        Map<String, Long> votesForPerformers = voteDtos.stream()
                .collect(Collectors.groupingBy(VoteDto::getVoiceForPerformer, Collectors.counting()));

        return SortMapUtil.sortByValue(votesForPerformers);
    }

    private Map<String, Long> getSortedGenreVotes(List<SavedVoteDTO> voteDtos) {
        Map<String, Long> votesForGenres = voteDtos.stream()
                .map(VoteDto::getVoicesForGenres)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return SortMapUtil.sortByValue(votesForGenres);
    }

    private Map<LocalDateTime, String> getSortedVoteInfos(List<SavedVoteDTO> voteDtos) {
        return voteDtos.stream()
                .collect(Collectors.toMap(VoteDto::getTime, VoteDto::getAbout, (a, b) -> a, TreeMap::new));
    }

}
