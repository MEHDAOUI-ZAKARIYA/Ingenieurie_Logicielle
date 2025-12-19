package ma.ensa.jpaap.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data @AllArgsConstructor @NoArgsConstructor
@Entity

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private LocalDate dateNaissance;
    private boolean malade;
    private int score;
}
