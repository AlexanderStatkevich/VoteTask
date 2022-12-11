package by.itacademy.jd2.votetask.controller;

import by.itacademy.jd2.votetask.service.api.IGenreService;
import by.itacademy.jd2.votetask.service.factories.GenreServiceSingleton;
import by.itacademy.jd2.votetask.util.BuildHtmlUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GenreServlet", urlPatterns = "/genres")
public class GenreServlet extends HttpServlet {
    private static final String HEADER = "<p><b>Choose 3-5 genres:</b></p>";
    private static final String FOOTER = "<p><b>Also write few words in about section...</b></p>";

    private final IGenreService genreService = GenreServiceSingleton.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        List<String> content = genreService.getContent();
        String htmlResult = BuildHtmlUtil.build(content,HEADER,FOOTER);
        writer.write(htmlResult);
    }


}