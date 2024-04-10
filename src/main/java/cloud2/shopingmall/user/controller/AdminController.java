package cloud2.shopingmall.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @GetMapping("/admin/home")
    public ResponseEntity<AdminDTO> admin() {
        System.out.println("관리자페이지");

        // 임의의 DTO 객체 생성
        AdminDTO adminDTO = new AdminDTO("Hello, Admin!", 200, true);

        // ResponseEntity를 사용하여 DTO 객체를 JSON 응답으로 반환
        return ResponseEntity.ok(adminDTO);
    }

    public class AdminDTO {
        private String message;
        private int statusCode;
        private boolean success;

        public AdminDTO() {
        }

        public AdminDTO(String message, int statusCode, boolean success) {
            this.message = message;
            this.statusCode = statusCode;
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }
    }
}
