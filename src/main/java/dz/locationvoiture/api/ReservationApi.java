package dz.locationvoiture.api;

import dz.locationvoiture.dto.DemandeReservationDto;
import dz.locationvoiture.dto.ReservationDto;
import dz.locationvoiture.exceptions.TraitementDemandeReservationException;
import dz.locationvoiture.service.DemandeReservationService;
import dz.locationvoiture.service.ReservationService;
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
public class ReservationApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationApi.class);

    private final ReservationService reservationService;

    public ReservationApi(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("reservation")
    public void insertReservation(@RequestBody ReservationDto reservationDto) {
        try {
            reservationService.insertReservation(reservationDto);
        } catch (TraitementDemandeReservationException e) {
            LOGGER.error("Errors:  {}", e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
