package dz.locationvoiture.api;

import dz.locationvoiture.domaine.DemandeReservation;
import dz.locationvoiture.dto.DemandeReservationDto;
import dz.locationvoiture.service.DemandeReservationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class DemandeReservationApi {

    private final DemandeReservationService demandeReservationService;

    public DemandeReservationApi(DemandeReservationService demandeReservationService) {
        this.demandeReservationService = demandeReservationService;
    }

    @PostMapping("demande-reservation")
    public void insertReservation(@RequestBody DemandeReservationDto demandeReservationDto) {
        demandeReservationService.insertReservation(demandeReservationDto);
    }
}
