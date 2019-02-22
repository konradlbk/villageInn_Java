package pl.konrad.szulc.chata.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String name;

    private RoomType roomType;

    private String picturePath;

    private BedType bedType;

//    private String description;

    private short pricePerDay;

//    private Integer roomsLimit;

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    private LocalDate availableDate;

}
