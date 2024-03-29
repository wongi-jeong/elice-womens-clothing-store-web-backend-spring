package cloud2.shopingmall.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @GetMapping("/admin/home")
    public ResponseEntity<String> admin() {
        System.out.println("관리자페이지");
        return ResponseEntity.ok("관리자 페이지 입니다.");
    }
}
