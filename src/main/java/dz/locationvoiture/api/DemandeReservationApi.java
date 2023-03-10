package dz.locationvoiture.api;

import dz.locationvoiture.dto.DemandeReservationDto;
import dz.locationvoiture.exceptions.TraitementDemandeReservationException;
import dz.locationvoiture.service.DemandeReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1")
public class DemandeReservationApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemandeReservationApi.class);

    private final DemandeReservationService demandeReservationService;

    public DemandeReservationApi(DemandeReservationService demandeReservationService) {
        this.demandeReservationService = demandeReservationService;
    }

    @PostMapping("demande-reservation")
    public void insertDemandeReservation(@RequestBody DemandeReservationDto demandeReservationDto) {
        try {
            demandeReservationService.insertDemandeReservation(demandeReservationDto);
        } catch (TraitementDemandeReservationException e) {
            LOGGER.error("Errors:  {}", e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
