package cloud2.shopingmall.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {

    @NotNull(message = "이름을 입력해 주세요.")
    @Pattern(regexp = "^[가-힣]*$", message = "한글만 입력 가능합니다.")
    private String userProfileName;

    @NotNull(message = "이메일을 입력해 주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "올바른 이메일 주소 형식이 아닙니다.")
    private String userEmail;

    @NotNull(message = "핸드폰 번호를 입력해 주세요.")
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message = "올바른 핸드폰 번호 형식이 아닙니다.")
    private String userPhoneNumber;

//    @NotNull(message = "주소를 입력해 주세요.")
    private String userAddress;

//    @NotNull(message = "성별을 선택해 주세요.")
    private Gender gender;

//    @NotNull(message = "생년월일을 입력해 주세요.")
    private String birthDate;



    public enum Gender {
        MALE, FEMALE,
    }

}
