package by.itacademy.jd2.votetask;

import by.itacademy.jd2.votetask.domain.Genre;
import by.itacademy.jd2.votetask.domain.Performer;
import by.itacademy.jd2.votetask.domain.SavedVote;
import by.itacademy.jd2.votetask.dto.GenreDto;
import by.itacademy.jd2.votetask.dto.PerformerDto;
import by.itacademy.jd2.votetask.dto.VoteDto;
import by.itacademy.jd2.votetask.dto.VoteResultDto;
import by.itacademy.jd2.votetask.service.GenreService;
import by.itacademy.jd2.votetask.service.PerformerService;
import by.itacademy.jd2.votetask.service.StatisticsService;
import by.itacademy.jd2.votetask.service.VoteService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String HEADER_PERFORMERS_RESULT = "<p>Total score among Performers:</p>";
    private static final String HEADER_GENRES_RESULT = "<p>Total score among Genres:</p>";
    private static final String HEADER_ABOUT_RESULT = "<p>Info about voters:</p>";
    private static final String BR = "<br>";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.LLL HH:mm:ss");

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");

        GenreService genreService = context.getBean("genreService", GenreService.class);
        List<Genre> genres = genreService.getGenres();
        genres.forEach(System.out::println);

        PerformerService performerService = context.getBean("performerService", PerformerService.class);
        List<Performer> performers = performerService.getPerformers();
        performers.forEach(System.out::println);

        genreService.create(new GenreDto(null, "testGenre"));
        performerService.create(new PerformerDto(null, "testPerformer"));

        List<Genre> genresAfterCreate = genreService.getGenres();
        List<Performer> performersAfterCreate = performerService.getPerformers();

        sleepAndShow(genresAfterCreate, performersAfterCreate);

        VoteService voteService = context.getBean("voteService", VoteService.class);
        voteService.addVote(new VoteDto(1L, List.of(1L, 2L, 3L), "test about"));

        StatisticsService statisticsService = context.getBean("statisticsService", StatisticsService.class);
        VoteResultDto voteResult = statisticsService.getVoteResult();
        Map<String, Long> performersMap = voteResult.getSortedPerformerVotes();

        System.out.println(HEADER_PERFORMERS_RESULT);
        for (Map.Entry<String, Long> performer : performersMap.entrySet()) {
            System.out.println(performer.getKey() + "  " + performer.getValue());
        }

        Map<String, Long> genresMap = voteResult.getSortedGenreVotes();
        System.out.println(BR + HEADER_GENRES_RESULT);
        for (Map.Entry<String, Long> genre : genresMap.entrySet()) {
            System.out.println(genre.getKey() + "  " + genre.getValue());
        }

        List<SavedVote> sortedVoteInfos = voteResult.getSortedVoteInfos();
        System.out.println(BR + HEADER_ABOUT_RESULT);
        for (SavedVote savedVote : sortedVoteInfos) {
            System.out.println(savedVote.getCreateDateTime().format(FORMATTER) + "  " + savedVote.getAbout());
        }

    }

    private static void sleepAndShow(List<Genre> genres, List<Performer> performers) {
        try {
            Thread.sleep(5000);
            genres.forEach(System.out::println);
            performers.forEach(System.out::println);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
