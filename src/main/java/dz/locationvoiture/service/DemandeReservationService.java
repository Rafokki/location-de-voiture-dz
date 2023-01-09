package dz.locationvoiture.service;

import dz.locationvoiture.domaine.DemandeReservation;
import dz.locationvoiture.domaine.DemandeReservationStatus;
import dz.locationvoiture.dto.DemandeReservationDto;
import dz.locationvoiture.repository.DemandeReservationRepository;
import org.springframework.stereotype.Service;

import java.time.ZoneId;

@Service
public class DemandeReservationService {

    private final DemandeReservationRepository demandeReservationRepository;

    public DemandeReservationService(DemandeReservationRepository demandeReservationRepository) {
        this.demandeReservationRepository = demandeReservationRepository;
    }

    public void insertReservation(DemandeReservationDto demandeReservationDto) {
        if(demandeReservationDto == null) {
            return;
        }
        DemandeReservation demandeReservation = demandeReservationDtoToDemandeReservation(demandeReservationDto);
        demandeReservationRepository.save(demandeReservation);
    }

    private DemandeReservation demandeReservationDtoToDemandeReservation(DemandeReservationDto dto) {
        return DemandeReservation
                .builder()
                .mail(dto.getMail())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .numeroTel(dto.getNumeroTel())
                .datePriseEnCharge(dto.getDatePriseEnCharge().toInstant().atZone(ZoneId.systemDefault()))
                .dateDeRetour(dto.getDateDeRetour().toInstant().atZone(ZoneId.systemDefault()))
                .heureDeRetour(dto.getHeureDeRetour())
                .heurePriseEnCharge(dto.getHeurePriseEnCharge())
                .status(DemandeReservationStatus.EN_COURS_DE_TRAITEMENT)
                .build();
    }
}
