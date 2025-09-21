package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.PageContext;
import model.entity.Match;
import org.apache.logging.log4j.Logger;
import service.FinishedMatchesPersistenceService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/matches")
public class FinishedMatchesController extends HttpServlet {
    FinishedMatchesPersistenceService finishedMatchesPersistenceService = FinishedMatchesPersistenceService.getInstance();
    Logger logger = util.LoggingUtil.getLogger();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNumber = Integer.parseInt(req.getParameter("page"));
        String filterByPlayerName = req.getParameter("filter_by_player_name");
        List<Match> matches = finishedMatchesPersistenceService.getMatches(pageNumber);
        Long amount = finishedMatchesPersistenceService.getAmount();
        logger.warn(matches.isEmpty());
        req.setAttribute("matches", matches);
        req.setAttribute("amount", amount);
        int previousPage = pageNumber - 1;
        int nextPage = pageNumber + 1;
        req.setAttribute("pageNumber", pageNumber);
        req.setAttribute("nextPage", nextPage);
        req.setAttribute("previousPage", previousPage);
        req.getRequestDispatcher("jsp/matches.jsp").forward(req, resp);
    }
}
