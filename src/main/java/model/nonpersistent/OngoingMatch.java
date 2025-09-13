package model.nonpersistent;

import lombok.*;
import model.entity.Player;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OngoingMatch {
    private Player player1;
    private Player player2;
    private PlayerScore score1;
    private PlayerScore score2;
}
