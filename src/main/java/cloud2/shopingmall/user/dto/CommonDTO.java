package cloud2.shopingmall.user.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;


public class CommonDTO {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinRequest {

        private UserDTO.Join userDTO;


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

        private UserDTO.ChangeInfo userDTO;

        private UserProfileDTO.ChangeInfo userProfileDTO;
    }

}
