package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.domain.Vote;
import by.itacademy.jd2.votetask.exceptions.InvalidVoteException;
import by.itacademy.jd2.votetask.service.vote.VoteExtractor;
import by.itacademy.jd2.votetask.service.vote.VoteService;
import by.itacademy.jd2.votetask.service.vote.VoteValidator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {
    private final String TAGGED_SUCCESS = "<p><b>SUCCESS</b></p>";
    private final VoteExtractor voteExtractor = new VoteExtractor();
    private final VoteValidator voteValidator = new VoteValidator();
    private final VoteService voteService = new VoteService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        Map<String, String[]> parameterMap = req.getParameterMap();
        Vote extractedVote = voteExtractor.extract(parameterMap);
        try {
            voteValidator.validate(extractedVote);
        } catch (InvalidVoteException e) {
            List<String> exceptionList = e.getExceptionList();
        }
        voteService.addVote(extractedVote);
        writer.write(TAGGED_SUCCESS);
    }
}