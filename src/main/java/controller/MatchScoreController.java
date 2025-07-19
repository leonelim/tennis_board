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
        String uuid = req.getParameter("uuid");
        OngoingMatch ongoingMatch = ongoingMatchService.getOngoingMatch(UUID.fromString(uuid)).get();
        // TODO make a page for wrong uuid
        req.setAttribute("match", ongoingMatch);
        req.setAttribute("uuid", uuid);
        req.getRequestDispatcher("jsp/match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidStr = req.getParameter("uuid");
        UUID uuid = UUID.fromString(uuidStr);
        int pointWinnerId = Integer.parseInt(req.getParameter("point_winner_id"));
        matchScoreCalculatorService.calculatePoints(uuid, pointWinnerId);
        OngoingMatch ongoingMatch = ongoingMatchService.getOngoingMatch(uuid).get();
        req.setAttribute("uuid", uuid);
        req.setAttribute("match", ongoingMatch);
        resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + uuid);
    }
}
