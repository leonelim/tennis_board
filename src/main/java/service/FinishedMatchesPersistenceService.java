package service;

import controller.FinishedMatchesController;
import dao.MatchDAO;
import model.entity.Match;

import java.util.List;

public class FinishedMatchesPersistenceService {
    public static final FinishedMatchesPersistenceService INSTANCE = new FinishedMatchesPersistenceService();
    public static FinishedMatchesPersistenceService getInstance() {
        return INSTANCE;
    }
    private FinishedMatchesPersistenceService() {}

    MatchDAO matchDAO = MatchDAO.getInstance();
    public List<Match> getMatches(int page) {
        return matchDAO.findMatches(page);
    }
}
