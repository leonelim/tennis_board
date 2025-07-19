package dto;

import model.entity.Player;

public record OngoingMatchDTO(Player player1,
                              Player player2,
                              PlayerScoreDTO score1,
                              PlayerScoreDTO score2
                              ){
}
