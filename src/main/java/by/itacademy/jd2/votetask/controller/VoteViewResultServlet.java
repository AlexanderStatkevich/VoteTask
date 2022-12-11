package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.dto.SavedVoteDTO;
import by.itacademy.jd2.votetask.dto.VoteResultDto;
import by.itacademy.jd2.votetask.service.StatisticsService;
import by.itacademy.jd2.votetask.service.factories.StatisticsServiceSingleton;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "VoteViewResultServlet", urlPatterns = "/vote_result")
public class VoteViewResultServlet extends HttpServlet {
    private static final String HEADER_PERFORMERS_RESULT = "<p>Total score among Performers:</p>";
    private static final String HEADER_GENRES_RESULT = "<p>Total score among Genres:</p>";
    private static final String HEADER_ABOUT_RESULT = "<p>Info about voters:</p>";
    private static final String BR = "<br>";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.LLL HH:mm:ss");
    private final StatisticsService statisticsService = StatisticsServiceSingleton.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        VoteResultDto voteResult = statisticsService.getVoteResult();

        Map<String, Long> performers = voteResult.getSortedPerformerVotes();
        writer.write(HEADER_PERFORMERS_RESULT);
        for (Map.Entry<String, Long> performer : performers.entrySet()) {
            writer.write("<p>" + performer.getKey() + "  " + performer.getValue() + "</p>");
        }

        Map<String, Long> genres = voteResult.getSortedGenreVotes();
        writer.write(BR + HEADER_GENRES_RESULT);
        for (Map.Entry<String, Long> genre : genres.entrySet()) {
            writer.write("<p>" + genre.getKey() + "  " + genre.getValue() + "</p>");
        }

        List<SavedVoteDTO> sortedVoteInfos = voteResult.getSortedVoteInfos();
        writer.write(BR + HEADER_ABOUT_RESULT);
        for (SavedVoteDTO savedVote : sortedVoteInfos) {
            writer.write("<p>" + savedVote.getCreateDateTime().format(FORMATTER) + "  " + savedVote.getVote().getAbout() + "</p>");
        }
    }
}