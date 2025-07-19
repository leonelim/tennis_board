package model.nonpersistent;

import lombok.Data;

@Data
public class PlayerScore {
    // is a String so that we can display "AD"
    String points;
    int sets;
    int games;
}
