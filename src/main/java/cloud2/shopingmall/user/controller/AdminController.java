package cloud2.shopingmall.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.status(HttpStatus.OK).body("admin login complete");
    }
}
