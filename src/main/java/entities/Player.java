package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Players")
public class Player {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
