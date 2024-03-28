package cloud2.shopingmall.user.controller;

import cloud2.shopingmall.user.dto.UserDTO;
import cloud2.shopingmall.user.dto.UserProfileDTO;
import cloud2.shopingmall.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    // 회원가입 기능
    @PostMapping("/join")
    public ResponseEntity<String> joinProcess(@Valid UserDTO userDTO,
                                              @Valid UserProfileDTO.Create userProfileDTO,
                                              BindingResult bindingResult) {

        // 유효성 검사 결과 확인
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            StringBuilder errorMessage = new StringBuilder();

            for (FieldError error : errors) {
                errorMessage.append(error.getDefaultMessage()).append("; ");
            }

            // 클라이언트에게 유효성 검사 실패 메시지 반환
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
        }

        // 유효성 검사 통과 시 UserService의 joinProcess 메서드 호출
        if(!userService.joinProcess(userDTO, userProfileDTO)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입에 실패했습니다.");
        }

        // 정상적인 응답 반환
        return ResponseEntity.ok("회원가입 성공");
    }

    // 아이디 중복 체크


    // 아이디 찾기 기능
    @GetMapping("/findID")
    public ResponseEntity<String> findId(UserProfileDTO.FindUser findUserDTO) {

        String resultId = userService.findUserId(findUserDTO);

        return ResponseEntity.status(HttpStatus.OK).body("찾으시는 아이디는 " + resultId + " 입니다.");
    }




}
