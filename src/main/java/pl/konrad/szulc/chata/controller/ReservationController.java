package pl.konrad.szulc.chata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.konrad.szulc.chata.model.CreateReservationDTO;
import pl.konrad.szulc.chata.model.Room;
import pl.konrad.szulc.chata.service.ReservationService;
import pl.konrad.szulc.chata.service.RoomService;

import java.util.List;

@Controller
@RequestMapping(path = "/reservation")
public class ReservationController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private ReservationService reservationService;


    @GetMapping(path = "/add")
    public String add(Model model){
        CreateReservationDTO reservation = new CreateReservationDTO();

        List<Room> rooms = roomService.getRooms();

        model.addAttribute("reservation", reservation);
        model.addAttribute("rooms", rooms);

        return "reservationAdd";

    }

    @PostMapping(path = "/add")
    public String add(Model model, CreateReservationDTO reservation){

        List<Room> rooms = roomService.getRooms();
        model.addAttribute("rooms", rooms);


        if (reservationService.checkAvailability(reservation)){
            model.addAttribute("reservation", reservation);
            model.addAttribute("error_message", " Room is not available. Change dates or room.");
            return "reservationAdd";
        }
        reservationService.addReservation(reservation);
        return "redirect:/index";

    }


}
