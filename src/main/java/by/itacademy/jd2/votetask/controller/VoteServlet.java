package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.dto.VoteDto;
import by.itacademy.jd2.votetask.exceptions.InvalidHttpRequestException;
import by.itacademy.jd2.votetask.exceptions.InvalidVoteException;
import by.itacademy.jd2.votetask.service.VoteService;
import by.itacademy.jd2.votetask.service.factories.VoteServiceSingleton;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {
    private static final String PERFORMER_LOWER_CASE = "performer";
    private static final String GENRE_LOWER_CASE = "genre";
    private static final String ABOUT_LOWER_CASE = "about";
    public static final String BR = "<br>";;
    private final VoteService voteService = VoteServiceSingleton.getInstance();



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        Map<String, String[]> parameterMap = req.getParameterMap();
        try {
            String[] performers = parameterMap.get(PERFORMER_LOWER_CASE);
            String performer = (performers == null) ? null : performers[0];
            if(performer == null || performers.length > 1) {
                throw new IllegalArgumentException("Должен быть указан 1 артист");
            }
//            Integer performer = Integer.parseInt(performers[0]);

            String[] genres = parameterMap.get(GENRE_LOWER_CASE);
            List<String> genresList = Arrays.asList(genres);

            String[] abouts = parameterMap.get(ABOUT_LOWER_CASE);
            String about = (abouts == null) ? null : abouts[0];

            VoteDto voteDto = new VoteDto(performer,genresList,about);

            voteService.addVote(voteDto);
            resp.sendRedirect(req.getContextPath() + "/vote_result");
        } catch (InvalidHttpRequestException e) {
            List<String> requestExceptionList = e.getRequestExceptionList();
            String exceptionsHttp = String.join(BR, requestExceptionList);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, exceptionsHttp);
        } catch (InvalidVoteException e) {
            List<String> voteExceptionList = e.getVoteExceptionList();
            String voteExceptions = String.join(BR, voteExceptionList);
            resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, voteExceptions);
        }
    }






}
