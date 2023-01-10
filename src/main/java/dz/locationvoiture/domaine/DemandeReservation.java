package dz.locationvoiture.domaine;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "demande_reservation")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class DemandeReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String mail;
    private String numeroTel;
    private String nom;
    private String prenom;
    private String heurePriseEnCharge;
    private String heureDeRetour;
    private ZonedDateTime datePriseEnCharge;
    private ZonedDateTime dateDeRetour;
    @Enumerated(EnumType.STRING)
    private DemandeReservationStatus status;
    private UUID reference;
    @ManyToOne
    private Voiture voiture;
}
