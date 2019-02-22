package pl.konrad.szulc.chata.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.konrad.szulc.chata.model.Reservation;
import pl.konrad.szulc.chata.model.Room;
import pl.konrad.szulc.chata.repository.ReservationRepository;
import pl.konrad.szulc.chata.repository.RoomRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public void addRoom (Room room){

        roomRepository.save(room);

    }

    public List<Room> getRooms() {

        return roomRepository.findAll();
    }

    public List<Room> getRoomByDates(LocalDate startDate, LocalDate endDate){

        List<Reservation> reservations = reservationRepository.findAll();
        List<Room> rooms = new ArrayList<>();

        for (int i=0; i<reservations.size(); i++){

            if ((startDate.isEqual(reservations.get(i).getReservationStartDate() )|| startDate.isAfter(reservations.get(i).getReservationStartDate())) &&
                    (endDate.isBefore(reservations.get(i).getReservationEndDate()) || endDate.isEqual(reservations.get(i).getReservationEndDate()))){
                System.out.println("Nie ma pokoju");
            }else {
            rooms.add(reservations.get(i).getRoom());


            }
        }
        return rooms;
    }

//    public List<Room> findByDate(LocalDate date){
//
//        List<Room> foundByDate = roomRepository.findAllByAvailableDateAfter(date);
//
//        return foundByDate;
//
//    }

    public Optional<Room> find(Long id) {
        return roomRepository.findById(id);
    }

}
