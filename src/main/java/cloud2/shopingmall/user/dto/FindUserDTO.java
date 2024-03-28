package cloud2.shopingmall.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class FindUserDTO {

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
