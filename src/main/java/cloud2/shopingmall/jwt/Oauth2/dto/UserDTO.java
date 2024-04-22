package cloud2.shopingmall.jwt.Oauth2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String role;
    private String name;
    private String username;
    private String email;
}