package model.nonpersistent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.Player;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OngoingMatch {
    private Player player1;
    private Player player2;
    private PlayerScore score1;
    private PlayerScore score2;
}
