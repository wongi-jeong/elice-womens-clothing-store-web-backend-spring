package cloud2.shopingmall.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;



public class UserDTO {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Join {
        @NotNull(message = "아이디를 입력해 주세요.")
        private String username;

        @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[!@])[A-Za-z\\d!@]{8,}$",
                message = "비밀번호는 영문 , 숫자, 특수문자(!@)를 모두 포함해야 하며, 적어도 8자 이상이어야 합니다.")
        private String password;

        private String secondPassword;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChangePassword {

        private String username;

        @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[!@])[A-Za-z\\d!@]{8,}$",
                message = "비밀번호는 영문 , 숫자, 특수문자(!@)를 모두 포함해야 하며, 적어도 8자 이상이어야 합니다.")
        private String password;

        private String secondPassword;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Show {

        private String username;

        private String password;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChangeInfo {

        @NotNull(message = "현재 비밀번호를 입력해주세요.")
        private String currentPassword;

        private String password;

        private String secondPassword;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest {

        @NotNull(message = "아이디를 입력해 주세요.")
        private String username;

        @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[!@])[A-Za-z\\d!@]{8,}$",
                message = "비밀번호는 영문 , 숫자, 특수문자(!@)를 모두 포함해야 하며, 적어도 8자 이상이어야 합니다.")
        private String password;


    }




}
