package dz.locationvoiture.repository;

import dz.locationvoiture.domaine.DemandeReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DemandeReservationRepository extends JpaRepository<DemandeReservation, Integer> {
    DemandeReservation findByReference(UUID uuid);
}
