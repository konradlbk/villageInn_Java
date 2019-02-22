package pl.konrad.szulc.chata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.konrad.szulc.chata.model.Reservation;
import pl.konrad.szulc.chata.model.Room;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {


}
