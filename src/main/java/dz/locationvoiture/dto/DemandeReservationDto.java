package dz.locationvoiture.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import java.util.Date;

@Builder
@Getter
public class DemandeReservationDto {
    private String mail;
    private String numeroTel;
    private String nom;
    private String prenom;
    private String heurePriseEnCharge;
    private String heureDeRetour;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date datePriseEnCharge;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateDeRetour;
}
