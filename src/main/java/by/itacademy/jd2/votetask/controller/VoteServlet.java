package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.dto.VoteDto;
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
import java.util.stream.Collectors;

@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {
    private static final String PERFORMER_LOWER_CASE = "performer";
    private static final String GENRE_LOWER_CASE = "genre";
    private static final String ABOUT_LOWER_CASE = "about";
    public static final String TAB = ",  ";;
    private final VoteService voteService = VoteServiceSingleton.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        Map<String, String[]> parameterMap = req.getParameterMap();
        try {

            VoteDto voteDto = voteDtoExtractor(parameterMap);
            voteService.addVote(voteDto);
            resp.sendRedirect(req.getContextPath() + "/vote_result");

        } catch (InvalidVoteException e) {
            List<String> voteExceptionList = e.getVoteExceptionList();
            String voteExceptions = String.join(TAB, voteExceptionList);
            resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, voteExceptions);
        }
    }

    private VoteDto voteDtoExtractor( Map<String, String[]> parameterMap){
        String[] performers = parameterMap.get(PERFORMER_LOWER_CASE);
        Long performerId = (performers == null) ? null : Long.parseLong(performers[0]);
        if(performerId == null || performers.length > 1) {
            throw new IllegalArgumentException("Have to be only one performer");
        }

        String[] genres = parameterMap.get(GENRE_LOWER_CASE);
        List<String> genresList = Arrays.asList(genres);
        List<Long> genresIdList =null;
        if(!genresList.isEmpty()) {
            genresIdList = genresList.stream()
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
        }

        String[] abouts = parameterMap.get(ABOUT_LOWER_CASE);
        String about = (abouts == null) ? null : abouts[0];

        return new VoteDto(performerId,genresIdList,about);
    }


}
