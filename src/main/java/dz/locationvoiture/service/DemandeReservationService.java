package dz.locationvoiture.service;

import dz.locationvoiture.domaine.DemandeReservation;
import dz.locationvoiture.domaine.DemandeReservationStatus;
import dz.locationvoiture.domaine.Voiture;
import dz.locationvoiture.dto.DemandeReservationDto;
import dz.locationvoiture.exceptions.TraitementDemandeReservationException;
import dz.locationvoiture.repository.DemandeReservationRepository;
import dz.locationvoiture.repository.VoitureRepository;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.UUID;

@Service
public class DemandeReservationService {

    private final DemandeReservationRepository demandeReservationRepository;
    private final VoitureRepository voitureRepository;

    public DemandeReservationService(DemandeReservationRepository demandeReservationRepository, VoitureRepository voitureRepository) {
        this.demandeReservationRepository = demandeReservationRepository;
        this.voitureRepository = voitureRepository;
    }

    public void insertDemandeReservation(DemandeReservationDto demandeReservationDto) throws TraitementDemandeReservationException {
        if(demandeReservationDto == null) {
            return;
        }
        DemandeReservation demandeReservation = demandeReservationDtoToDemandeReservation(demandeReservationDto);
        Voiture voiture = voitureRepository.findByReference(demandeReservationDto.getVoitureUuid());
        if(voiture == null) {
            throw new TraitementDemandeReservationException(TraitementDemandeReservationException.ErrorCode.VOITURE_NON_DISPONIBLE, String.format("la reference %s n'exsite pas dans la base",demandeReservationDto.getVoitureUuid()));
        }
        demandeReservation.setVoiture(voiture);
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
                .reference(UUID.randomUUID())
                .build();
    }
}
