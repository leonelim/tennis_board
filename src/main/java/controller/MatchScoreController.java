package controller;

import dto.OngoingMatchDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.nonpersistent.OngoingMatch;
import service.MatchScoreCalculationService;
import service.OngoingMatchService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {
    private static final OngoingMatchService ongoingMatchService = OngoingMatchService.getInstance();
    private static final MatchScoreCalculationService matchScoreCalculatorService = MatchScoreCalculationService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidStr = req.getParameter("uuid");
        UUID uuid = UUID.fromString(uuidStr);
        OngoingMatch ongoingMatch = ongoingMatchService.getOngoingMatch(uuid).get();
        // TODO make a page for wrong uuid
        req.setAttribute("match", ongoingMatch);
        req.setAttribute("uuid", uuid);
        if (matchScoreCalculatorService.isGameOver(ongoingMatch)) {
            ongoingMatchService.deleteOngoingMatch(uuid);
            req.getRequestDispatcher("jsp/match-score-finished.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("jsp/match-score.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidStr = req.getParameter("uuid");
        UUID uuid = UUID.fromString(uuidStr);
        int pointWinnerId = Integer.parseInt(req.getParameter("point_winner_id"));
        matchScoreCalculatorService.calculatePoints(uuid, pointWinnerId);
        resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + uuid);
    }
}
