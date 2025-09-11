package service;

import dto.OngoingMatchDTO;
import model.entity.Player;
import model.nonpersistent.OngoingMatch;
import model.nonpersistent.PlayerScore;

import java.util.Optional;
import java.util.StringTokenizer;
import java.util.UUID;

public class MatchScoreCalculationService {
    private static final MatchScoreCalculationService INSTANCE = new MatchScoreCalculationService();
    public static MatchScoreCalculationService getInstance() {
        return INSTANCE;
    }
    OngoingMatchService ongoingMatchService = OngoingMatchService.getInstance();

    public boolean isGameOver(OngoingMatch ongoingMatch) {
        return ongoingMatch.getScore1().getSets() == 2 || ongoingMatch.getScore2().getSets() == 2;
    }
    public void calculatePoints(UUID uuid, int pointWinnerId) {
        Optional<OngoingMatch> ongoingMatchOptional = ongoingMatchService.getOngoingMatch(uuid);
        if (ongoingMatchOptional.isEmpty()) {
            return;
        }
        OngoingMatch ongoingMatch = ongoingMatchOptional.get();
        addPoint(ongoingMatch, pointWinnerId, uuid);

    }
    public void addPoint(OngoingMatch ongoingMatch, int pointWinnerId, UUID uuid) {
        PlayerScore winnerScore;
        PlayerScore opponentScore;
        if (ongoingMatch.getPlayer1().getId() == pointWinnerId) {
            winnerScore = ongoingMatch.getScore1();
            opponentScore = ongoingMatch.getScore2();
        } else {
            winnerScore = ongoingMatch.getScore2();
            opponentScore = ongoingMatch.getScore1();
        }
        if (winnerScore.getGames() == 6 && opponentScore.getGames() == 6) {
            winnerScore.setPoints(String.valueOf(Integer.parseInt(winnerScore.getPoints()) + 1));
            if (Integer.parseInt(winnerScore.getPoints()) - Integer.parseInt(opponentScore.getPoints()) >= 2
            && Integer.parseInt(winnerScore.getPoints()) >= 7) {
                winnerScore.setPoints("0");
                winnerScore.setGames(0);
                winnerScore.setSets(winnerScore.getSets() + 1);
                opponentScore.setPoints("0");
                opponentScore.setGames(0);
            }
            return;
        }
        switch (winnerScore.getPoints()) {
            case "0":
                winnerScore.setPoints("15");
                break;
            case "15":
                winnerScore.setPoints("30");
                break;
            case "30":
                winnerScore.setPoints("40");
                break;
            case "40":
                if (opponentScore.getPoints().equals("AD")) {
                    opponentScore.setPoints("40");
                } else if (opponentScore.getPoints().equals("40")) {
                    winnerScore.setPoints("AD");
                } else {
                    winnerScore.setPoints("0");
                    winnerScore.setGames(winnerScore.getGames() + 1);
                    opponentScore.setPoints("0");
                }
                break;
            case "AD":
                winnerScore.setPoints("0");
                winnerScore.setGames(winnerScore.getGames() + 1);
                opponentScore.setPoints("0");
                break;
            default:
                break;
        }
        if (winnerScore.getGames() >= 6) {
            if (winnerScore.getGames() - opponentScore.getGames() >= 2) {
                winnerScore.setSets(winnerScore.getSets() + 1);
                winnerScore.setGames(0);
                opponentScore.setGames(0);
            }
        }
        if (winnerScore.getSets() == 2) {
            Player winner;
            if (ongoingMatch.getPlayer1().getId() == pointWinnerId) {
                winner = ongoingMatch.getPlayer1();
            } else {
                winner = ongoingMatch.getPlayer2();
            }
            ongoingMatchService.persistMatch(ongoingMatch, winner, uuid);
        }
    }
}
