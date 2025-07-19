package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.OngoingMatchService;

import java.util.Objects;
import java.util.UUID;

import java.io.IOException;

@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {
    private static final OngoingMatchService ongoingMatchService = OngoingMatchService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name1 = req.getParameter("name1");
        String name2 = req.getParameter("name2");
        if (Objects.equals(name1, name2)) {
            req.getRequestDispatcher("jsp/new-match.jsp").forward(req, resp);
        }
        UUID uuid = UUID.randomUUID();
        ongoingMatchService.createMatchAndSaveToMemory(uuid, name1, name2);
        resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + uuid);
    }
}
