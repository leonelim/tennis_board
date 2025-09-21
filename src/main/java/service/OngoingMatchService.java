package service;

import dao.MatchDAO;
import dao.PlayerDAO;
import model.entity.Match;
import model.entity.Player;
import model.nonpersistent.OngoingMatch;
import model.nonpersistent.PlayerScore;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class OngoingMatchService {
    private static final OngoingMatchService INSTANCE = new OngoingMatchService();
    public static OngoingMatchService getInstance() {
        return INSTANCE;
    }

    private static final PlayerDAO playerDAO = PlayerDAO.getInstance();
    private static final MatchDAO matchDAO = MatchDAO.getInstance();
    private static final ConcurrentMap<UUID, OngoingMatch> ongoingMatches = new ConcurrentHashMap<>();

    public Optional<OngoingMatch> getOngoingMatch(UUID uuid) {
        OngoingMatch ongoingMatch = ongoingMatches.get(uuid);
        return Optional.ofNullable(ongoingMatch);
    }
    public void deleteOngoingMatch(UUID uuid) {
        ongoingMatches.remove(uuid);
    }

    public void createMatchAndSaveToMemory(UUID uuid, String name1, String name2) {
        name1 = name1.replace("<", "&lt;").replace(">", "&gt;")
                .replace("&", "&amp;");
        name2 = name2.replace("<", "&lt;").replace(">", "&gt;")
                .replace("&", "&amp;");
        String[] names = {name1, name2};
        OngoingMatch ongoingMatch = new OngoingMatch();
        ongoingMatch.setScore1(new PlayerScore());
        ongoingMatch.setScore2(new PlayerScore());
        ongoingMatch.getScore1().setPoints("0");
        ongoingMatch.getScore2().setPoints("0");
        for (String name: names) {
            Player player = new Player();
            player.setName(name);
            playerDAO.savePlayerIfUnique(player);
        }
        ongoingMatch.setPlayer1(playerDAO.findPlayer(name1).get());
        ongoingMatch.setPlayer2(playerDAO.findPlayer(name2).get());
        ongoingMatches.put(uuid, ongoingMatch);
    }

    public void persistMatchAndRemoveFromMemory(OngoingMatch ongoingMatch, Player winner) {
        Match match = new Match();
        match.setPlayer1(ongoingMatch.getPlayer1());
        match.setPlayer2(ongoingMatch.getPlayer2());
        match.setWinner(winner);
        matchDAO.saveMatch(match);
    }
}
