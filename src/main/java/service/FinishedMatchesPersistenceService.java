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
    public synchronized List<Match> getMatches(int page, String name) {
        return matchDAO.findMatches(page, name);
    }
    public Long getAmount(String name) {
        return matchDAO.countEntries(name);
    }
}
