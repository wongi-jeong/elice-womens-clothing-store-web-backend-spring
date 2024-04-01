package cloud2.shopingmall.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class UserProfileDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Join {
        @NotNull(message = "이름을 입력해 주세요.")
        @Pattern(regexp = "^[가-힣]*$", message = "한글만 입력 가능합니다.")
        private String name;

        @NotNull(message = "이메일을 입력해 주세요.")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "올바른 이메일 주소 형식이 아닙니다.")
        private String email;

        @NotNull(message = "핸드폰 번호를 입력해 주세요.")
        @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message = "올바른 핸드폰 번호 형식이 아닙니다.")
        private String phoneNumber;

        @NotNull(message = "주소를 입력해 주세요.")
        private String address;

        @NotNull(message = "성별을 선택해 주세요.")
        private Gender gender;

        @NotNull(message = "생년월일을 입력해 주세요.")
        private String birthDate;

        @Getter
        public enum Gender {
            MALE("Male"),
            FEMALE("Female");

            private String key;

            Gender(String key) {
                this.key = key;
            }
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindUser{

        @NotNull(message = "핸드폰 번호를 입력해 주세요.")
        @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message = "올바른 핸드폰 번호 형식이 아닙니다.")
        private String phoneNumber;

        @NotNull(message = "이메일을 입력해 주세요.")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "올바른 이메일 주소 형식이 아닙니다.")
        private String email;

        private Source source;

        @Getter
        public enum Source {
            PHONE("phone"), EMAIL("email");

            private String key;


            Source(String source) {
                this.key = source;
            }
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindPassword{

        @NotNull(message = "아이디를 입력해 주세요.")
        private String username;

        private String phoneNumber;

        private String email;

        private Source source;

        @Getter
        public enum Source {
            PHONE("phone"), EMAIL("email");

            private String key;

            Source(String source) {
                this.key = source;
            }
        }
    }





}
