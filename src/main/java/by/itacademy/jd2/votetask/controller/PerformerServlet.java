package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.dto.PerformerDTO;
import by.itacademy.jd2.votetask.service.api.IPerformerService;
import by.itacademy.jd2.votetask.service.factories.PerformerServiceSingleton;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "PerformerServlet", urlPatterns = "/performers")
public class PerformerServlet extends HttpServlet {

    private static final String BR = "<br>";
    private static final String HEADER = "<p><b>Choose one performer:</b></p>";
    private static final String FOOTER = "<p><b>Thanks for your vote!</b></p>";

    private final IPerformerService performerService = PerformerServiceSingleton.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        List<PerformerDTO> genreDtoList = performerService.getContent();
        StringBuilder str = new StringBuilder();
        for(PerformerDTO performerDTO :genreDtoList){
            str.append(performerDTO.getId()).append(" - ").append(performerDTO.getNickName()).append(BR);
        }
        String htmlResult = HEADER + str + FOOTER;

        writer.write(htmlResult);
    }
}
