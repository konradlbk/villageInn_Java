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
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return pricePerDay == room.pricePerDay &&
                Objects.equals(id, room.id) &&
                Objects.equals(name, room.name) &&
                roomType == room.roomType &&
                Objects.equals(picturePath, room.picturePath) &&
                bedType == room.bedType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, roomType, picturePath, bedType, pricePerDay);
    }


    //    private Integer roomsLimit;

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    private LocalDate availableDate;

}
