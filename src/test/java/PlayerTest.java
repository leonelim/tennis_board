import dao.MatchDAO;
import dao.PlayerDAO;
import model.entity.Match;
import model.entity.Player;
import model.nonpersistent.OngoingMatch;
import org.junit.Test;
import service.OngoingMatchService;

import java.util.List;

public class PlayerTest {

    @Test
    public void PlayerDAOTest() {
        PlayerDAO playerDAO = PlayerDAO.getInstance();
        Player player = new Player();
        player.setName("Joe");
        playerDAO.savePlayerIfUnique(player);

        Player player1 = playerDAO.findPlayer("Joe").get();
        System.out.println(player1.getId() + player1.getName());
    }

    @Test
    public void MatchDAOTest() {
        OngoingMatchService ongoingMatchService = OngoingMatchService.getInstance();
        PlayerDAO playerDAO = PlayerDAO.getInstance();
        MatchDAO matchDAO = MatchDAO.getInstance();
        OngoingMatch ongoingMatch = new OngoingMatch();
        Player player1 = new Player();
        player1.setName("Joe");
        Player player2 = new Player();
        player2.setName("Nick");
        playerDAO.savePlayerIfUnique(player1);
        playerDAO.savePlayerIfUnique(player2);
        player1 = playerDAO.findPlayer("Joe").get();
        player2 = playerDAO.findPlayer("Nick").get();
        ongoingMatch.setPlayer1(player1);
        ongoingMatch.setPlayer2(player2);

        ongoingMatchService.persistMatch(ongoingMatch, player1);

        List<Match> matches = matchDAO.findMatches();
        System.out.println(matches);
    }
}