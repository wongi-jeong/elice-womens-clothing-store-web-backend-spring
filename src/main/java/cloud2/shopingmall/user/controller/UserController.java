package cloud2.shopingmall.user.controller;

import cloud2.shopingmall.common.exception.PasswordMismatchException;
import cloud2.shopingmall.jwt.CustomUserDetails;
import cloud2.shopingmall.user.dto.CommonDTO;
import cloud2.shopingmall.user.dto.UserDTO;
import cloud2.shopingmall.user.dto.UserProfileDTO;
import cloud2.shopingmall.user.entity.User;
import cloud2.shopingmall.user.entity.UserProfile;
import cloud2.shopingmall.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

//    @PostMapping("/login")
//    public String login(@RequestBody UserDTO.LoginRequest request) {
//        // 간단히 사용자가 제공한 아이디와 패스워드를 출력하는 예시
//        System.out.println("사용자 아이디: " + request.getUsername());
//        System.out.println("비밀번호: " + requestg.getPassword());
//
//        // 로그인 성공 시 메시지 반환
//        return "로그인 성공";
//    }

    // 회원가입 기능
    // 하나의 메서드에서는 하나의 @RequestBody만 사용 가능하다
    @PostMapping("/join")
    public ResponseEntity<String> joinProcess(@RequestBody CommonDTO.JoinRequest request,
                                              BindingResult bindingResult) throws PasswordMismatchException {
        UserDTO.Join userDTO = request.getUserDTO();
        UserProfileDTO.Join userProfileDTO = request.getUserProfileDTO();

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
        if (!userService.joinProcess(userDTO, userProfileDTO)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입에 실패했습니다.");
        }

        // 정상적인 응답 반환
        return ResponseEntity.ok("회원가입 성공");
    }

    // 아이디 찾기 기능
    @GetMapping("/findID")
    public ResponseEntity<String> findId(@RequestBody UserProfileDTO.FindUser findUserDTO) {

        String resultId = userService.findUserId(findUserDTO);

        return ResponseEntity.status(HttpStatus.OK).body("찾으시는 아이디는 " + resultId + " 입니다.");
    }

    // 비밀번호 찾기 기능 필터
    @GetMapping("/findPassword")
    public ResponseEntity<String> findPasswordFilter(@RequestBody UserProfileDTO.FindPassword findPasswordDTO) {

        if (!userService.findPasswordFilter(findPasswordDTO)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호 찾기에 실패했습니다.");
        }

        // 정상적인 응답 반환
        return ResponseEntity.status(HttpStatus.OK).body("비밀번호 찾기 정보 입력 완료");
    }

    @PatchMapping("/findPassword")
    public ResponseEntity<String> findPasswordChange(@RequestBody UserDTO.ChangePassword changePasswordDTO) throws PasswordMismatchException {

        if (!userService.changePassword(changePasswordDTO)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호 찾기에 실패했습니다.");
        }

        // 정상적인 응답 반환
        return ResponseEntity.status(HttpStatus.OK).body("비밀번호 변경이 완료되었습니다.");
    }

    // 마이페이지 이동시 현재 로그인한 사용자 정보 조회 기능
    @GetMapping("/myInfor")
    public ResponseEntity<CommonDTO.ShowResponse> showMyInfo(@AuthenticationPrincipal CustomUserDetails userInfo) {
        CommonDTO.ShowResponse dtos = userService.showUser(userInfo);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 사용자 회원정보 변경 기능
    @PatchMapping("/changeInfo")
    public ResponseEntity<String> changeInfo(@AuthenticationPrincipal CustomUserDetails userInfo, @RequestBody CommonDTO.ChangeInfoRequest request) throws PasswordMismatchException {
        Boolean result = userService.changeInfo(userInfo, request);
        return ResponseEntity.status(HttpStatus.OK).body("회원정보 변경이 완료되었습니다.");
    }
}