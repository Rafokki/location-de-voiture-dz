package dz.locationvoiture.domaine;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "reservation")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private UUID reference;
    private Integer nombreDeJour;
    private BigDecimal montantTotal;
    @Enumerated(EnumType.STRING)
    private DemandeReservationStatus status;
    @ManyToOne
    private DemandeReservation demandeReservation;

}
