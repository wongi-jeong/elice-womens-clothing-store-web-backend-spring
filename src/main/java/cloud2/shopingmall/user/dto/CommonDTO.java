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

}
