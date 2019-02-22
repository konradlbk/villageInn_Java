package pl.konrad.szulc.chata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.konrad.szulc.chata.model.Room;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {


//    List<Room> findAllByAvailableDateAfter(LocalDate date);



}
