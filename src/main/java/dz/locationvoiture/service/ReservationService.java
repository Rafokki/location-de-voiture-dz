package dz.locationvoiture.service;

import dz.locationvoiture.domaine.DemandeReservation;
import dz.locationvoiture.domaine.DemandeReservationStatus;
import dz.locationvoiture.domaine.Reservation;
import dz.locationvoiture.dto.ReservationDto;
import dz.locationvoiture.exceptions.TraitementDemandeReservationException;
import dz.locationvoiture.repository.DemandeReservationRepository;
import dz.locationvoiture.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static dz.locationvoiture.domaine.DemandeReservationStatus.TRAITEE;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final DemandeReservationRepository demandeReservationRepository;

    public ReservationService(ReservationRepository reservationRepository, DemandeReservationRepository demandeReservationRepository) {
        this.reservationRepository = reservationRepository;
        this.demandeReservationRepository = demandeReservationRepository;
    }

    public void insertReservation(ReservationDto reservationDto) throws TraitementDemandeReservationException {
        if (reservationDto == null) {
            return;
        }
        DemandeReservation demandeReservation = demandeReservationRepository.findByReference(reservationDto.getDemandeReservationUuid());
        if (demandeReservation == null) {
            throw new TraitementDemandeReservationException(TraitementDemandeReservationException.ErrorCode.DEMANDE_RESERVATION_NON_DISPONIBLE, String.format("la reference %s n'exsite pas dans la base", reservationDto.getDemandeReservationUuid()));
        }
        if(demandeReservation.getStatus() == TRAITEE) {
            throw new TraitementDemandeReservationException(TraitementDemandeReservationException.ErrorCode.DEMANDE_RESERVATION_DEJA_TRAITEE, String.format("la reference %s déja traitée", reservationDto.getDemandeReservationUuid()));
        }
        Reservation reservation = reservationBuilder(demandeReservation);
        BigDecimal  montantTotalLocation =  getMontantTotalLocation(demandeReservation);
        Integer     nombreDeJours =  getNmbJoursLocation(demandeReservation);
        reservation.setMontantTotal(montantTotalLocation);
        setNombreDeJour(reservation, nombreDeJours);
        reservationRepository.save(reservation);
        miseAjourStatusDemandeReservation(demandeReservation);
    }

    private void setNombreDeJour(Reservation reservation, Integer nombreDeJours) {
        reservation.setNombreDeJour(nombreDeJours == 0 ? 1: nombreDeJours);
    }

    private void miseAjourStatusDemandeReservation(DemandeReservation demandeReservation) {
        demandeReservation.setStatus(TRAITEE);
        demandeReservationRepository.save(demandeReservation);
    }

    private BigDecimal getMontantTotalLocation(DemandeReservation demandeReservation) {
        BigDecimal prixDeLocation   = demandeReservation.getVoiture().getPrixDeLocation();
        int nmbJoursLocation = getNmbJoursLocation(demandeReservation);
        if(nmbJoursLocation == 0) {
            return prixDeLocation;
        }
        return prixDeLocation.multiply(BigDecimal.valueOf(nmbJoursLocation));
    }

    private Reservation reservationBuilder(DemandeReservation demandeReservation) {
        return Reservation
                .builder()
                .reference(UUID.randomUUID())
                .status(DemandeReservationStatus.CONFIRMEE)
                .demandeReservation(demandeReservation)
                .build();
    }

    private static long calculateDifferenceInDays(ZonedDateTime from, ZonedDateTime now) {
        long secondsDiff = now.toEpochSecond() - from.toEpochSecond();
        return TimeUnit.DAYS.convert(secondsDiff, TimeUnit.SECONDS);
    }

    private static int getNmbJoursLocation(DemandeReservation demandeReservation) {
        return Math.toIntExact(calculateDifferenceInDays(demandeReservation.getDatePriseEnCharge(), demandeReservation.getDateDeRetour()));
    }
}
