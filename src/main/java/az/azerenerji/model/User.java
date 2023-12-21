package az.azerenerji.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    long id;
    String firstname;
    String lastname;
    String email;
    String password;
    LocalDateTime expiredData;
    @Column(length = 6)
    String sixDigitCode;

}
