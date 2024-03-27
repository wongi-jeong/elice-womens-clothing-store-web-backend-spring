package cloud2.shopingmall.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {

    private String userName;
    private String userEmail;
    private String userPhoneNumber;
    private String userAddress;
    private Gender gender;
    private String birthDate;



    public enum Gender {
        MALE, FEMALE,
    }

}
