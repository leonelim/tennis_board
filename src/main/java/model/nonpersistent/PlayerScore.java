package model.nonpersistent;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerScore {
    // is a String so that we can display "AD"
    String points;
    int sets;
    int games;
}
