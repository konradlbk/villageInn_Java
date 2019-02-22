package pl.konrad.szulc.chata.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.konrad.szulc.chata.model.BedType;
import pl.konrad.szulc.chata.model.Room;
import pl.konrad.szulc.chata.model.RoomType;
import pl.konrad.szulc.chata.service.ReservationService;
import pl.konrad.szulc.chata.service.RoomService;
import pl.konrad.szulc.chata.service.StorageService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Controller
//@RequestMapping(path = "/addRoom")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private ReservationService reservationService;

    private final StorageService storageService;

    @Autowired
    public RoomController(StorageService storageService) {
        this.storageService = storageService;
    }


    @GetMapping(path = "/upload")
    public String add (Model model){

        Room room = new Room();
        List<RoomType> roomTypes = new ArrayList<RoomType>(EnumSet.allOf(RoomType.class));
        List<BedType> bedTypes = new ArrayList<BedType>(EnumSet.allOf(BedType.class));

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        model.addAttribute("room", room);
        model.addAttribute("roomTypes", roomTypes);
        model.addAttribute("bedTypes", bedTypes);



        return "uploadForm";
    }

    @PostMapping(path = "/upload")
    public String addRooms(Room room, @RequestParam("file") MultipartFile file,
                           RedirectAttributes redirectAttributes) throws IOException {


        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        if (!file.isEmpty()){
            String pathToPicture = file.getOriginalFilename();
//            byte[] bytes = file.getBytes();
//            String pathToPicutre = new String(bytes);
            room.setPicturePath(pathToPicture);
            System.out.println(pathToPicture);
        }
        roomService.addRoom(room);


        return "uploadForm";

    }

    @GetMapping(path = "/list")
    public String list(Model model, @RequestParam(value = "startDate", required = false)@DateTimeFormat(pattern="yyyy-MM-dd") LocalDate startDate,
                       @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endDate){
        List<Room> foundRooms = roomService.getRoomByDates(startDate, endDate);
        model.addAttribute("foundRooms", foundRooms);
//        model.addAttribute("checkInDate", dateCheck);





        return "availableRoomsList";
    }

//    @GetMapping(path = "/list/check/")
//    public String listChecked(Model model, @RequestParam(value = "dateCheck" , required = false) String dateString){
//
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE;
//
//        LocalDate date = dateTimeFormatter.parse(dateString, LocalDate::from);
////        model.addAttribute("checkInDate", dateCheck);
////
//        List<Room> found = roomService.findByDate(date);
////        for (int i=0; i< roomService.getRooms().size(); i++){
////
////            if (date.isAfter(roomService.getRooms().get(i).getAvailableDate())){
////                List<Room> found = roomService.getRooms();
////            }
////        }
//        model.addAttribute("found", found);
////        model.addAttribute("found", found);
////        if (dateCheck!=null){
////            for (int i=0; i<roomService.getRooms().size(); i++){
////                if (dateCheck.isAfter(roomService.getRooms().get(i).getAvailableDate())){
////                    List<Room> filteredRooms = roomService.findByDate(dateCheck);
////                    model.addAttribute("filteredRooms", filteredRooms);
////                }
////
////            }
////
////        }
//        return "roomsListChecked";
//
//    }


    @GetMapping(path = "/index")
    public String showIndex(){

        return "index";
    }

}
