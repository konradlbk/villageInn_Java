package pl.konrad.szulc.chata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.konrad.szulc.chata.model.CreateReservationDTO;
import pl.konrad.szulc.chata.model.Reservation;
import pl.konrad.szulc.chata.model.Room;
import pl.konrad.szulc.chata.repository.ReservationRepository;
import pl.konrad.szulc.chata.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomRepository roomRepository;


    public void addReservation(CreateReservationDTO createReservationDTO) {

        Optional<Room> roomOptional = roomService.find(createReservationDTO.getRoomId());

        long amountOfDays = DAYS.between(createReservationDTO.getReservationStartDate(),createReservationDTO.getReservationEndDate());

        long pricePerDay = roomOptional.get().getPricePerDay();

        long totalPrice = pricePerDay*amountOfDays;

        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();


            Reservation reservation = new Reservation();

        if (checkAvailability(createReservationDTO)==true) {
            System.out.println("zajete");

        } else {
            System.out.println("niby dziala");
            reservation = new Reservation(null, createReservationDTO.getCustomerFirstName(), createReservationDTO.getCustomerLastName(),
                            createReservationDTO.getMail(), totalPrice, createReservationDTO.getPhone(), roomOptional.get(), createReservationDTO.getReservationStartDate(),
                    createReservationDTO.getReservationEndDate());
            saveReservation(reservation);
        }

        }

    }

    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);

    }

    public List<Reservation> findReservations() {

        return reservationRepository.findAll();

    }

    public boolean checkAvailability(CreateReservationDTO createReservationDTO) {

        List<Reservation> reservationList = findReservations();

        boolean isNotAvailable = false;

        for (int i=0; i<reservationList.size(); i++){
        if (createReservationDTO.getReservationStartDate().isBefore(reservationList.get(i).getReservationEndDate())
                && createReservationDTO.getRoomId() == reservationList.get(i).getRoom().getId()
                && (createReservationDTO.getReservationEndDate().isAfter(reservationList.get(i).getReservationStartDate())
                || createReservationDTO.getReservationEndDate().isEqual(reservationList.get(i).getReservationStartDate()))) {
            isNotAvailable=true;



        }
        }

        return isNotAvailable;


    }
}


//            for (int i = 0; i < reservationList.size(); i++) {
//
//                if (createReservationDTO.getReservationStartDate().isBefore(reservationList.get(i).getReservationEndDate()) && createReservationDTO.getRoomId() == reservationList.get(i).getRoom().getId()
//                        && (createReservationDTO.getReservationEndDate().isAfter(reservationList.get(i).getReservationStartDate())
//                        || createReservationDTO.getReservationEndDate().isEqual(reservationList.get(i).getReservationStartDate()))) {
//                    System.out.println("Zajete w tych datach i chuj");
//                    return;
//                } else {
//
//                    reservation = new Reservation(null, createReservationDTO.getCustomerFirstName(), createReservationDTO.getCustomerLastName(),
//                            createReservationDTO.getMail(), roomOptional.get(), createReservationDTO.getReservationStartDate(), createReservationDTO.getReservationEndDate());
//
//                }
//            }

//            if (reservation != null) {
//                saveReservation(reservation);
