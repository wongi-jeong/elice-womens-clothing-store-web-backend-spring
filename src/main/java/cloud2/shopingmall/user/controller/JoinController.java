package cloud2.shopingmall.user.controller;

import cloud2.shopingmall.user.dto.UserDTO;
import cloud2.shopingmall.user.service.JoinService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinController {
    private final JoinService joinService;

    public JoinController(JoinService joinService) {

        this.joinService = joinService;
    }

    @PostMapping("/join")
    public String joinProcess(UserDTO userDTO) {

        joinService.joinProcess(userDTO);

        return "ok";
    }
}
