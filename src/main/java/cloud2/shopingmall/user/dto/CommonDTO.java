package cloud2.shopingmall.user.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CommonDTO {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinRequest {
        @Valid
        private UserDTO.Join userDTO;

        @Valid
        private UserProfileDTO.Join userProfileDTO;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ShowResponse {

        private UserDTO.Show userDTO;

        private UserProfileDTO.Show userProfileDTO;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChangeInfoRequest {
        @Valid
        private UserDTO.ChangeInfo userDTO;
        @Valid
        private UserProfileDTO.ChangeInfo userProfileDTO;
    }

}
