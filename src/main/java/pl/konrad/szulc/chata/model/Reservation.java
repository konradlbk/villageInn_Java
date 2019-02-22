package pl.konrad.szulc.chata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String customerFirstName;

    private String customerLastName;

    private String mail;

    private long totalPrice;

    private String phone;


    @OneToOne
    private Room room;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate reservationStartDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate reservationEndDate;






}
