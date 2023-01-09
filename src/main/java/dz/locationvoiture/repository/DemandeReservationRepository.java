package dz.locationvoiture.repository;

import dz.locationvoiture.domaine.DemandeReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeReservationRepository extends JpaRepository<DemandeReservation, Integer> {
}
