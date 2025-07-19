package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Match;
import service.FinishedMatchesPersistenceService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/matches")
public class FinishedMatchesController extends HttpServlet {
    FinishedMatchesPersistenceService finishedMatchesPersistenceService = FinishedMatchesPersistenceService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNumber = Integer.parseInt(req.getParameter("page"));
        String filterByPlayerName = req.getParameter("filter_by_player_name");
        List<Match> matches = finishedMatchesPersistenceService.getMatches();
        req.setAttribute("matches", matches);
        req.getRequestDispatcher("jsp/matches.jsp").forward(req, resp);
    }
}
