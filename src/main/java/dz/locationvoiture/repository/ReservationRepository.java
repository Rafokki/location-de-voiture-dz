package dz.locationvoiture.repository;
import dz.locationvoiture.domaine.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
