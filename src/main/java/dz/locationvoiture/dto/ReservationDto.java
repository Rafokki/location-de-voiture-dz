package dz.locationvoiture.dto;
import dz.locationvoiture.domaine.DemandeReservationStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
public class ReservationDto {
    private UUID reference;
    private Integer nombreDeJour;
    private BigDecimal montantTotal;
    private DemandeReservationStatus status;
    private UUID demandeReservationUuid;
}
