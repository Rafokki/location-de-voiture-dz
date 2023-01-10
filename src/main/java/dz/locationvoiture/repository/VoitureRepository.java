package dz.locationvoiture.repository;
import dz.locationvoiture.domaine.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VoitureRepository extends JpaRepository<Voiture, Integer> {
    Voiture findByReference(UUID uuid);
}
