package ma.ensa.jpa_ap.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
;

import java.time.LocalDate;


@Data // Lambok ajoute les getters et les setters
@NoArgsConstructor
@AllArgsConstructor
@Entity // Entity JPA
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String nom;
    @Column(name = "date-naissance")
    private LocalDate dateNaissance;
    private boolean malade;
    private int score ;



}
