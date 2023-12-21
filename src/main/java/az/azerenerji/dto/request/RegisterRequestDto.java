package az.azerenerji.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequestDto {
      String firstName;
      String lastName;
      String email;
      String password;
      String repeatPassword;
}
